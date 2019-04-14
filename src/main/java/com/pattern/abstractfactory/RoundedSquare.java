package com.pattern.abstractfactory;

public class RoundedSquare implements Shape {
    @Override
    public void draw () {
        System.out.println("Inside RoundedSquare::draw() factorymethod.");
    }
}
