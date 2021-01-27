package flow.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @ToString
@Setter
public class TestDto {
    String name;
    int number;
    Integer wrapperNumber;
    List<TestInner> list = new ArrayList<>();
    Map<String, String> map = new HashMap<>();

    @Builder
    @NoArgsConstructor @AllArgsConstructor
    @Getter @ToString
    @Setter
    public static class TestInner{
         private String test;
         private String title;
    }
}
