package com.toprate.erp.rest;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toprate.erp.dto.CrmLeadDTO;
import com.toprate.erp.dto.LeadSourceDTO;
import com.toprate.erp.service.impl.CrmLeadServiceImpl;

@RestController
@RequestMapping("/v1")
public class CrmLeadController {

	@Autowired
	CrmLeadServiceImpl crmLeadService;

	@Transactional(rollbackOn = Exception.class)
	@PostMapping("/leads/create")
	public ResponseEntity<?> createCrmLead(@RequestBody CrmLeadDTO obj) throws Exception {
		CrmLeadDTO crmLeadDTO = crmLeadService.addCrmLead(obj);
		return new ResponseEntity(crmLeadDTO, HttpStatus.CREATED);
	}

	@Transactional(rollbackOn = Exception.class)
	@PostMapping("leads/update")
	public ResponseEntity<?> updateCrmLead(@RequestBody CrmLeadDTO obj) throws Exception {
		CrmLeadDTO crmLeadDTO = crmLeadService.saveAndUpdate(obj);
		return new ResponseEntity(crmLeadDTO, HttpStatus.OK);
	}

	@Transactional(rollbackOn = Exception.class)
	@PostMapping("leads/updatePhase")
	public ResponseEntity<?> updatePhaseId(@RequestBody CrmLeadDTO crmLeadDTO) throws Exception {
		crmLeadService.updatePhase(crmLeadDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Transactional(rollbackOn = Exception.class)
	@PostMapping("/leads/delete")
	public ResponseEntity<?> deleteCrmLead(@RequestBody CrmLeadDTO crmLeadDTO) {
		crmLeadService.deleteCrmLead(crmLeadDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/leads/doSearch")
	public ResponseEntity<?> doSearch(@RequestBody CrmLeadDTO crmLeadDTO) {
		return new ResponseEntity(crmLeadService.doSearch(crmLeadDTO), HttpStatus.OK);
	}

	@Transactional(rollbackOn = Exception.class)
	@PostMapping("/leads/import")
	public ResponseEntity<?> importCrmLead(@RequestBody MultipartFile file) {
		// TODO
		return new ResponseEntity(crmLeadService.importCrmLead(file), HttpStatus.OK);
	}

	@Transactional(rollbackOn = Exception.class)
	@PostMapping("/leads/createLeadSource")
	public ResponseEntity<?> createLeadSource(@RequestBody LeadSourceDTO leadSourceDTO) throws Exception {
		return new ResponseEntity(crmLeadService.addLeadSource(leadSourceDTO), HttpStatus.OK);
	}

	@PostMapping("/leads/getLeadSource")
	public ResponseEntity<?> getLeadSources() {
		return new ResponseEntity(crmLeadService.getLeadSources(), HttpStatus.OK);
	}

}
