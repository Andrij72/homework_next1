package com.pattern.factorymethod;

public class ProxyIOS implements SYS {
    private String internetSite;
    private IphonSys iOS;

    public ProxyIOS (String internetSite) {
        this.internetSite = internetSite;
    }

    @Override
    public String getSYS () {
        if (iOS == null) {
            iOS = new IphonSys(internetSite);
        }
        return iOS.getSYS();
    }
}
