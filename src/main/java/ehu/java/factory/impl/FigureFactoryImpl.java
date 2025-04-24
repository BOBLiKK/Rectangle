package ehu.java.factory.impl;

import ehu.java.entity.Point;
import ehu.java.entity.Rectangle;
import ehu.java.factory.FigureFactory;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FigureFactoryImpl implements FigureFactory {
    @Override
    public Rectangle createRectangle(String name, List<Point> validatedCoordinates) {
        AtomicInteger idGenerator = new AtomicInteger(1);
        int id = idGenerator.getAndIncrement();
        return new Rectangle(id, name, validatedCoordinates);
    }

    @Override
    public Rectangle createRectangle(int id, String name, List<Point> validatedCoordinates) {
        return new Rectangle(id, name, validatedCoordinates);
    }
}
