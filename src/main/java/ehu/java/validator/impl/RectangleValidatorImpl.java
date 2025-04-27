package ehu.java.validator.impl;

import ehu.java.validator.RectangleValidator;
import java.util.List;

public class RectangleValidatorImpl implements RectangleValidator {

    @Override
    public boolean isValidRectangle(List<Double> distances) {
        if (distances.size() != 6) {
            return false;
        }
        distances.sort(Double::compareTo);
        // Проверка свойств прямоугольника
        double side1 = distances.get(0);
        double side2 = distances.get(1);
        double side3 = distances.get(2);
        double side4 = distances.get(3);
        double diag1 = distances.get(4);
        double diag2 = distances.get(5);

        //todo not sure about the precision
        boolean equalSides = Math.abs(side1 - side2) < 1e-6
                && Math.abs(side3 - side4) < 1e-6;
        boolean equalDiagonals = Math.abs(diag1 - diag2) < 1e-6;
        return equalSides && equalDiagonals;
    }
}
