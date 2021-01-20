package flow.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @ToString
public class TestDto {
    String name;
    List<String> list = new ArrayList<>();
}
