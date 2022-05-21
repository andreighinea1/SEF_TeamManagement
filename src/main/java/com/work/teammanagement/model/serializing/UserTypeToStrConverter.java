package com.work.teammanagement.model.serializing;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.work.teammanagement.model.types.UserType;

public class UserTypeToStrConverter extends StdConverter<UserType, String> {
    @Override
    public String convert(UserType userType) {
        return userType.toString();
    }
}
