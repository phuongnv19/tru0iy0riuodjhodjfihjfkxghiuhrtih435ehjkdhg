package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.dto.SysRoleDTO;
import com.toprate.erp.dto.SysRoleObjectDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.SysRole;
import com.toprate.erp.model.SysUser;
import com.toprate.erp.repositories.ObjectRepository;
import com.toprate.erp.repositories.RoleRepository;
import com.toprate.erp.service.RoleService;
import com.toprate.erp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Override
    public SysRoleDTO insert(SysRoleDTO obj) throws Exception{
        if(!checkCode(obj.getRoleCode(), null)){
            throw new BusinessException(MessageUtils.getMessage("dublicate.roleCode"));
        }
        obj.setStatus(1l);
        obj.setCreateDate(new Date());
//        obj.setCreateUser(authenticationServiceImpl.getCurrentUser().getUserId().toString());
        Long id=roleRepository.insert(obj.toModel());
        obj.setRoleId(id);
        return obj;
    }

    @Override
    public SysRoleDTO update(SysRoleDTO obj) throws Exception{

        if(null==obj.getRoleId()){
            throw new BusinessException(MessageUtils.getMessage("role.notfound"));
        }

        SysRoleDTO sysRoleDTO= roleRepository.getById(obj.getRoleId());

        if(null==obj){
            throw new BusinessException(MessageUtils.getMessage("role.notfound"));
        }


        if(!checkCode(obj.getRoleCode(), obj.getRoleId())){
            throw new BusinessException(MessageUtils.getMessage("dublicate.roleCode"));
        }
        sysRoleDTO.setStatus(obj.getStatus());
        sysRoleDTO.setDescription(obj.getDescription());
        sysRoleDTO.setRoleCode(obj.getRoleCode());
        sysRoleDTO.setRoleName(obj.getRoleName());

        roleRepository.update(obj.toModel());
        return obj;
    }


    private Boolean checkCode(String roleCode, Long appParamId) {
        SysRole obj = roleRepository.findByFiled("roleCode",roleCode);

        if (appParamId == null) {
            if (obj == null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (obj == null) {
                return true;
            } else if (obj != null && obj.getRoleId().longValue() == appParamId) {
                return true;
            } else {
                return false;
            }
        }

    }

    @Override
    public DataListDTO doSearch(SearchDTO obj) {
        List<SysRoleDTO> ls = roleRepository.doSearch(obj);
        DataListDTO data = new DataListDTO();
        data.setData(ls);
        data.setTotal(obj.getTotalRecord());
        data.setSize(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }

    @Override
    public void delete(SysRoleDTO obj) throws Exception {
        SysRole sysRole=roleRepository.find(obj.getRoleId());
        if(null==sysRole){
            throw new BusinessException(MessageUtils.getMessage("role.notFound"));
        }
        roleRepository.delete(sysRole);
    }

    @Override
    public List<SysRoleObjectDTO> getListObjectByRoleId(Long roleId) {
        return roleRepository.getListObjectByRoleId(roleId);
    }

    @Override
    @Transactional
    public void insertToRoleObject(SysRoleDTO roleDto) throws Exception {
        roleRepository.deleteByRoleId(roleDto.getRoleId());
        if(roleDto.getSysObjectsList().size()>0){
            for(SysRoleObjectDTO roleUser:roleDto.getSysObjectsList()){
                roleRepository.insertToRoleObject(roleUser,roleDto);
            }
        }
    }


}
