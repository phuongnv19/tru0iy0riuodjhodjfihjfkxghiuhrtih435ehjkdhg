package com.toprate.erp.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.AttachDocumentDTO;
import com.toprate.erp.dto.CompanyDTO;
import com.toprate.erp.dto.ContactDTO;
import com.toprate.erp.dto.CrmDealDTO;
import com.toprate.erp.dto.CrmLeadDTO;
import com.toprate.erp.dto.LeadSourceDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.AttachDocument;
import com.toprate.erp.model.Company;
import com.toprate.erp.model.Contact;
import com.toprate.erp.model.CrmLead;
import com.toprate.erp.model.CrmPhase;
import com.toprate.erp.repositories.AttachDocumentRepository;
import com.toprate.erp.repositories.CompanyRepository;
import com.toprate.erp.repositories.ContactRepository;
import com.toprate.erp.repositories.CrmLeadRepository;
import com.toprate.erp.repositories.CrmPhaseRepository;
import com.toprate.erp.service.CompanyService;
import com.toprate.erp.service.CrmDealService;
import com.toprate.erp.service.CrmLeadService;
import com.toprate.erp.service.LeadSourceService;
import com.toprate.erp.util.EmailUtils;
import com.toprate.erp.util.EncryptUtils;
import com.toprate.erp.util.ErpConstants;
import com.toprate.erp.util.FileHelper;
import com.toprate.erp.util.MessageUtils;
import com.toprate.erp.util.PhoneUtils;
import com.toprate.erp.util.ValidateUtils;

@Service
public class CrmLeadServiceImpl implements CrmLeadService, ErpConstants {

	@Autowired
	CrmLeadRepository crmLeadRepository;

	@Autowired
	AttachDocumentRepository attachDocumentRepository;

	@Autowired
	CrmPhaseRepository crmPhaseRepository;

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	LeadSourceService leadSourceService;

	@Autowired
	CrmDealService crmDealService;

	@Autowired
	private AuthenticationServiceImpl authenticationServiceImpl;

//	@Value("${file.input.extentions}")
	private String[] uploadFileInputExt;

	// @Value("${file.upload-dir}")
	private String fileDirectory;

	private static final int LIMIT_FILE = 3;
	private static final int MAX_LENGHT = 255;
	private static final String EMAIL = "Email";
	private static final String FIRST_NAME = "First name";
	private static final String LAST_NAME = "Last name";
	private static final String ARN = "Acn";
	private static final String PHONE = "Phone";
	private static final String NOTE = "Note";
	private static final String CRM_LEAD = "Crm lead";
	private static final String DESCRIPTION = "Description";
	private static final String FILE = "File";
	private static final String CONTACT = "Contact";
	private static final String COMPANY = "Company";
	private static final String CRM_PHASE = "Crm phase";

	@Override
	public List<CrmLeadDTO> findAll() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public CrmLeadDTO saveAndUpdate(CrmLeadDTO crmLeadDTO) throws IOException {
		checkRequire(crmLeadDTO);
		checkMaxlength(crmLeadDTO);
		checkFormat(crmLeadDTO);
//		checkExist(crmLeadDTO);

		// update contact
		ContactDTO contactDTO = new ContactDTO();
		contactDTO.setContactId(crmLeadDTO.getContactId());
		contactDTO.setCompanyId(crmLeadDTO.getCompanyId());
		contactDTO.setContactEmail(crmLeadDTO.getEmail());
		contactDTO.setContactFirstName(crmLeadDTO.getFirstName());
		contactDTO.setContactLastName(crmLeadDTO.getLastName());
		contactDTO.setContactPhone(crmLeadDTO.getPhone());
		contactRepository.update(contactDTO.toModel());

		// update company
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setAbn(crmLeadDTO.getAbn());
		companyDTO.setAcn(crmLeadDTO.getArn());
		companyDTO.setCompanyId(crmLeadDTO.getCompanyId());
		companyRepository.update(companyDTO.toModel());

		crmLeadDTO.setCreateDatetime(new Date());
//		crmLeadDTO.setCreateUserId(1L);

		crmLeadRepository.updateCrmLead(crmLeadDTO);

		// TODO save log
		return crmLeadDTO;
	}

