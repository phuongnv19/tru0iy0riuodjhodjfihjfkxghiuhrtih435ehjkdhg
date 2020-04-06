package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.SysObjects;
import com.toprate.erp.model.SysRole;
import com.toprate.erp.repositories.ObjectRepository;
import com.toprate.erp.service.ObjectService;
import com.toprate.erp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ObjectServiceImpl implements ObjectService {


    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Override
    public SysObjectsDTO insert(SysObjectsDTO obj) throws Exception {
        if(!checkCode(obj.getObjectCode(), null)){
            throw new BusinessException(MessageUtils.getMessage("dublicate.objectCode"));
        }
        obj.setStatus(1l);
        obj.setCreateDate(new Date());
//        obj.setCreateUser(authenticationServiceImpl.getCurrentUser().getUserId().toString());
        Long id=objectRepository.insert(obj.toModel());
        obj.setObjectId(id);
        return obj;
    }

    @Override
    public SysObjectsDTO update(SysObjectsDTO obj) throws Exception {
        if(!checkCode(obj.getObjectCode(), obj.getObjectId())){
            throw new BusinessException(MessageUtils.getMessage("dublicate.objectCode"));
        }
        objectRepository.update(obj.toModel());
        return obj;
    }

    private Boolean checkCode(String objectCode, Long appParamId) {
        SysObjects obj = objectRepository.findByFiled("objectCode",objectCode);

        if (appParamId == null) {
            if (obj == null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (obj == null) {
                return true;
            } else if (obj != null && obj.getObjectId().longValue() == appParamId) {
                return true;
            } else {
                return false;
            }
        }

    }

    @Override
    public DataListDTO doSearch(SearchDTO obj) {
        List<SysObjectsDTO> ls = objectRepository.doSearch(obj);
        DataListDTO data = new DataListDTO();
        data.setData(ls);
        data.setTotal(obj.getTotalRecord());
        data.setSize(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }

    @Override
    public void delete(SysObjectsDTO obj) throws Exception {
        SysObjects sysObjects=objectRepository.find(obj.getObjectId());
        if(null==sysObjects){
            throw new BusinessException(MessageUtils.getMessage("object.notFound"));
        }
        objectRepository.delete(sysObjects);
    }
}
