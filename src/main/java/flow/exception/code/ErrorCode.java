package flow.exception.code;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum ErrorCode {
    ERROR_HANDLER_CREATE(500, "1000", "핸들러 생성중 오류가 발생하였습니다."),
    ERROR_HANDLER_NOT_FOUND(500, "2000", "요청하신 경로와 일치하는 경로가 존재하지 않습니다."),
    UNKNOWN(500, "9999", "정의되지 않은 에러입니다."),
    ;

    private static final Map<String, ErrorCode> codeMap;

    static {
        codeMap = Stream.of(values())
                .collect(toMap(ErrorCode::getCode, e -> e));
    }

    @Getter
    public final int httpStatus;
    @Getter
    public final String code;
    @Getter
    public final String message;

    ErrorCode(int httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public static ErrorCode codeOf(String code) {
        return codeMap.get(code);
    }
}