	@Override
	public DataListDTO doSearch(CrmLeadDTO crmLeadDTO) {
		List<CrmLeadDTO> crmLeadDTOs = crmLeadRepository.doSearch(crmLeadDTO);
		DataListDTO data = new DataListDTO();
		data.setData(crmLeadDTOs);
		data.setTotal(crmLeadDTO.getTotalRecord());
		data.setSize(crmLeadDTO.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@Override
	public boolean importCrmLead(MultipartFile file) {
		// TODO
		return false;
	}

	@Override
	public LeadSourceDTO addLeadSource(LeadSourceDTO leadSourceDTO) throws Exception {
		return leadSourceService.addLeadSource(leadSourceDTO);
	}

	@Override
	public List<LeadSourceDTO> getLeadSources() {
		return leadSourceService.findAll();
	}

	@Override
	public CrmLeadDTO addCrmLead(CrmLeadDTO crmLeadDTO) throws Exception {
		checkRequire(crmLeadDTO);
		checkMaxlength(crmLeadDTO);
		checkFormat(crmLeadDTO);
//		checkExitCrmPhase(crmLeadDTO);

		long companyId;
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setAbn(crmLeadDTO.getAbn());
		companyDTO.setAcn(crmLeadDTO.getArn());

		if (crmLeadDTO.getCompanyId() == 0L || null == companyRepository.find(crmLeadDTO.getCompanyId())) {
			// insert tbl company
			companyId = companyRepository.insert(companyDTO.toModel());
		} else {
			companyId = crmLeadDTO.getCompanyId();
		}
		companyDTO.setCompanyId(companyId);
		// insert tbl contact
		ContactDTO contactDTO = new ContactDTO();
		contactDTO.setCompanyId(companyId);
		contactDTO.setContactEmail(crmLeadDTO.getEmail());
		contactDTO.setContactFirstName(crmLeadDTO.getFirstName());
		contactDTO.setContactLastName(crmLeadDTO.getLastName());
		contactDTO.setContactPhone(crmLeadDTO.getPhone());

		Long contactId = contactRepository.insert(contactDTO.toModel());

		crmLeadDTO.setCompanyId(companyId);
		crmLeadDTO.setContactId(contactId);
		crmLeadDTO.setLeadStatus(ErpConstants.ACTIVE);
		// location
		crmLeadDTO.setCreateDatetime(new Date());
		crmLeadDTO.setCreateUserId(1L);
		crmLeadDTO.setPriorityLevel(0L);
		crmLeadDTO.setPhaseId(6L);
		crmLeadDTO.setStatus("1");
		long id = crmLeadRepository.insert(crmLeadDTO.toModel());
		crmLeadDTO.setLeadId(id);
		// TODO save log
		insertFiles(crmLeadDTO);
		return crmLeadDTO;
	}

	private void checkExitCrmPhase(CrmLeadDTO crmLeadDTO) {
		if (null != crmLeadDTO.getPhaseId() && 0L != crmLeadDTO.getPhaseId()) {
			CrmPhase crmPhase = crmPhaseRepository.find(crmLeadDTO.getPhaseId());
			if (null == crmPhase) {
				throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, CRM_PHASE));
			}
		}
	}

