package flow.exception;

import flow.exception.code.ErrorCode;
import flow.exception.code.ErrorType;

public class CoreBaseException extends RuntimeException {

    private static String EXCEPTION_MSG_FORMAT = "ErrorType : %s, ErrorCode:%s,userMsg : '%s'";
    private ErrorCode errorCode;
    private ErrorType errorType;

    public CoreBaseException(ErrorCode errorCode, ErrorType errorType) {
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public CoreBaseException(String message, ErrorCode errorCode, ErrorType errorType) {
        super(message);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public CoreBaseException(String message, Throwable cause, ErrorCode errorCode, ErrorType errorType) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public CoreBaseException(Throwable cause, ErrorCode errorCode, ErrorType errorType) {
        super(cause);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public CoreBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode, ErrorType errorType) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }
}
