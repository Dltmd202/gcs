package com.gcs.supporter.domain.agent.entity;

import com.gcs.domain.agent.AgentConfigureable;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Agent는 agent 데이터를 RDB에 영속시키기 위한 매핑 클래스이다.
 * @author Dltmd202
 */
@Getter
//@Entity
public class AgentEntity implements AgentConfigureable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer sysid;
    private String mode;
    private String type;
    private Integer group;
    private String vehicle;
    private String ip;
    private Integer port;
}
