package com.database.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Project {
    private int id;
    private String name;
    private double cost;
    private Date date;

    public String toString() {
        return "Project[" +"id _ " + id + "|"+
                "name_ " + name + "|"+
                "cost_ " + cost + "|"+
                "date_ " + date + "|" +"]" ;
    }
}