	@Override
	public void deleteCrmLead(CrmLeadDTO crmLeadDTO) {
		if (StringUtils.isNotEmpty(crmLeadDTO.getTerminatedLeadReason())) {
			ValidateUtils.checkMaxlength(crmLeadDTO.getTerminatedLeadReason(), ErpConstants.PARAM_IS_MAXLENGTH,
					DESCRIPTION, MAX_LENGHT);
		}

		CrmLead crmLead = crmLeadRepository.find(crmLeadDTO.getLeadId());
		if (null == crmLead) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, CRM_LEAD));
		}
		crmLeadDTO.setLeadStatus(ErpConstants.INACTIVE);
		crmLeadRepository.deleteCrmLead(crmLeadDTO);
	}

	@Override
	public List<String> insertFiles(CrmLeadDTO crmLeadDTO) throws Exception {
		AttachDocumentDTO attachDocumentDTO = new AttachDocumentDTO();
		attachDocumentDTO.setSourceId(crmLeadDTO.getCreateUserId());
		attachDocumentDTO.setTableName(CRM_LEAD.toUpperCase());
		attachDocumentDTO.setCreateDate(new Date());
		attachDocumentDTO.setCreateUserId(1L);
		List<String> listFiles = crmLeadDTO.getFiles();
		if (null == listFiles) {
			return Collections.emptyList();
		}
		if (listFiles.size() > LIMIT_FILE) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.UPLOAD_FILE_LIMITED));
		}

		for (String file : listFiles) {
			String pathFile = EncryptUtils.decryptFileUploadPath(file);
			String[] pathName = pathFile.split("\\\\");
			attachDocumentDTO.setDocumentPath(EncryptUtils.decryptFileUploadPath(file));
			attachDocumentDTO.setDocumentName(pathName[pathName.length - 1]);
			attachDocumentRepository.insert(attachDocumentDTO.toModel());
		}
		// TODO save log
		return listFiles;
	}

	private void checkRequire(CrmLeadDTO crmLeadDTO) {
		ValidateUtils.checkRequire(crmLeadDTO.getEmail(), ErpConstants.PARAM_IS_REQUIRED, EMAIL);
		ValidateUtils.checkRequire(crmLeadDTO.getFirstName(), ErpConstants.PARAM_IS_REQUIRED, FIRST_NAME);
		ValidateUtils.checkRequire(crmLeadDTO.getLastName(), ErpConstants.PARAM_IS_REQUIRED, LAST_NAME);
		ValidateUtils.checkRequire(crmLeadDTO.getPhone(), ErpConstants.PARAM_IS_REQUIRED, PHONE);
	}

	private void checkMaxlength(CrmLeadDTO crmLeadDTO) {
		if (StringUtils.isNotEmpty(crmLeadDTO.getArn())) {
			ValidateUtils.checkMaxlength(crmLeadDTO.getArn(), ErpConstants.PARAM_IS_MAXLENGTH, ARN, MAX_LENGHT);
		}

		ValidateUtils.checkMaxlength(crmLeadDTO.getEmail(), ErpConstants.PARAM_IS_MAXLENGTH, EMAIL, MAX_LENGHT);
		ValidateUtils.checkMaxlength(crmLeadDTO.getFirstName(), ErpConstants.PARAM_IS_MAXLENGTH, FIRST_NAME,
				MAX_LENGHT);
		ValidateUtils.checkMaxlength(crmLeadDTO.getLastName(), ErpConstants.PARAM_IS_MAXLENGTH, LAST_NAME, MAX_LENGHT);
		ValidateUtils.checkMaxlength(crmLeadDTO.getPhone(), ErpConstants.PARAM_IS_MAXLENGTH, PHONE, MAX_LENGHT);

		if (StringUtils.isNotEmpty(crmLeadDTO.getLeadNote())) {
			ValidateUtils.checkMaxlength(crmLeadDTO.getLeadNote(), ErpConstants.PARAM_IS_MAXLENGTH, NOTE, MAX_LENGHT);
		}

	}

	private void checkFormat(CrmLeadDTO crmLeadDTO) {
		if (!EmailUtils.isEmail(crmLeadDTO.getEmail())) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_FORMAT, EMAIL));
		}

		if (!PhoneUtils.isPhone(crmLeadDTO.getPhone())) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_FORMAT, PHONE));
		}
	}

	private void checkExist(CrmLeadDTO crmLeadDTO) {
		if (null != crmLeadDTO.getLeadId() && 0L != crmLeadDTO.getLeadId()) {
			CrmLead crmLead = crmLeadRepository.find(crmLeadDTO.getLeadId());
			if (null == crmLead) {
				throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, CRM_LEAD));
			}
		}

		if (null != crmLeadDTO.getContactId() && 0L != crmLeadDTO.getContactId()) {
			Contact contact = contactRepository.find(crmLeadDTO.getContactId());
			if (null == contact) {
				throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, CONTACT));
			}
		}

		if (null != crmLeadDTO.getCompanyId() && 0L != crmLeadDTO.getCompanyId()) {
			Company company = companyRepository.find(crmLeadDTO.getCompanyId());
			if (null == company) {
				throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, COMPANY));
			}
		}
	}

	@Override
	public void updatePhase(CrmLeadDTO crmLeadDTO) throws Exception {
//		checkExitCrmPhase(crmLeadDTO);
		if (ErpConstants.PHASE_TYPE_LEAD == crmLeadDTO.getPhaseType()) {
			crmLeadRepository.updatePhase(crmLeadDTO);
			if (ErpConstants.WON_LEAD == crmLeadDTO.getPhaseId()) {
				CrmDealDTO crmDealDTO = new CrmDealDTO();
				BeanUtils.copyProperties(crmDealDTO, crmLeadDTO);
				crmDealDTO.setDealBudget(crmLeadDTO.getLeadBudget());
				crmDealDTO.setDealStatus(ErpConstants.ACTIVE);
				crmDealDTO.setDealNote(crmLeadDTO.getLeadNote());
				crmDealDTO.setCreateDate(new Date());
				crmDealDTO.setDealScore(crmLeadDTO.getLeadScore());
				crmDealService.addCrmDeal(crmDealDTO);
			}
		}
	}

}
