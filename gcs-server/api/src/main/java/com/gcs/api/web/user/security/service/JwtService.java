package com.gcs.api.web.user.security.service;

import com.gcs.api.web.user.dto.UserTokenResponse;
import com.gcs.supporter.domain.security.Jwt;
import com.gcs.supporter.domain.security.JwtAuthentication;
import com.gcs.supporter.domain.security.JwtAuthenticationToken;
import com.gcs.supporter.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

/**
 * JwtTokenProvider를 통한 인증 모듈 계층
 * <br/>
 * 해당 서비스 계층은 pricipal과 credential을 부여할때 User, 또는 Guest에 대한 토큰을 빌행한다.
 * <br/>
 * 발행받을 수 있는 유형은 토큰 값, 토큰이 닮긴 쿠키, Api 응답객체가 있다.
 *
 * @author  Dltmd202
 */
@Service
@RequiredArgsConstructor
public class JwtService {

    private final Jwt jwt;
    private final AuthenticationManager authenticationManager;

    public UserTokenResponse issueUserTokenApiResponse(String principal, String credential){
        Authentication authentication = authenticateUser(principal, credential);
        Class<?> aClass = authentication.getDetails().getClass();
        System.out.println(aClass.toString());
        UserEntity user = (UserEntity) authentication.getDetails();
        String token = createJwt(
                user,
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toArray(String[]::new)
        );
        return new UserTokenResponse(user.getId(), token, user.getRoles().getValue());
    }

    /**
     * 아이디 비밀번호를 통해 Servlet 계층에서 사용가능한 쿠키를 반환
     *
     * @param principal 아이디
     * @param credential 비밀번호
     * @return javax.servlet.http.Cookie
     */
    public Cookie issueUserTokenCookie(String principal, String credential){
        return makeTokenCookie(
                issueUserToken(principal, credential)
        );
    }

    /**
     * 게스트 접근을 처리하며 Servlet 계층에서 사용가능한 쿠키를 반환
     *
     * @return javax.servlet.http.Cookie
     */
    public Cookie issueGuestTokenCookie(){
        return makeTokenCookie(
                issueGuestToken()
        );

    }


    public String issueUserToken(String principal, String credential){
        Authentication authentication = authenticateUser(principal, credential);
        return getToken(authentication);
    }

    public String issueGuestToken() {
        Authentication authentication = JwtAuthenticationToken.getGuestToken();
        return getToken(authentication);
    }

    private String getToken(Authentication authentication) {
        if(authentication.getDetails() instanceof UserEntity) {
            UserEntity user = (UserEntity) authentication.getDetails();
            return createJwt(
                    user,
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .toArray(String[]::new)
            );
        }

        return createGuestJwt(
                ((JwtAuthentication) authentication.getPrincipal()).getId(),
                authentication.getName(),
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toArray(String[]::new)
                );
    }


    private Cookie makeTokenCookie(String token){
        // TODO token 키 값은 Configuration에서 관리해야함
        Cookie tokenCookie = new Cookie("token", token);
        return tokenCookie;
    }

    /**
     * 아이디, 비밀번호 인증을 통한 인증 발행
     *
     * @param principal 아이디
     * @param credential 비밀번호
     * @return Authentication
     * @throws org.springframework.security.core.AuthenticationException 핸들링 점검할 것
     * @author Dltmd202
     */
    private Authentication authenticateUser(String principal, String credential){
        return authenticationManager.authenticate(
                new JwtAuthenticationToken(principal, credential)
        );
    }

    /**
     * Guest 인증 발행
     *
     * @return org.springframework.security.core.Authentication
     * @author Dltmd202
     */
    private Authentication authenticateGuest(){
        return authenticationManager.authenticate(JwtAuthenticationToken.GUEST);
    }

    private String createJwt(UserEntity user, String[] roles){
        Jwt.Claims claims = Jwt.Claims.of(user.getId(), user.getName(), roles);
        return jwt.create(claims);
    }

    private String createGuestJwt(Long id, String name, String[] roles){
        Jwt.Claims claims = Jwt.Claims.of(id, name, roles);
        return jwt.create(claims);
    }
}
