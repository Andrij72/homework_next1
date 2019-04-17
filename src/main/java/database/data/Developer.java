package database.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Developer {
    private int id;
    private String name;
    private int age;
    private String sex;
    private double salary;
    }
