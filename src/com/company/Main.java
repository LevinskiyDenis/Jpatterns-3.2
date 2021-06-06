package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<FrogCommand> commands = new ArrayList<>();
    private static int indCurCommand = -1;
    private static String[] map = new String[11];

    public static void main(String[] args) {


        System.out.println("***CRAZY FROG GAME***");
        System.out.println("+N - прыгни на N шагов направо");
        System.out.println("-N - прыгни на N шагов налево");
        System.out.println("<<-Undo(отмени последнюю команду)");
        System.out.println(">> -Redo(повтори отменённую команду)");
        System.out.println("!!-повтори последнюю команду");
        System.out.println("0 - выход");

        Scanner scanner = new Scanner(System.in);
        Frog frog = new Frog();
        showMap(frog);

        while (true) {
            System.out.println("\nВведите команду");
            String input = scanner.nextLine();

            if (input.equals("0")) {
                System.out.println("Выход из игры...");
                break;
            }

            // ОТМЕНА КОМАНДЫ
            undo(input);

            // ПОВТОР ОТМЕНЕННОЙ КОМАНДЫ
            redo(input);

            // ПОВТОР ПОСЛЕДНЕЙ КОМАНДЫ
            repeat(input);

            // ХОД
            move(frog, input);

            // ВЫВОД КАРТЫ
            showMap(frog);
        }
    }

    private static void showMap(Frog frog) {
        Arrays.fill(map, "0");
        map[frog.getCurrentPosition()] = "X";
        System.out.println("\n == Лягушка находится здесь ==");
        System.out.println(Arrays.toString(map));
    }

    private static void move(Frog frog, String input) {
        if (input.matches("[+-][\\d]+")) {
            int steps = Integer.parseInt(input);

            if (frog.jump(steps)) {

                if (indCurCommand != commands.size() - 1) {
                    commands.remove(commands.size() - 1);
                }

                indCurCommand++;
                commands.add(indCurCommand, FrogCommandCreator.createFrogCommand(frog, steps));
            }
        }
    }

    private static void undo(String input) {
        if (input.equals("<<")) {

            if (indCurCommand < 0) {
                System.out.println("Нечего отменять");
            } else {
                commands.get(indCurCommand).undo();
                indCurCommand--;
            }
        }
    }

    private static void redo(String input) {
        if (input.equals(">>")) {

            if (indCurCommand == commands.size() - 1) {
                System.out.println("Нет отмененных команд для повтора");
            } else {
                indCurCommand++;
                commands.get(indCurCommand).doit();
            }

        }
    }

    private static void repeat(String input) {

        if (input.equals("!!")) {

            if (indCurCommand < 0) {
                System.out.println("Нет действий для повтора");
                return;
            }

            if (commands.get(indCurCommand).doit()) {

                if (indCurCommand != commands.size() - 1) {
                    commands.remove(commands.size() - 1);
                }

                commands.add(commands.get(indCurCommand));
                indCurCommand++;
            }
        }
    }
}
