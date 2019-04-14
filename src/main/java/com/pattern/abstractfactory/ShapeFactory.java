package com.pattern.abstractfactory;

public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape (String shapeType) {

        try {
            if (shapeType.equalsIgnoreCase(FIRST_FIGURE)) {
                return new Rectangle();
            } else if (shapeType.equalsIgnoreCase(SECOND_FIGURE)) {
                return new Square();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
