package com.toprate.erp.service;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.dto.SysRoleDTO;
import com.toprate.erp.dto.SysRoleObjectDTO;

import java.util.List;

public interface RoleService {
    SysRoleDTO insert(SysRoleDTO obj) throws Exception;

    SysRoleDTO update(SysRoleDTO obj) throws Exception;

    DataListDTO doSearch(SearchDTO obj);

    void delete(SysRoleDTO obj) throws  Exception;

    List<SysRoleObjectDTO> getListObjectByRoleId(Long roleId);

    void insertToRoleObject(SysRoleDTO roleDto) throws Exception;
}
