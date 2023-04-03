package com.gcs.api.web.user.security;

import com.gcs.api.domain.user.service.UserService;
import com.gcs.supporter.domain.security.JwtAuthentication;
import com.gcs.supporter.domain.security.JwtAuthenticationToken;
import com.gcs.supporter.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.ClassUtils;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        return processUserAuthentication(
                String.valueOf(authenticationToken.getPrincipal()),
                authenticationToken.getCredentials());
    }

    private Authentication processUserAuthentication(String username, String password){
        try {
            UserEntity user = userService.login(username, password);
            JwtAuthenticationToken authenticated =
                    new JwtAuthenticationToken(
                            new JwtAuthentication(user.getId(), user.getName()),
                            null,
                            AuthorityUtils.createAuthorityList(user.getRoles().getValue())
                    );
            authenticated.setDetails(user);
            return authenticated;
        } catch (DataAccessException e){
            return JwtAuthenticationToken.GUEST;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ClassUtils.isAssignable(JwtAuthenticationToken.class, authentication);
    }
}
