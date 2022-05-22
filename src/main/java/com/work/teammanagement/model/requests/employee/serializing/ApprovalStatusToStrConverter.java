package com.work.teammanagement.model.requests.employee.serializing;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.work.teammanagement.model.requests.employee.ApprovalStatus;

public class ApprovalStatusToStrConverter extends StdConverter<ApprovalStatus, String> {
    @Override
    public String convert(ApprovalStatus approvalStatus) {
        return approvalStatus.toString();
    }
}
