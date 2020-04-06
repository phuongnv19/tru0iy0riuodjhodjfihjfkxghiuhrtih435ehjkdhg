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
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.Company;
import com.toprate.erp.model.Contact;
import com.toprate.erp.model.CrmDeal;
import com.toprate.erp.repositories.AttachDocumentRepository;
import com.toprate.erp.repositories.CompanyRepository;
import com.toprate.erp.repositories.ContactRepository;
import com.toprate.erp.repositories.CrmDealRepository;
import com.toprate.erp.service.CompanyService;
import com.toprate.erp.service.ContactService;
import com.toprate.erp.service.CrmDealService;
import com.toprate.erp.service.LeadSourceService;
import com.toprate.erp.util.EmailUtils;
import com.toprate.erp.util.EncryptUtils;
import com.toprate.erp.util.ErpConstants;
import com.toprate.erp.util.FileHelper;
import com.toprate.erp.util.MessageUtils;
import com.toprate.erp.util.PhoneUtils;
import com.toprate.erp.util.ValidateUtils;

@Service
public class CrmDealServiceImpl implements CrmDealService, ErpConstants {

	@Autowired
	AttachDocumentRepository attachDocumentRepository;

	@Autowired
	CrmDealRepository crmDealRepository;

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	LeadSourceService leadSourceService;

	@Autowired
	private AuthenticationServiceImpl authenticationServiceImpl;

	@Value("${file.input.extentions}")
	private String[] uploadFileInputExt;

	@Value("${file.upload-dir}")
	private String fileDirectory;

	private static final int LIMIT_FILE = 3;
	private static final int MAX_LENGHT = 255;
	private static final String FIRST_NAME = "First name";
	private static final String LAST_NAME = "Last name";
	private static final String ARN = "Acn";
	private static final String PHONE = "Phone";
	private static final String NOTE = "Note";
	private static final String CRM_DEAL = "Crm deal";
	private static final String DESCRIPTION = "Description";
	private static final String FILE = "File";
	private static final String CONTACT = "Contact";
	private static final String COMPANY = "Company";

	@Override
	public List<CrmDealDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CrmDealDTO saveAndUpdate(CrmDealDTO crmDealDTO) throws IOException {
		checkRequire(crmDealDTO);
		checkMaxlength(crmDealDTO);
		checkFormat(crmDealDTO);
//		checkExist(crmDealDTO);

		// update contact
		ContactDTO contactDTO = new ContactDTO();
		contactDTO.setContactId(crmDealDTO.getContactId());
		contactDTO.setCompanyId(crmDealDTO.getCompanyId());
		contactDTO.setContactEmail(crmDealDTO.getEmail());
		contactDTO.setContactFirstName(crmDealDTO.getFirstName());
		contactDTO.setContactLastName(crmDealDTO.getLastName());
		contactDTO.setContactPhone(crmDealDTO.getPhone());
		contactRepository.update(contactDTO.toModel());

		// update company
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setAbn(crmDealDTO.getAbn());
		companyDTO.setAcn(crmDealDTO.getArn());
		companyDTO.setCompanyId(crmDealDTO.getCompanyId());
		companyRepository.update(companyDTO.toModel());;

		crmDealDTO.setCreateDate(new Date());
//		crmDealDTO.setCreateUserId(authenticationServiceImpl.getCurrentUser().getUserId().toString());

		crmDealRepository.updateCrmDeal(crmDealDTO);

		// TODO save log
		return crmDealDTO;
	}

