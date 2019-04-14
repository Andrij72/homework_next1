package com.pattern.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ANDROIDsys implements SYS {
    private boolean isMultithreading;
    private String typeOfUI;
    private static ANDROIDsys instance = null;

    public static ANDROIDsys getInstance(boolean multithreadin, String typeOfUI) {
        if (instance == null) {
            instance = new ANDROIDsys(multithreadin, typeOfUI);
        }
        return instance;
    }

    @Override
    public String getSYS () {
        return this.toString();
    }
}
