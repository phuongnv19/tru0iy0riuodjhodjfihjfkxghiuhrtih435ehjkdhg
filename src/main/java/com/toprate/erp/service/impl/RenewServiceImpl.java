package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.dto.ContractDTO;
import com.toprate.erp.model.Contract;
import com.toprate.erp.repositories.ContractRepository;
import com.toprate.erp.service.RenewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenewServiceImpl implements RenewService {

    @Autowired
    ContractRepository contractRepository;

    public static Long contractLockStatus = (long) 2;// 0:inactive, 1:active, 2:locked

    @Override
    public DataListDTO doSearch(ContractDTO obj) {
        List<ContractDTO> list = contractRepository.doSearchRenew(obj);
        DataListDTO data = new DataListDTO();

        data.setData(list);
        data.setSize(obj.getPageSize());
        data.setTotal(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }

//    @Override
//    public ContractDTO lockStatus(ContractDTO obj) {
//        Contract contract =  contractRepository.find(obj.getId());
//        contract.setContractStatus(contractLockStatus);
//        contractRepository.update(contract);
//        return contract.toDto();
//    }

    @Override
    public ContractDTO update(ContractDTO obj) {
        Contract contract = contractRepository.find(obj.getId());
        if (contract != null && contract.getContractStatus()==0){
            contract.setContractStatus((long) 1);
        }
        contractRepository.update(contract);
        return contract.toDto();
    }

    @Override
    public int delete(ContractDTO obj) {
        try {
            contractRepository.delete(contractRepository.find(obj.getId()));
            return 1;
        } catch (Exception e){
            return 0;
        }
    }
}
