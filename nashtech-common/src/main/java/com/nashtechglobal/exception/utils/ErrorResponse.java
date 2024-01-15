package com.nashtechglobal.exception.utils;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    /**
     * The error code associated with the error response.
     */
    @JsonProperty("error_code")
    private int errorCode;
    /**
     * The error status associated with the error response.
     */
    @JsonProperty("status")
    private ErrorStatus status;
    /**
     * The time stamp when the error occurred.
     */
    @JsonProperty("timeStamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;
    /**
     * The details associated with the error response.
     */
    @JsonProperty("details")
    private String details;
    /**
     * Constructs a new ErrorResponse instance with the specified
     * error code, error status, time stamp, and details.
     *
     * @param tempErrorCode the error code associated with the error response.
     * @param tempStatus the error status associated with the error response.
     * @param tempTimeStamp the time stamp when the error occurred.
     * @param tempDetails the details associated with the error response.
     */
    public ErrorResponse(final int tempErrorCode,
                         final ErrorStatus tempStatus,
                         final LocalDateTime tempTimeStamp,
                         final String tempDetails) {
        this.errorCode = tempErrorCode;
        this.status = tempStatus;
        this.timeStamp = tempTimeStamp;
        this.details = tempDetails;
    }

}
