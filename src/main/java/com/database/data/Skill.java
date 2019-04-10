package com.database.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Skill {
    private int id;
    private String industry;
    private String level;

    public String toString() {
        return "Skill["  +"id _ " + id + "|"+
                "industry_ " + industry + "|"+
                "level_ " + level + "]" ;
    }
}
