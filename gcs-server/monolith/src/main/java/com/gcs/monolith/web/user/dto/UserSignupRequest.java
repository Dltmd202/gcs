package com.gcs.monolith.web.user.dto;

import com.gcs.supporter.domain.user.entity.UserEntity;
import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.error.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequest {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "아이디를 입력해주세요")
    private String username;
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String password;

    @NotBlank(message = "비밀번호를 확인해주세요")
    private String checkPassword;

    public UserEntity toEntity(){
        return new UserEntity(
                name,
                username,
                password
        );
    }

    public void validateCheckedPassword(){
        if(!this.password.equals(this.checkPassword)){
            throw new ApiException(ErrorCode.CONFLICT_PASSWORD_ERROR);
        }
    }
}
