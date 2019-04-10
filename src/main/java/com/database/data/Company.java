package com.database.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString

public class Company {
    private int id;
    private String name;
    private String address;

    public String toString() {
        return "Project[" +"["+"id _ " + id + "|"+
                 "name_ " + name + "|"+
                "address_ " + address +"]";
    }
}
