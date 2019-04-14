package com.pattern.factorymethod;

import lombok.Data;

@Data
public class IphonSys implements SYS {
    private String internetSite;

    public IphonSys (String internetSite) {
        this.internetSite = internetSite;
        load(internetSite);
    }

    private void load(String internetSite) {
        System.out.println(" Starting from url: " + internetSite);
    }

    @Override
    public String getSYS () {
        return "System installing success";
    }
}
