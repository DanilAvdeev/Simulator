package main;

public enum State {
    //NONE,
    //BORN,
    ALIVE,
    DEAD;

    public State step_1 (int around) {
        if (this == DEAD) {
            if (around == 3) {
                return ALIVE;
            } else {
                return DEAD;
            } // return (around == 3) ? BORN : NONE
        } else if (this == ALIVE) {
            if (around < 2 || around > 3) {
                return DEAD;
            } else {
                return ALIVE;
            } // return (around < 2 || around > 3) ? DEAD : ALIVE
        } else {
            return this;
        }
    }

//    public State step_2 () {
//        if (this == BORN) {
//            return ALIVE;
//        } else if (this == DEAD) {
//            return NONE;
//        } else {
//            return this;
//        }
//    }
}
