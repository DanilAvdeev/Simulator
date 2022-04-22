package main;

public enum State {
    ALIVE,
    DEAD;

    public State step_1 (int around) {
        if (this == DEAD) {
            if (around == 3) {
                return ALIVE;
            } else {
                return DEAD;
            }
        } else if (this == ALIVE) {
            if (around < 2 || around > 3) {
                return DEAD;
            } else {
                return ALIVE;
            }
        } else {
            return this;
        }
    }
}
