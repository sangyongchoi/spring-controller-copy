package flow.exception;

import flow.exception.code.ErrorCode;
import flow.exception.code.ErrorType;

public class NotFoundException extends CoreBaseException{

    private static final ErrorType ERROR_TYPE = ErrorType.NOT_FOUND_HANDLER;

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode, ERROR_TYPE);
    }

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode, ERROR_TYPE);
    }

    public NotFoundException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode, ERROR_TYPE);
    }

    public NotFoundException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode, ERROR_TYPE);
    }

}
