package com.pattern.method;

import lombok.Data;

@Data
public class IPHONEsys implements SYS {
    private String internetSite;

    public IPHONEsys (String internetSite) {
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
