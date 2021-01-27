package flow.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import flow.dto.TestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("Json -> Class Convert Test")
    public void parameter_convert_test_success_json2Class() throws Exception{
        String json = "{\n" +
                "    \"name\" : \"test\",\n" +
                "    \"number\":\"123\",\n" +
                "    \"wrapperNumber\":\"1234\",\n" +
                "    \"list\" : [\n" +
                "        {\n" +
                "            \"test\":\"test\",\n" +
                "            \"title\":\"title\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"test\":\"test1\",\n" +
                "            \"title\":\"title2\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"map\" : {\n" +
                "        \"test\":\"123\",\n" +
                "        \"test1\":\"456\"\n" +
                "    }\n" +
                "}";
        TestDto testDto = objectMapper.readValue(json, TestDto.class);

        assertEquals("test", testDto.getName());
        assertEquals(123, testDto.getNumber());
        assertEquals(1234, testDto.getWrapperNumber());
        assertEquals(2, testDto.getList().size());
        assertNotNull(testDto.getMap().get("test"));
        assertNotNull(testDto.getMap().get("test1"));
    }

    @Test
    @DisplayName("Json -> Class Convert Test Case 2")
    public void parameter_convert_test_success_json2Class_without_name_column() throws Exception{
        String json = "{\n" +
                "    \"number\":\"123\",\n" +
                "    \"wrapperNumber\":\"1234\",\n" +
                "    \"list\" : [\n" +
                "        {\n" +
                "            \"test\":\"test\",\n" +
                "            \"title\":\"title\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"test\":\"test1\",\n" +
                "            \"title\":\"title2\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"map\" : {\n" +
                "        \"test\":\"123\",\n" +
                "        \"test1\":\"456\"\n" +
                "    }\n" +
                "}";
        TestDto testDto = objectMapper.readValue(json, TestDto.class);

        assertEquals(123, testDto.getNumber());
        assertEquals(1234, testDto.getWrapperNumber());
        assertEquals(2, testDto.getList().size());
        assertNotNull(testDto.getMap().get("test"));
        assertNotNull(testDto.getMap().get("test1"));
    }
}