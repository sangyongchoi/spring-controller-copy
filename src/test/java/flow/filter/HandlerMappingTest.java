package flow.filter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HandlerMappingTest {

    ObjectMapper objectMapper;
    {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }

    @Test
    @DisplayName("파라미터 변환 테스트 실패 케이스 Json -> String")
    public void parameter_Convert_Test_fail_wrong_Right_Curly_Bracket_json2String() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> {
            String json = "{\"123\"";
            boolean isStart = json.startsWith("{");
            boolean isEnd = json.endsWith("}");

            if (isStart && isEnd) {
                json = json.substring(1, json.length() - 1);
                boolean isStringStart = json.startsWith("\"");
                boolean isStringEnd = json.endsWith("\"");

                if (isStringStart && isStringEnd) {
                    json = json.substring(1, json.length() - 1);
                } else {
                    throw new IllegalArgumentException("");
                }
            } else {
                throw new IllegalArgumentException("");
            }
        });
    }

    @Test
    @DisplayName("파라미터 변환 테스트 실패 케이스 Json -> String")
    public void parameter_Convert_Test_fail_wrong_Left_Curly_Bracket_json2String() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> {
            String json = "\"123\"}";
            boolean isStart = json.startsWith("{");
            boolean isEnd = json.endsWith("}");

            if (isStart && isEnd) {
                json = json.substring(1, json.length() - 1);
                boolean isStringStart = json.startsWith("\"");
                boolean isStringEnd = json.endsWith("\"");

                if (isStringStart && isStringEnd) {
                    json = json.substring(1, json.length() - 1);
                } else {
                    throw new IllegalArgumentException("");
                }
            } else {
                throw new IllegalArgumentException("");
            }
        });
    }

    @Test
    @DisplayName("파라미터 변환 테스트 실패 케이스 Json -> String")
    public void parameter_Convert_Test_fail_wrong_Right_Quotation_mark_json2String() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> {
            String json = "{\"123}";
            boolean isStart = json.startsWith("{");
            boolean isEnd = json.endsWith("}");

            if (isStart && isEnd) {
                json = json.substring(1, json.length() - 1);
                boolean isStringStart = json.startsWith("\"");
                boolean isStringEnd = json.endsWith("\"");

                if (isStringStart && isStringEnd) {
                    json = json.substring(1, json.length() - 1);
                } else {
                    throw new IllegalArgumentException("");
                }
            } else {
                throw new IllegalArgumentException("");
            }
        });
    }

    @Test
    @DisplayName("파라미터 변환 테스트 실패 케이스 Json -> String")
    public void parameter_Convert_Test_fail_wrong_Left_Quotation_mark_json2String() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> {
            String json = "{123\"}";
            boolean isStart = json.startsWith("{");
            boolean isEnd = json.endsWith("}");

            if (isStart && isEnd) {
                json = json.substring(1, json.length() - 1);
                boolean isStringStart = json.startsWith("\"");
                boolean isStringEnd = json.endsWith("\"");

                if (isStringStart && isStringEnd) {
                    json = json.substring(1, json.length() - 1);
                } else {
                    throw new IllegalArgumentException("");
                }
            } else {
                throw new IllegalArgumentException("");
            }
        });
    }

    @Test
    @DisplayName("파라미터 변환 테스트 성공 케이스 Json -> String")
    public void parameter_Convert_Test_success_json2String() throws Exception{
        String json = "{\"123\"}";
        boolean isStart = json.startsWith("{");
        boolean isEnd = json.endsWith("}");

        if(isStart && isEnd){
            json = json.substring(1, json.length() - 1);
            boolean isStringStart = json.startsWith("\"");
            boolean isStringEnd = json.endsWith("\"");

            if (isStringStart && isStringEnd) {
                json = json.substring(1, json.length() - 1);
            }
        }

        assertEquals("123", json);
    }
}