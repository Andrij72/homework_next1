package com.database.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Customer {
    private int id;
    private String name;
    private int age;

    public String toString() {
        return "Customer" +"["+"id _ " + id + "|"+
                "name = '" + name + "|"+
                ", age = '" + age +"]";
    }
}
