package com.toprate.erp.service;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.dto.SysRoleDTO;

public interface ObjectService {
    SysObjectsDTO insert(SysObjectsDTO obj) throws Exception;

    SysObjectsDTO update(SysObjectsDTO obj) throws Exception;

    DataListDTO doSearch(SearchDTO obj);

    void delete(SysObjectsDTO obj) throws  Exception;
}
