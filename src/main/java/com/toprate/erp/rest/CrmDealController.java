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
import com.toprate.erp.dto.CrmDealDTO;
import com.toprate.erp.dto.LocationDTO;
import com.toprate.erp.service.impl.CrmDealServiceImpl;

@RestController
@RequestMapping("/v1")	
public class CrmDealController {

	@Autowired
	CrmDealServiceImpl crmDealService;

	@Transactional(rollbackOn = Exception.class)
	@PostMapping("/deals/create")
	public ResponseEntity<?> createCrmDeal(@RequestBody CrmDealDTO obj) throws Exception {
		CrmDealDTO crmDealDTO = crmDealService.addCrmDeal(obj);
//		List<String> listFile = crmDealService.upLoadFileCrmDeal(files, crmDealDTO.getDealId());
//		crmDealDTO.setFiles(listFile);
		return new ResponseEntity(crmDealDTO, HttpStatus.CREATED);
	}

	@Transactional(rollbackOn = Exception.class)
	@PostMapping("/deals/update")
	public ResponseEntity<?> updateCrmDeal(@RequestBody CrmDealDTO obj) throws Exception {
		CrmDealDTO crmDealDTO = crmDealService.saveAndUpdate(obj);
//		List<String> listFile = crmDealService.upLoadFileCrmDeal(files, crmDealDTO.getDealId());
//		crmDealDTO.setFiles(listFile);
		return new ResponseEntity(crmDealDTO, HttpStatus.OK);
	}

	@Transactional(rollbackOn = Exception.class)
	@PostMapping("/deals/updatePhase")
	public ResponseEntity<?> updatePhaseId(@RequestBody CrmDealDTO crmDealDTO) {
		crmDealService.updatePhase(crmDealDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional(rollbackOn = Exception.class)
	@PostMapping("/deals/delete")
	public ResponseEntity<?> deleteCrmDeal(@RequestBody CrmDealDTO crmDealDTO) {
		crmDealService.deleteCrmDeal(crmDealDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/deals/doSearch")
	public ResponseEntity<List<CrmDealDTO>> doSearch(@RequestBody CrmDealDTO crmDealDTO) {
		return new ResponseEntity(crmDealService.doSearch(crmDealDTO), HttpStatus.OK);
	}

	@Transactional(rollbackOn = Exception.class)
	@PostMapping("/deals/lockStatus")
	public ResponseEntity<?> lockStatus(@RequestBody CrmDealDTO crmDealDTO) {
		crmDealService.lockStatus(crmDealDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
