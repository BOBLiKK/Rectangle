package ehu.java.validator.impl;

import ehu.java.validator.PointValidator;

public class PointValidatorImpl implements PointValidator {

    private static final String COORDINATE_LINE_REGEX = "(-?\\d+(\\.\\d+)?;){3}-?\\d+(\\.\\d+)?";
    @Override
    public boolean isValidCoordinatesLine(String cleanedInput) {
        // Should be 8 numbers separated by ;
        return cleanedInput.matches(COORDINATE_LINE_REGEX);
    }
}
