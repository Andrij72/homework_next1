package com.pattern.abstractfactory;

public abstract class AbstractFactory {
    public static final String FIRST_FIGURE = "RECTANGLE";
    public static final String SECOND_FIGURE = "SQUARE";

    abstract Shape getShape (String shapeType);
}
