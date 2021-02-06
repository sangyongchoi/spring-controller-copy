package flow.exception;

import flow.exception.code.ErrorCode;
import flow.exception.code.ErrorType;

import static java.lang.String.format;

public class CoreBaseException extends RuntimeException {

    private static final String EXCEPTION_MSG_FORMAT = "ErrorType : %s, ErrorCode:%s ,userMsg : '%s'";
    private final ErrorCode errorCode;
    private final ErrorType errorType;

    public CoreBaseException(ErrorCode errorCode, ErrorType errorType) {
        super(format(EXCEPTION_MSG_FORMAT, errorType, errorCode, errorCode.getMessage()));
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public CoreBaseException(String message, ErrorCode errorCode, ErrorType errorType) {
        super(format(EXCEPTION_MSG_FORMAT, errorType, errorCode, message));
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public CoreBaseException(String message, Throwable cause, ErrorCode errorCode, ErrorType errorType) {
        super(format(EXCEPTION_MSG_FORMAT, errorType, errorCode, message), cause);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public CoreBaseException(Throwable cause, ErrorCode errorCode, ErrorType errorType) {
        super(format(EXCEPTION_MSG_FORMAT, errorType, errorCode, errorCode.getMessage()), cause);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }
}
