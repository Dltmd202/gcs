package com.gcs.domain.user;

import com.gcs.domain.user.constants.Role;

public interface User {
    Long getId();
    String getName();
    String getUsername();
    Role getRoles();

}
