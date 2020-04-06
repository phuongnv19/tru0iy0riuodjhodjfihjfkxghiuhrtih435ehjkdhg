package com.toprate.erp.service.impl;

import com.toprate.erp.base.dto.DataListDTO;
import com.toprate.erp.base.dto.ErpBaseDTO;
import com.toprate.erp.base.dto.SearchDTO;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.dto.SysRoleUserDTO;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.SysObjects;
import com.toprate.erp.model.SysUser;
import com.toprate.erp.repositories.ObjectRepository;
import com.toprate.erp.repositories.UserRepository;
import com.toprate.erp.rest.VM.LoginRequest;
import com.toprate.erp.service.UserService;
import com.toprate.erp.util.MessageUtils;
import com.toprate.erp.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Override
    public List<SysUser> findAll(){
        return userRepository.findAll();
    }

    @Override
    public SysUserDTO insert(SysUserDTO sysUserDto) throws Exception{
        if(!checkEmail(sysUserDto.getEmail(), null)){
            throw new BusinessException(MessageUtils.getMessage("dublicate.email"));
        }
        sysUserDto.setStatus(1l);
        sysUserDto.setCreateDate(new Date());
//        sysUserDto.setCreateUserId(authenticationServiceImpl.getCurrentUser().getUserId().toString());
            sysUserDto.setPassword(passwordEncoder.encode(sysUserDto.getPassword()));
       Long id=  userRepository.insert(sysUserDto.toModel());
        sysUserDto.setUserId(id);
        return sysUserDto;
    }

    @Override
    public SysUserDTO update(SysUserDTO sysUserDto) throws Exception{
        if(!checkEmail(sysUserDto.getEmail(), sysUserDto.getUserId())){
            throw new BusinessException(MessageUtils.getMessage("dublicate.email"));
        }
        if(null==sysUserDto.getUserId()){
            throw new BusinessException(MessageUtils.getMessage("user.not.found"));
        }

        SysUserDTO dtoOld=userRepository.getUserById(sysUserDto.getUserId());

        if(null==dtoOld){
            throw new BusinessException(MessageUtils.getMessage("user.not.found"));
        }
        dtoOld.setCheckIp(sysUserDto.getCheckIp());
        dtoOld.setDateOfBirth(sysUserDto.getDateOfBirth());
        dtoOld.setDescription(sysUserDto.getDescription());
        dtoOld.setEmail(sysUserDto.getEmail());
        dtoOld.setFirstName(sysUserDto.getFirstName());
        dtoOld.setGender(sysUserDto.getGender());
        dtoOld.setHomePhone(sysUserDto.getHomePhone());
        dtoOld.setIdentityCode(sysUserDto.getIdentityCode());
        dtoOld.setIdentityType(sysUserDto.getIdentityType());
        dtoOld.setIpAddress(sysUserDto.getIpAddress());
        dtoOld.setKeyFobId(sysUserDto.getKeyFobId());
        dtoOld.setLastName(sysUserDto.getLastName());
        dtoOld.setLocationId(sysUserDto.getLocationId());
        dtoOld.setMobilePhone(sysUserDto.getMobilePhone());
        dtoOld.setValidFrom(sysUserDto.getValidFrom());
        dtoOld.setValidTo(sysUserDto.getValidTo());
//        sysUserDto.setPassword(passwordEncoder.encode(sysUserDto.getPassword()));
         userRepository.update(dtoOld.toModel());
        return dtoOld;
    }

    private Boolean checkEmail(String email, Long appParamId) {
        SysUser obj = userRepository.findByFiled("email",email);

        if (appParamId == null) {
            if (obj == null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (obj == null) {
                return true;
            } else if (obj != null && obj.getUserId().longValue() == appParamId) {
                return true;
            } else {
                return false;
            }
        }

    }

    @Override
    public SysUserDTO findByEmail(String email){
        return userRepository.findByFiled("email",email).toDto();
    }


    @Override
    public SysUserDTO getUserFullObject(String email){
        SysUserDTO userDTO=userRepository.findByFiled("email",email).toDto();
        List<SysObjectsDTO> listObjParent=objectRepository.getListObjects(userDTO.getUserId(), null);

        for(SysObjectsDTO objParent:listObjParent){
            List<SysObjectsDTO> listObjChild=objectRepository.getListObjects(userDTO.getUserId(), objParent.getObjectId());
            objParent.setListChildObject(listObjChild);
        }

        userDTO.setSysObjectsList(listObjParent);
        return userDTO;
    }
    @Override
    public DataListDTO doSearch(SearchDTO obj) {
        List<SysUserDTO> ls = userRepository.doSearch(obj);
        DataListDTO data = new DataListDTO();
        data.setData(ls);
        data.setTotal(obj.getTotalRecord());
        data.setSize(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }

    @Override
    public void updateLoginFailCount(String email)  {
        userRepository.updateLoginFailCount(email);
    }

    @Override
    public void resetLoginFailCount(String email) {
        userRepository.resetLoginFailCount(email);
    }



    @Override
    public void updatePass(LoginRequest loginRequest) throws Exception{
        if(StringUtils.isNotEmpty(loginRequest.getEmail())
                && StringUtils.isNotEmpty(loginRequest.getPassword())
                && StringUtils.isNotEmpty(loginRequest.getPasswordConfirm())
                && StringUtils.isNotEmpty(loginRequest.getPasswordOld())){
            SysUserDTO usersDTO=userRepository.getUserInfo(loginRequest.getEmail());
            if(null==usersDTO){
                throw new BusinessException(MessageUtils.getMessage("invalid.account"));
            }

            if(!passwordEncoder.matches(loginRequest.getPasswordOld(), usersDTO.getPassword())){
                throw new BusinessException(MessageUtils.getMessage("invalid.account"));
            }
            if(loginRequest.getPassword().equals(loginRequest.getPasswordOld())){
                throw new BusinessException(MessageUtils.getMessage("validate.pass.same.oldpass"));
            }

            if(!loginRequest.getPassword().equals(loginRequest.getPasswordConfirm())){
                throw new BusinessException(MessageUtils.getMessage("validate.pass.not.same.passConfirm"));
            }

            String hashPass=passwordEncoder.encode(loginRequest.getPassword());

            userRepository.updatePass(loginRequest.getEmail(), hashPass);


        } else {
            throw new BusinessException(MessageUtils.getMessage("validate.data.empty"));
        }

    }

    @Override
    public LoginRequest resetPass(SysUserDTO sysUserDTO) throws Exception{
        LoginRequest loginDTO= new LoginRequest();
        String pass= PasswordUtil.generateRandomString(8, 3);
        String hashPass=passwordEncoder.encode(pass);
        userRepository.resetPass(sysUserDTO.getUserId(), hashPass);
//        SysUserDTO newObj=sysUserDTO;
//        newObj.setPassword(hashPass);
//        actionAuditBusinessImpl.trackingAdjustment(Constants.ACTION_AUDIT_TYPE.INSERT,TABLE_NAME, usersDTO.getUserId(), newObj, usersDTO, QlanResource.get(DISPLAY_NAME_KEY), request);
        loginDTO.setPassword(pass);
        return loginDTO;
    }
    @Override
    @Transactional
    public void lock(SysUserDTO obj) throws Exception{
            SysUser objOld = userRepository.find(obj.getUserId());
            if(null==objOld){
                throw new BusinessException("");
            }
            userRepository.lockAccount(obj.getUserId());
//            actionAuditBusinessImpl.trackingAdjustment(Constants.ACTION_AUDIT_TYPE.LOCK,TABLE_NAME, obj.getUserId(), obj, objOld, QlanResource.get(DISPLAY_NAME_KEY), request);
    }
    @Override
    @Transactional
    public void unlock(SysUserDTO obj) throws Exception{
        SysUser objOld = userRepository.find(obj.getUserId());
            if(null==objOld){
                throw new BusinessException("QlanResource.get(ACCOUNT_VI_KEY)+obj.getUserName()+QlanResource.get(INVALID_VI_KEY)");
            }
            userRepository.unlockAccount(obj.getUserId());
//            actionAuditBusinessImpl.trackingAdjustment(Constants.ACTION_AUDIT_TYPE.UNLOCK,TABLE_NAME, obj.getUserId(), obj, objOld, QlanResource.get(DISPLAY_NAME_KEY), request);
    }
    @Override
    public List<SysRoleUserDTO> getListRoleByUserId(Long userId){
        return userRepository.getListRoleByUserId(userId);
    }

    @Override
    @Transactional
    public void insertUserRoleData(SysUserDTO userDto) throws Exception {
        userRepository.deleteByUserId(userDto.getUserId());
        if(userDto.getSysRoleList().size()>0){
            for(SysRoleUserDTO roleUser:userDto.getSysRoleList()){
                userRepository.insertToUserRole(roleUser,userDto);
            }
        }
    }

    @Override
    public  List<SysUserDTO> getForAutocompleteUser(SearchDTO dto) {
        List<SysUserDTO> list = userRepository.autoCompleteSearch(dto);

        return list;
    }

    @Override
    public List<SysUserDTO> getAllUser() throws Exception {
        List<SysUserDTO> listSysUser = userRepository.getSysUser();
        if (listSysUser == null || listSysUser.isEmpty()) {
            throw new BusinessException(MessageUtils.getMessage("User.data.empty"));
        }

        return listSysUser;
    }


}
