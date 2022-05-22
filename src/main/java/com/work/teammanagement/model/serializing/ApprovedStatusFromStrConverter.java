package com.work.teammanagement.model.serializing;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.work.teammanagement.model.requests.ApprovalStatus;

public class ApprovedStatusFromStrConverter extends StdConverter<String, ApprovalStatus> {
    @Override
    public ApprovalStatus convert(String approvedStatusName) {
        return ApprovalStatus.valueOf(approvedStatusName);
    }
}
