package ehu.java.validator.impl;

import ehu.java.validator.PointValidator;

public class PointValidatorImpl implements PointValidator {
    @Override
    public boolean isValidCoordinatesLine(String cleanedInput) {
        // Should be 8 numbers separated by ;
        String regex = "(-?\\d+(\\.\\d+)?;){3}-?\\d+(\\.\\d+)?";
        return cleanedInput.matches(regex);
    }
}
