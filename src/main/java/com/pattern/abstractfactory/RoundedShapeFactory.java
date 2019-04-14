package com.pattern.abstractfactory;

public class RoundedShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape (String shapeType) {
        if (shapeType.equalsIgnoreCase(FIRST_FIGURE)) {
            return new RoundedRectangle();
        } else if (shapeType.equalsIgnoreCase(SECOND_FIGURE)) {
            return new RoundedSquare();
        }
        return null;
    }
}
