package com.gcs.supporter.domain.user.entity;

import com.gcs.domain.user.User;
import com.gcs.domain.user.constants.Role;
import com.gcs.supporter.domain.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseTimeEntity implements User {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role roles = Role.USER;

    public UserEntity(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }


    public void setEncryptedPassword(String password){
        this.password = password;
    }
}
