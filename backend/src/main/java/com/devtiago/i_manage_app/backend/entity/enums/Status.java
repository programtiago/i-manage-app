package com.devtiago.i_manage_app.backend.entity.enums;

public enum Status {
    ACTIVE,
    /**
     * Employee has left the company voluntarily (e.g., resignation).
     */
    RESIGNED,
    /**
     * Employee is temporarily on leave (e.g., medical, vacation, parental).
     */
    ON_LEAVE,
    /**
     * Employee is inactive but still in the system (e.g., for record retention or rehire).
     */
    INACTIVE,
    /**
     * Employee contract was terminated by the company.
     */
    TERMINATED
}
