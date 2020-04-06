package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.ContractDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.Contract;
import com.toprate.erp.repositories.ContractRepository;
import com.toprate.erp.service.NewClientService;
import com.toprate.erp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewClientServiceImpl implements NewClientService {

    public static Long contractLockStatus = (long) 2;

    @Autowired
    ContractRepository contractRepository;

    @Override
    public List<ContractDTO> doSearch(ContractDTO obj) {
        List<ContractDTO> list = contractRepository.doSearchNewClient(obj);
        return list;
    }

    @Override
    public ContractDTO update(ContractDTO dto) throws BusinessException {
        Contract contract = contractRepository.find(dto.getId());
        try {
            if (contract.getContractStatus()!=contractLockStatus){
                contract.setPhaseId(dto.getPhaseId());
                contractRepository.update(contract);
                return contract.toDto();
            } else throw new BusinessException(MessageUtils.getMessage("Contract.locked"));
        } catch (Exception e){
            throw new BusinessException(MessageUtils.getMessage("Contract.locked or null"));
        }
    }

    @Override
    public ContractDTO lockStatus(ContractDTO dto) throws BusinessException {
        try {
            Contract contract = contractRepository.find(dto.getId());
            contract.setContractStatus(contractLockStatus);
            contractRepository.update(contract);
            return contract.toDto();
        } catch (Exception e){
            throw new BusinessException(MessageUtils.getMessage("error.lockstatus"));
        }
    }
}
