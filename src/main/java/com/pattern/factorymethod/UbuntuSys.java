package com.pattern.factorymethod;

import lombok.Data;

@Data
public class UbuntuSys implements SYS {
    private int bitDepth;
    private boolean isMultithreading;
    private String typeOfUI;
    private String performance;

    public static class Builder {
        private UbuntuSys ubuntUsys;

        public Builder() {
            ubuntUsys = new UbuntuSys();
        }

        public Builder setBitDepth(int bitDepth) {
            ubuntUsys.bitDepth = bitDepth;
            return this;
        }

        public Builder setMultithreading(boolean multithreading) {
            ubuntUsys.isMultithreading = multithreading;
            return this;
        }

        public Builder setTypeOfUI(String typeOfUI) {
            ubuntUsys.typeOfUI = typeOfUI;
            return this;
        }

        public Builder setPerformance(String performance) {
            ubuntUsys.performance = performance;
            return this;
        }

        public UbuntuSys build() {
            return ubuntUsys;
        }
    }

    @Override
    public String getSYS () {
        return this.toString();
    }
}
