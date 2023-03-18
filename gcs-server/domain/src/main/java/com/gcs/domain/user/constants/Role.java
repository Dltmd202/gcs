package com.gcs.domain.user.constants;

import lombok.Getter;

@Getter
public enum Role {
    USER("USER"),
    STAFF("STAFF"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN"),
    GUEST("GUEST");
    private final String value;

    Role(String value){
        this.value = value;
    }

    public static Role of(String name){
        for (Role role : Role.values()) {
            if(role.name().equalsIgnoreCase(name))
                return role;
        }
        return null;
    }
}
