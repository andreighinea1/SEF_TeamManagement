package com.work.teammanagement.model.users.serializing;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.work.teammanagement.model.users.UserRole;

public class UserRoleFromStrConverter extends StdConverter<String, UserRole> {
    @Override
    public UserRole convert(String userRoleName) {
        return UserRole.valueOf(userRoleName);
    }
}
