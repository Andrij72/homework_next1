package com.pattern.method;

import lombok.Data;

@Data
public class UBUNTUsys implements SYS {
    private int bitDepth;
    private boolean isMultithreading;
    private String typeOfUI;
    private String performance;

    public static class Builder {
        private UBUNTUsys ubuntUsys;

        public Builder() {
            ubuntUsys = new UBUNTUsys();
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

        public UBUNTUsys build() {
            return ubuntUsys;
        }
    }

    @Override
    public String getSYS () {
        return this.toString();
    }
}
