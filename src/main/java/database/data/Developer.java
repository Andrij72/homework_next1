package database.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Developer {
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private Integer salary;
    }
