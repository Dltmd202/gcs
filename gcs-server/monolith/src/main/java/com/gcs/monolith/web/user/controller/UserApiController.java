package com.gcs.monolith.web.user.controller;

import com.gcs.monolith.domain.user.service.UserService;
import com.gcs.monolith.web.user.dto.UserDetailResponse;
import com.gcs.monolith.web.user.dto.UserSignInRequest;
import com.gcs.monolith.web.user.dto.UserSignupRequest;
import com.gcs.monolith.web.user.dto.UserTokenResponse;
import com.gcs.monolith.web.user.security.service.JwtService;
import com.gcs.supporter.domain.security.JwtAuthentication;
import com.gcs.supporter.util.api.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<ApiUtil.ApiResult<UserDetailResponse>> getUserDetail(
            @AuthenticationPrincipal JwtAuthentication token
    ){
        // TODO webconfig에서 막아야 함
        return ResponseEntity.ok(
                ApiUtil.success(
                        UserDetailResponse.of(
                                userService.findUserById(token.getId())
                        )
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiUtil.ApiResult<Long>> join(
            @Valid @RequestBody UserSignupRequest request
    ){
        request.validateCheckedPassword();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiUtil.success(
                            userService.join(request.toEntity())
                        )
                );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiUtil.ApiResult<UserTokenResponse>> login(
            @Valid @RequestBody UserSignInRequest signInRequest
    ) {
        return ResponseEntity.ok(
                ApiUtil.success(
                        jwtService.issueUserTokenApiResponse(
                                signInRequest.getUsername(),
                                signInRequest.getPassword()
                        )
                )
        );
    }

    @GetMapping("/username")
    public ResponseEntity<com.gcs.supporter.util.api.ApiUtil.ApiResult<Boolean>> checkValidUsername(
            @RequestParam(value = "value", defaultValue = "") String username
    ){
        return ResponseEntity.ok(
                com.gcs.supporter.util.api.ApiUtil.success(
                        userService.isValidUsername(username)
                )
        );
    }
}
