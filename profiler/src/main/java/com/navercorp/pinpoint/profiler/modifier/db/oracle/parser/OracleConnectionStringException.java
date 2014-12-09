package com.navercorp.pinpoint.profiler.modifier.db.oracle.parser;

import com.navercorp.pinpoint.exception.PinpointException;

/**
 * @author emeroad
 */
public class OracleConnectionStringException extends PinpointException {

    public OracleConnectionStringException() {
    }

    public OracleConnectionStringException(String message) {
        super(message);
    }

    public OracleConnectionStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public OracleConnectionStringException(Throwable cause) {
        super(cause);
    }
}
