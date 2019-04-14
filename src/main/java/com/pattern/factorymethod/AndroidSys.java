package com.pattern.factorymethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AndroidSys implements SYS {
    private boolean isMultithreading;
    private String typeOfUI;
    private static AndroidSys instance = null;

    public static AndroidSys getInstance(boolean multithreadin, String typeOfUI) {
        if (instance == null) {
            instance = new AndroidSys(multithreadin, typeOfUI);
        }
        return instance;
    }

    @Override
    public String getSYS () {
        return this.toString();
    }
}
