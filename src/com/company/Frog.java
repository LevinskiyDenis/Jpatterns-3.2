package com.company;

public class Frog {

    public static final int minPosition = 0;
    public static final int maxPosition = 10;

    protected final int START = 5;
    protected int currentPosition = START;

    public Frog() {
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public boolean jump(int steps) {

        if (this.getCurrentPosition() + steps > maxPosition || this.getCurrentPosition() + steps < minPosition) {
            System.out.println("Вы не можете выйти за пределы!");
            return false;
        } else {
            this.currentPosition = this.getCurrentPosition() + steps;
            FrogCommandCreator.createFrogCommand(this, steps);
            System.out.println("Прыг");
            return true;
        }
    }
}
