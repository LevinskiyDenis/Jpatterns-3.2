package com.company;

public class FrogCommandCreator {

    public static FrogCommand createFrogCommand(Frog frog, int steps) {

        return new FrogCommand() {

            @Override
            public boolean doit() {
                final int savedSteps = steps;
                return frog.jump(savedSteps);
            }

            @Override
            public void undo() {
                final int savedSteps = -steps;
                frog.jump(savedSteps);
            }

        };

    }
}
