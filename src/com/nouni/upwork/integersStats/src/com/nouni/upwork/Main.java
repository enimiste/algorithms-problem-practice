package com.nouni.upwork;

import java.util.Scanner;

public class Main {
    static int INTEGER_COUNT = 3;
    static int INTEGER_MIN_VALUE = 0;
    static int INTEGER_MAX_VALUE = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] items = requestInputs("Please type " + INTEGER_COUNT + " integrs between " + INTEGER_MIN_VALUE +
                " and " + INTEGER_MAX_VALUE + ". One integre per line :", scanner);
        while (true) {
            switch (showMenu(scanner)) {
                case 1:
                    showLargest(items);
                    break;
                case 2:
                    showSmallest(items);
                    break;
                case 3:
                    showSumAll(items);
                    break;
                case 4:
                    showOriginalArray(items);
                    break;
                case 5:
                    return;//exit the app
                default:
                    break;
            }
        }
    }

    private static int[] requestInputs(String message, Scanner in) {
        System.out.println(message);
        int[] items = new int[INTEGER_COUNT];
        int indx = 0;
        while (indx < INTEGER_COUNT) {
            if (in.hasNextInt()) {
                int x = in.nextInt();
                if (x >= INTEGER_MIN_VALUE && x <= INTEGER_MAX_VALUE) {
                    items[indx++] = x;
                    continue;
                } else
                    System.out.println("Error : The typed integer " + x + " should be between " + INTEGER_MIN_VALUE + " and " + INTEGER_MAX_VALUE);
            } else
                System.out.println("Error : Only integers are accepted");
            in.nextLine();
        }
        return items;
    }

    private static int showMenu(Scanner in) {
        do {
            System.out.println("Menu :");
            System.out.println("=======");
            System.out.println("1. Show the largest integer");
            System.out.println("2. Show the smallest integer");
            System.out.println("3. Show the sum of all integers");
            System.out.println("4. Show the original array");
            System.out.println("5. Exit the application");
            System.out.println("Type your choice : ");
            if (in.hasNextInt())
                return in.nextInt();
            else
                System.out.println("Error : You should type a choice number between 1 and 5");
            in.nextLine();
        } while (true);
    }

    private static void showOriginalArray(int[] items) {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0, n = items.length; i < n; i++) {
            s.append(items[i]);
            if (i < n - 1)
                s.append(",");
        }
        s.append("]");
        System.out.println(s);
    }

    private static void showSumAll(int[] items) {
        int sum = 0;
        for (int i = 0, n = items.length; i < n; i++) {
            sum += items[i];
        }
        System.out.println("Sum : " + sum);
    }

    private static void showSmallest(int[] items) {
        int min = INTEGER_MAX_VALUE;
        for (int i = 0, n = items.length; i < n; i++) {
            int x = items[i];
            if (x < min)
                min = x;
        }
        System.out.println("Min : " + min);
    }

    private static void showLargest(int[] items) {
        int max = INTEGER_MIN_VALUE;
        for (int i = 0, n = items.length; i < n; i++) {
            int x = items[i];
            if (x > max)
                max = x;
        }
        System.out.println("Max : " + max);
    }

}
