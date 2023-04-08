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
//@Entity
public class AgentEntity implements AgentConfigureable {
    private Long id;
    private Integer sysid;
    private String mode;
    private String type;
    private Integer group;
    private String vehicle;
    private String ip;
    private Integer port;

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public Integer getSysid() {
        return null;
    }

    @Override
    public String getMode() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public Integer getGroup() {
        return null;
    }

    @Override
    public String getVehicle() {
        return null;
    }

    @Override
    public String getIp() {
        return null;
    }

    @Override
    public Integer getPort() {
        return null;
    }
}