	@Override
	public DataListDTO doSearch(CrmDealDTO crmDealDTO) {
		List<CrmDealDTO> crmDealDTOs = crmDealRepository.doSearch(crmDealDTO);
		DataListDTO data = new DataListDTO();
		data.setData(crmDealDTOs);
		data.setTotal(crmDealDTO.getTotalRecord());
		data.setSize(crmDealDTO.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@Override
	public CrmDealDTO addCrmDeal(CrmDealDTO crmDealDTO) throws Exception {
		checkRequire(crmDealDTO);
		checkMaxlength(crmDealDTO);
		checkFormat(crmDealDTO);

		long companyId;
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setAbn(crmDealDTO.getAbn());
		companyDTO.setAcn(crmDealDTO.getArn());

		if (crmDealDTO.getCompanyId() == null || crmDealDTO.getCompanyId() == 0L
				|| null == companyRepository.find(crmDealDTO.getCompanyId())) {
			// insert tbl company
			companyId = companyRepository.insert(companyDTO.toModel());
		} else {
			companyId = crmDealDTO.getCompanyId();
		}
		companyDTO.setCompanyId(companyId);
		// insert tbl contact
		ContactDTO contactDTO = new ContactDTO();
		contactDTO.setCompanyId(companyId);
		contactDTO.setContactEmail(crmDealDTO.getEmail());
		contactDTO.setContactFirstName(crmDealDTO.getFirstName());
		contactDTO.setContactLastName(crmDealDTO.getLastName());
		contactDTO.setContactPhone(crmDealDTO.getPhone());
		long contactId = contactRepository.insert(contactDTO.toModel());

		crmDealDTO.setContactId(contactId);
		crmDealDTO.setDealStatus(ErpConstants.ACTIVE);
		// location
		crmDealDTO.setCreateDate(new Date());
//		crmDealDTO.setCreateUserId(authenticationServiceImpl.getCurrentUser().getUserId().toString());
		crmDealDTO.setDealPriorityLevel(0L);
		crmDealDTO.setPhaseId(11L);
		long id = crmDealRepository.insert(crmDealDTO.toModel());
		crmDealDTO.setDealId(id);
		// TODO save log
		return crmDealDTO;
	}

	@Override
	public void deleteCrmDeal(CrmDealDTO crmDealDTO) {
		if (StringUtils.isNotEmpty(crmDealDTO.getTerminatedDealReason())) {
			ValidateUtils.checkMaxlength(crmDealDTO.getTerminatedDealReason(), ErpConstants.PARAM_IS_MAXLENGTH,
					DESCRIPTION, MAX_LENGHT);
		}

		CrmDeal crmDeal = crmDealRepository.find(crmDealDTO.getDealId());
		if (null == crmDeal) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, CRM_DEAL));
		}
		crmDealDTO.setDealStatus(ErpConstants.INACTIVE);
		crmDealRepository.deleteCrmDeal(crmDealDTO);
	}

	@Override
	public List<String> upLoadFileCrmDeal(MultipartFile[] files, Long id) throws Exception {
		AttachDocumentDTO attachDocumentDTO = new AttachDocumentDTO();
		attachDocumentDTO.setSourceId(id);
		attachDocumentDTO.setTableName(CRM_DEAL.toUpperCase());
		attachDocumentDTO.setCreateDate(new Date());
//		attachDocumentDTO.setCreateUserId(authenticationServiceImpl.getCurrentUser().getUserId());
		List<String> listFiles = new ArrayList<>();
		if (null == files) {
			return Collections.emptyList();
		}
		if (files.length > LIMIT_FILE) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.UPLOAD_FILE_LIMITED));
		}

		String extension = null;

		for (int i = 0; i < files.length; i++) {
			if (files[i].isEmpty()) {
				continue;
			}
			String pathDir = fileDirectory + "crm-deal/";
			createFolderIfNotExists(pathDir);
			extension = FileHelper.getExtension(files[i].getOriginalFilename()).toLowerCase();
			if (Arrays.asList(uploadFileInputExt).contains(extension)) {
				byte[] bytes = files[i].getBytes();
				Path path = Paths.get(pathDir + files[i].getOriginalFilename());
				Files.write(path, bytes);
				listFiles.add(EncryptUtils.encryptFileUploadPath(path.toString()));
				attachDocumentDTO.setDocumentPath(EncryptUtils.encryptFileUploadPath(path.toString()));
				attachDocumentDTO.setDocumentName(path.getFileName().toString());
				attachDocumentRepository.insert(attachDocumentDTO.toModel());
			} else {
				throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_FORMAT, FILE));
			}
		}
		// TODO save log
		return listFiles;
	}

	private void createFolderIfNotExists(String dirName) {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}

	private void checkRequire(CrmDealDTO crmDealDTO) {
		ValidateUtils.checkRequire(crmDealDTO.getFirstName(), ErpConstants.PARAM_IS_REQUIRED, FIRST_NAME);
		ValidateUtils.checkRequire(crmDealDTO.getLastName(), ErpConstants.PARAM_IS_REQUIRED, LAST_NAME);
		ValidateUtils.checkRequire(crmDealDTO.getPhone(), ErpConstants.PARAM_IS_REQUIRED, PHONE);
	}

	private void checkMaxlength(CrmDealDTO crmDealDTO) {
		if (StringUtils.isNotEmpty(crmDealDTO.getArn())) {
			ValidateUtils.checkMaxlength(crmDealDTO.getArn(), ErpConstants.PARAM_IS_MAXLENGTH, ARN, MAX_LENGHT);
		}

		ValidateUtils.checkMaxlength(crmDealDTO.getFirstName(), ErpConstants.PARAM_IS_MAXLENGTH, FIRST_NAME,
				MAX_LENGHT);
		ValidateUtils.checkMaxlength(crmDealDTO.getLastName(), ErpConstants.PARAM_IS_MAXLENGTH, LAST_NAME, MAX_LENGHT);
		ValidateUtils.checkMaxlength(crmDealDTO.getPhone(), ErpConstants.PARAM_IS_MAXLENGTH, PHONE, MAX_LENGHT);

		if (StringUtils.isNotEmpty(crmDealDTO.getDealNote())) {
			ValidateUtils.checkMaxlength(crmDealDTO.getDealNote(), ErpConstants.PARAM_IS_MAXLENGTH, NOTE, MAX_LENGHT);
		}

	}

	private void checkFormat(CrmDealDTO crmDealDTO) {
		if (!PhoneUtils.isPhone(crmDealDTO.getPhone())) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_FORMAT, PHONE));
		}
	}

	private void checkExist(CrmDealDTO crmDealDTO) {
		CrmDeal crmDeal = crmDealRepository.find(crmDealDTO.getDealId());
		if (null == crmDeal) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, CRM_DEAL));
		}

		Contact contact = contactRepository.find(crmDealDTO.getContactId());
		if (null == contact) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, CONTACT));
		}

		Company company = companyRepository.find(crmDealDTO.getCompanyId());
		if (null == company) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, COMPANY));
		}
	}

	@Override
	public void updatePhase(CrmDealDTO crmDealDTO) {
		if (null != crmDealDTO.getPhaseId() && 0L != crmDealDTO.getPhaseId()
				&& ErpConstants.PHASE_TYPE_DEAL == crmDealDTO.getPhaseType()) {
			crmDealRepository.updatePhase(crmDealDTO);
		}
	}

	@Override
	public void lockStatus(CrmDealDTO crmDealDTO) {
		CrmDeal crmDeal = crmDealRepository.find(crmDealDTO.getLeadId());
		if (null == crmDeal) {
			throw new BusinessException(MessageUtils.getMessage(ErpConstants.PARAM_IS_NOT_FOUND, CRM_DEAL));
		}
		crmDealRepository.lockStatus(crmDealDTO);
	}

}
