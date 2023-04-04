package com.gcs.supporter.domain.context.entity;

import com.gcs.supporter.domain.common.BaseTimeEntity;
import com.gcs.supporter.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "scenario")
@NoArgsConstructor(access = PROTECTED)
public class AgentContextEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "scenario_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String uploadFileName;

    private String storeFileName;

    public AgentContextEntity(UserEntity user, String uploadFileName, String storeFileName) {
        this.user = user;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

}
