package com.toprate.erp.authen;

import com.toprate.erp.base.dto.CustomUserDetails;
import com.toprate.erp.model.SysUser;
import com.toprate.erp.service.impl.AuthenticationServiceImpl;
import com.toprate.erp.service.impl.UserDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String WHITE_LIST = "/v1/login,/v1/logout,/v1/changePassword";
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Autowired
    private UserDetailServiceImpl customUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getServletPath();

        // Lấy jwt từ request
        if(!WHITE_LIST.contains(path)) {
            String jwt = tokenProvider.getJwtFromRequest(request);
//            CustomUserDetails currentUser = authenticationServiceImpl.getCustomUserDetails();
            CustomUserDetails currentUser= new CustomUserDetails(null,null);
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt, currentUser)) {
                // Lấy id user từ chuỗi jwt
                String userName = tokenProvider.getEmailFromJWT(jwt);
                // Lấy thông tin người dùng từ id
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

                if (userDetails != null) {
                    CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
//                    String refreshToken = tokenProvider.generateToken(customUserDetails);
//                    customUserDetails.setJwt(refreshToken);
                    // Nếu người dùng hợp lệ, set thông tin cho Seturity Context
                    UsernamePasswordAuthenticationToken
                            authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    request.setAttribute("USER_INFO", customUserDetails.getUser());
//                    response.setHeader(TokenProvider.AUTHORIZATION_HEADER, "Bearer " + refreshToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}