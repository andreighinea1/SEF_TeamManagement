package com.work.teammanagement.model.serializing;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.work.teammanagement.model.requests.ApprovalStatus;

public class ApprovedStatusToStrConverter extends StdConverter<ApprovalStatus, String> {
    @Override
    public String convert(ApprovalStatus approvalStatus) {
        return approvalStatus.toString();
    }
}
