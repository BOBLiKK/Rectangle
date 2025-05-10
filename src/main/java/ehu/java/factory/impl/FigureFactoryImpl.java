package ehu.java.factory.impl;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import ehu.java.factory.FigureFactory;
import ehu.java.state.impl.ValidState;
import ehu.java.util.IDGenerator;
import ehu.java.warehouse.Warehouse;
import java.util.List;


public class FigureFactoryImpl implements FigureFactory {

    @Override
    public Rectangle createRectangle(List<Point> validatedCoordinates) {
        int id = IDGenerator.getInstance().generateId();
        Rectangle rectangle = new Rectangle(id, validatedCoordinates);
        rectangle.setState(new ValidState());
        Warehouse.getInstance().addRectangle(rectangle);
        return rectangle;
    }
}