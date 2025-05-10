package ehu.java.state.impl;

import ehu.java.state.RectangleState;

public class InvalidState implements RectangleState {
    @Override
    public String getDescription() {
        return "Invalid Rectangle";
    }
}