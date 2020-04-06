package com.toprate.erp.authen;


import com.toprate.erp.base.dto.CustomUserDetails;
import com.toprate.erp.dto.SysObjectsDTO;
import com.toprate.erp.dto.SysUserDTO;
import com.toprate.erp.exception.BusinessException;
import com.toprate.erp.model.ActionAudit;
import com.toprate.erp.model.AuditDetail;
import com.toprate.erp.repositories.ActionAuditRepository;
import com.toprate.erp.repositories.AuditDetailRepository;
import com.toprate.erp.repositories.ObjectRepository;
import com.toprate.erp.repositories.UserRepository;
import com.toprate.erp.rest.VM.LoginRequest;
import com.toprate.erp.service.impl.UserServiceImpl;
import com.toprate.erp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailService;
     @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private ActionAuditRepository actionAuditRepository;

    @Autowired
    private AuditDetailRepository auditDetailRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // sử dụng userDetailService để lấy thông tin user
        LoginRequest loginRequest = (LoginRequest) authentication.getPrincipal();
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetailService.loadUserByUsername(loginRequest.getEmail());
        SysUserDTO userDTO = customUserDetails.getUser();
        Assert.notNull(userDTO, MessageUtils.getMessage("authentication.is-fail"));

        // logic xac thuc user
        UsernamePasswordAuthenticationToken result = null;

        if (userDTO.getLastLockDate()!=null && userDTO.getLastLockDate().before(new Date()) && 0l == userDTO.getStatus()) {
            throw new BusinessException(MessageUtils.getMessage("account.is.lock"));
        }

        if(userDTO.getLoginFailureCount() != null && userDTO.getLoginFailureCount() >= 5){
            throw new BusinessException(MessageUtils.getMessage("account.is.lock"));
        }

        if (userDTO.getEmail().equals(loginRequest.getEmail()) && passwordEncoder.matches(authentication.getCredentials().toString(), customUserDetails.getPassword())) {
            List<SysObjectsDTO> listObjParent=objectRepository.getListObjects(userDTO.getUserId(), null);

            for(SysObjectsDTO objParent:listObjParent){
                List<SysObjectsDTO> listObjChild=objectRepository.getListObjects(userDTO.getUserId(), objParent.getObjectId());
                objParent.setListChildObject(listObjChild);
            }
            userDTO.setSysObjectsList(listObjParent);
            customUserDetails.setUser(userDTO);
            String jwt = tokenProvider.generateToken(customUserDetails);
            customUserDetails.setJwt(jwt);
            result = new UsernamePasswordAuthenticationToken(customUserDetails, customUserDetails.getPassword(), customUserDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(result);
        } else {
            if (userDTO.getLoginFailureCount() == null || userDTO.getLoginFailureCount() < 4) {
                userRepository.updateLoginFailCount(userDTO.getEmail());
                throw new BusinessException(MessageUtils.getMessage("authentication.is-fail"));
            } else {
                userRepository.updateLoginFailCount(userDTO.getEmail());
                userRepository.lockAccount(userDTO.getUserId());


                ActionAudit actionAudit = new ActionAudit();
                actionAudit.setDescription(MessageUtils.getMessage("lock.account.login.fail"));
//                actionAudit.setUserName(userName);
                actionAudit.setAuditTypeId(1l);
                actionAudit.setCreateDate(new Date());
                actionAudit.setSourceId(userDTO.getUserId());
//                actionAudit.setServerIp(actionAuditBusinessImpl.getIpServer());
                Long auditId = actionAuditRepository.insert(actionAudit);

                AuditDetail auditDetail = new AuditDetail();

                auditDetail.setAuditId(auditId);
                auditDetail.setColName("STATUS");
                auditDetail.setOldName("1");
                auditDetail.setNewName("0");
//                auditDetail.set("user");

                auditDetailRepository.insert(auditDetail);
                throw new BusinessException(MessageUtils.getMessage("authentication.is-fail"));
            }
        }
        return result;
    }

    public void setUserDetailService(UserDetailsService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
