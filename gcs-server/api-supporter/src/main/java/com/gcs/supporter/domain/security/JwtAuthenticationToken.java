package com.gcs.supporter.domain.security;

import com.gcs.domain.user.constants.Role;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    public static final Authentication GUEST = new GuestToken();
    private final Object principal;
    private String credentials;

    public static Authentication getGuestToken() {
        return new GuestToken();
    }


    public JwtAuthenticationToken(Object principal, String credentials){
        super(null);
        super.setAuthenticated(false);

        this.principal = principal;
        this.credentials = credentials;
    }

    public JwtAuthenticationToken(
            Object principal,
            String credentials,
            Collection<? extends GrantedAuthority> authorities
    ){
        super(authorities);
        super.setAuthenticated(true);

        this.principal = principal;
        this.credentials = credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public String getCredentials() {
        return credentials;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException{
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

    private static class GuestToken implements Authentication {

        private static final String SPLITTER = ":";
        private static Long seq = 0L;

        private final Object principal;

        public GuestToken(){
            this.principal = new JwtAuthentication(++seq, "guest");
        }


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singleton(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return Role.GUEST.name();
                }
            });
        }

        @Override
        public Object getCredentials() {
            return Optional.empty();
        }

        @Override
        public Object getDetails() {
            return Optional.empty();
        }

        @Override
        public Object getPrincipal() {
            return principal;
        }

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException { }

        @Override
        public String getName() {
            return "guest" + ((JwtAuthentication)principal).getId();
        }
    }
}
