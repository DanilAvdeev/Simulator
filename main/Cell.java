package main;

import java.awt.*;

public class Cell {
    public State state;

    public Cell() {
        state = State.DEAD;
    }

    public boolean isLive() {
        return state == State.ALIVE;
    }

    public void step1(int around) {
        state = state.step_1(around);
    }

    public void turn() { this.state = State.ALIVE; }

    public Color getColor() {
        if (this.state == State.DEAD) {
            return Color.WHITE;
        }
        return Color.BLUE;
    }
}
