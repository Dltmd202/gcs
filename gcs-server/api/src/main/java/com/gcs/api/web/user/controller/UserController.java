package com.gcs.api.web.user.controller;

import com.gcs.api.domain.user.service.UserService;
import com.gcs.api.web.user.dto.UserSignInRequest;
import com.gcs.api.web.user.dto.UserSignupRequest;
import com.gcs.api.web.user.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/new")
    public String createForm(@ModelAttribute("userSignupRequest") UserSignupRequest request){
        return "page/user/createUserForm";
    }

    @PostMapping("/new")
    public String create(
            @Valid @ModelAttribute UserSignupRequest signupRequest,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return "page/user/createUserForm";
        }

        signupRequest.validateCheckedPassword();
        Long id = userService.join(signupRequest.toEntity());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") UserSignInRequest signInRequest){
        return "page/user/loginForm";
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute UserSignInRequest signInRequest,
            BindingResult bindingResult,
            HttpServletResponse response
    ) {
        if(bindingResult.hasErrors()){
            return "page/user/loginForm";
        }

        response.addCookie(
            jwtService.issueUserTokenCookie(
                    signInRequest.getUsername(),
                    signInRequest.getPassword()
            )
        );

        return "redirect:/";
    }
}
