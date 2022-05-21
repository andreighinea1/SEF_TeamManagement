package com.work.teammanagement.model.serializing;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.work.teammanagement.model.types.UserRole;

public class UserRoleFromStrConverter extends StdConverter<String, UserRole> {
    @Override
    public UserRole convert(String userRoleName) {
        return UserRole.valueOf(userRoleName);
    }
}
