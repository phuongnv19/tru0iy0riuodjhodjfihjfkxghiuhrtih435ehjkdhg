package com.toprate.erp.service;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysRoleUserDTO;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.model.SysUser;
import com.toprate.erp.rest.VM.LoginRequest;
import org.hibernate.SQLQuery;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface  UserService {
    List<SysUser> findAll();

    SysUserDTO insert(SysUserDTO sysUserDto) throws Exception;

    SysUserDTO update(SysUserDTO sysUserDto) throws Exception;

     SysUserDTO findByEmail(String email);

     SysUserDTO getUserFullObject(String email);

    DataListDTO doSearch(SearchDTO obj);

     void updateLoginFailCount(String email);

     void resetLoginFailCount(String email);

    void lock(SysUserDTO obj) throws Exception;

    void unlock(SysUserDTO obj) throws Exception;

     void updatePass(LoginRequest request) throws  Exception;

    LoginRequest resetPass(SysUserDTO sysUserDTO) throws Exception;

    List<SysRoleUserDTO> getListRoleByUserId(Long userId);

    void insertUserRoleData(SysUserDTO userDto) throws Exception;

     List<SysUserDTO>  getForAutocompleteUser(SearchDTO dto);

    List<SysUserDTO> getAllUser() throws Exception;
}
