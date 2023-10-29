package com.haytech.demo_referralmanagement.model.enums;

public enum ReferrType {
    COMPLETION_OF_THE_COURSE("Completion of the course"),
    COMPLETE_COVERAGE("Complete coverage"),
    WRONG_ENTRY_OF_PERSONNEL_CODE("Wrong entry of personnel code");

    private String description;

    ReferrType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
