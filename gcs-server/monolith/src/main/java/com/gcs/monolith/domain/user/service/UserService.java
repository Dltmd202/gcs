package com.gcs.monolith.domain.user.service;

import com.gcs.monolith.domain.user.repository.UserRepository;
import com.gcs.supporter.domain.user.entity.UserEntity;
import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public Long join(UserEntity user){
        isDuplicatedUser(user);
        user.setEncryptedPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    public UserEntity  login(String username, String password){
        // TODO 계정 없을 때 상황이랑 비밀번호 틀렸을 때 구분
        UserEntity user = findUserByUsername(username);

        validateUserPassword(user, password);

        return user;
    }

    private void validateUserPassword(UserEntity user, String password){
        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new ApiException(ErrorCode.INVALID_PASSWORD);
    }

    private UserEntity findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
    }

    public UserEntity findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
    }

    private void isDuplicatedUser(UserEntity user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new ApiException(ErrorCode.CONFLICT_USERNAME);
        }
    }

    public boolean isValidUsername(String username){
        return !userRepository.existsByUsername(username);
    }
}
