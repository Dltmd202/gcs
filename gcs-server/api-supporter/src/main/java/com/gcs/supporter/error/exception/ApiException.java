package com.gcs.supporter.error.exception;


import com.gcs.error.exception.GcsException;

public class ApiException extends GcsException {

    private ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode, String location) {
        super(location + ", " + errorCode.getDetail());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return errorCode.getDetail();
    }


    public int getStatus(){
        return errorCode.getStatus();
    }
}
