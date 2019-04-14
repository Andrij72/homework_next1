package com.pattern.method;

public class FactoryMethodApplication {
    public static void main(String[] args) {
        FactoryMethod factoryMethod = new FactoryMethod();
        SYS sys = factoryMethod.getCurrentOS(CompanyManufacture.GOOGLE);
        System.out.println(sys.getSYS());
    }
}
