package main.lifeGame;

public enum CellState {
    NONE,
    BORN,
    ALIVE,
    DEAD;

    public CellState step_1(int around) {
        if (this == NONE) {
            if (around == 3) {
                return ALIVE;
            } else {
                return NONE;
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

    public CellState step_2() {
        if (this == BORN) {
            return ALIVE;
        } else if (this == DEAD){
            return NONE;
        } else {
            return this;
        }
    }
}
