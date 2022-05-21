package com.work.teammanagement.model.serializing;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.work.teammanagement.model.types.UserType;

public class UserTypeFromStrConverter extends StdConverter<String, UserType> {
    @Override
    public UserType convert(String userTypeName) {
        return UserType.valueOf(userTypeName);
    }
}
