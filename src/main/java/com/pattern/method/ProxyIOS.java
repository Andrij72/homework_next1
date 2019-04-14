package com.pattern.method;

public class ProxyIOS implements SYS {
    private String internetSite;
    private IPHONEsys iOS;

    public ProxyIOS (String internetSite) {
        this.internetSite = internetSite;
    }

    @Override
    public String getSYS () {
        if (iOS == null) {
            iOS = new IPHONEsys(internetSite);
        }
        return iOS.getSYS();
    }
}
