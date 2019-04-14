package com.pattern.flyweight;

public class FlyweightApplication {

    public static void main (String[] args) {
        for (int i = 0; i < 20; ++i) {
            Circle circle = (Circle) ShapeFactory.getCircle(ShapeFactory.getRandomColor());
            circle.setX(ShapeFactory.getRandomX());
            circle.setY(ShapeFactory.getRandomX());
            circle.setRadius(100);
            circle.draw();
        }
    }
}
