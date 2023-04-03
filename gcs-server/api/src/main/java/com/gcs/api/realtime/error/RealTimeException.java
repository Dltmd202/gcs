package com.gcs.api.realtime.error;

import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.error.exception.ErrorCode;

public class RealTimeException extends ApiException {
    public RealTimeException(ErrorCode errorCode) {
        super(errorCode);
    }
}