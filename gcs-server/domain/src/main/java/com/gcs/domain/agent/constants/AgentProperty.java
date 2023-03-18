package com.gcs.domain.agent.constants;

import lombok.Getter;

/**
 * AgentProperty는 비행물체가 가질 수 있는 속성의 열거형 데이터이다.
 * <br/>
 * 이 클래스는 에이전트가 가질 수 있는 속성의 요구사항에 따라 변경될 수 있다.
 * @author Dltmd202
 */
@Getter
public enum AgentProperty {
    SYSID("sysid"),
    MODE("mode"),
    TYPE("type"),
    IP("ip"),
    GROUP("group"),
    VEHICLE("vehicle"),

    PORT("port");

    private String var;

    AgentProperty(String var) {
        this.var = var;
    }
}
