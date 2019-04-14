package com.pattern.flyweight;

import java.util.HashMap;

public class ShapeFactory {

    private static final String[] colors = {"Red", "Green", "Blue", "White", "Black"};
    private static final HashMap circleMap = new HashMap();

    public static String getRandomColor () {
        return colors[(int) (Math.random() * colors.length)];
    }

    public static int getRandomX () {
        return (int) (Math.random() * 100);
    }

    public static int getRandomY () {
        return (int) (Math.random() * 100);
    }

    public static Shape getCircle (String color) {
        Circle circle = (Circle) circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
