package com.gcs.supporter.mavlink.error;

import com.gcs.error.exception.GcsException;

public class MavLinkGcsException extends GcsException {
    public MavLinkGcsException(String message) {
        super(message);
    }
}
