package com.work.teammanagement.model.requests.employee.serializing;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.work.teammanagement.model.requests.employee.ApprovalStatus;

public class ApprovalStatusFromStrConverter extends StdConverter<String, ApprovalStatus> {
    @Override
    public ApprovalStatus convert(String approvedStatusName) {
        return ApprovalStatus.valueOf(approvedStatusName);
    }
}
