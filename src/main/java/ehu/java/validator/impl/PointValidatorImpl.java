package ehu.java.validator.impl;

import ehu.java.validator.PointValidator;

public class PointValidatorImpl implements PointValidator {
    @Override
    public boolean isValidCoordinatesLine(String cleanedInput) {
        // Должно быть ровно 8 чисел, разделённых точками с запятой
        String regex = "(-?\\d+(\\.\\d+)?;){3}-?\\d+(\\.\\d+)?";
        return cleanedInput.matches(regex);
    }
}
