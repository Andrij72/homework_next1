package com.pattern.singelton;

//Singleton With Public Static Factory Method
    public class Singleton {
        private static final Singleton INSTANCE = new Singleton();
        private Singleton() {}
        public static Singleton getInstance(){
            return INSTANCE;
        }
    }
	