package com.work.teammanagement.model.serializing;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.work.teammanagement.model.users.UserRole;

public class UserRoleToStrConverter extends StdConverter<UserRole, String> {
    @Override
    public String convert(UserRole role) {
        return role.toString();
    }
}
