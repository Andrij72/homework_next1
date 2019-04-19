package database.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Project {
    private Long id;
    private String name;
    private Integer cost;
    private Date date;
    }
