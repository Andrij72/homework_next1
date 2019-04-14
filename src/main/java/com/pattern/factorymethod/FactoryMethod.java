package com.pattern.factorymethod;

public class FactoryMethod {
    private static final String BASE_ON_CORE = "Linux";
    private static final String TYPE_OF_UI = "graphic";
    private static final String SITE = "www.android.com";
    private static final boolean MULTITHREADING = true;
    private static final int PLATFORM_BIT = 64;

    public SYS getCurrentOS(CompanyManufacture name) {
        SYS sys = null;
        if (name.equals(CompanyManufacture.CANOCIAL)) {
            sys = new UbuntuSys.Builder()
                    .setBitDepth(PLATFORM_BIT)
                    .setMultithreading(MULTITHREADING)
                    .setPerformance(BASE_ON_CORE)
                    .setTypeOfUI(TYPE_OF_UI)
                    .build();
        } else if (name.equals(CompanyManufacture.APPLE)) {
            sys = AndroidSys.getInstance(MULTITHREADING, TYPE_OF_UI);
        } else if (name.equals(CompanyManufacture.GOOGLE)) {
            sys = new ProxyIOS(SITE);
        }
        return sys;
    }
}
