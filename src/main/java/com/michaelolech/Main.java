package com.michaelolech;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println(calculate("2 + 2 - 6 / 2 * 5"));
    }

    public static int calculate(String expression) {
        String[] values = expression.split(" ");
        int result = 0;

        try {
            boolean first = false;
            for (int i = 0; i < values.length; i++) {
                if (i % 2 == 1) {
                    int right = Integer.parseInt(values[i + 1]);

                    if (!first && (Objects.equals(values[i], "*") || Objects.equals(values[i], "/"))) {
                        first = true;
                        result = Integer.parseInt(values[i - 1]);
                    }

                    switch (values[i]) {
                        case "+":
                        case "-":
                            break;
                        case "*":
                            result *= right;
                            break;
                        case "/":
                            if (right == 0) {
                                throw new ArithmeticException("Cannot divide by zero");
                            }
                            result /= right;
                            break;
                        default:
                            throw new IllegalArgumentException("Incorrect operator.");
                    }
                }
            }

            for (int i = values.length - 1; i > 0; i--) {
                if (i % 2 == 1) {
                    int left = Integer.parseInt(values[i - 1]);

                    switch (values[i]) {
                        case "+":
                            result += left;
                            break;
                        case  "-":
                            result = left - result;
                            break;
                        case "*":
                        case "/":
                            break;
                        default:
                            throw new IllegalArgumentException("Incorrect operator.");
                    }
                }
            }
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Invalid number.");
        }

        return result;
    }
}