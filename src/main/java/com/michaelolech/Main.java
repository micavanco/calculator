package com.michaelolech;

import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> expressions = Map.of(
                "2 + 2 - 6 / 2 * 5 + 2", -9,
                "3 * -2 + 6", 0,
                "5 / 2 / 6 + 5", 5
        );

        for (String expression : expressions.keySet()) {
            System.out.println(
                    "Result of expression \"" +
                            expression + "\": " +
                            calculate(expression) +
                            " should equals " + expressions.get(expression));
        }
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
                            values[i + 1] = "0";
                            break;
                        case "/":
                            if (right == 0) {
                                throw new ArithmeticException("Cannot divide by zero");
                            }
                            result /= right;
                            values[i + 1] = "0";
                            break;
                        default:
                            throw new IllegalArgumentException("Incorrect operator.");
                    }
                }
            }

            for (int i = 0; i < values.length; i++) {
                if (i % 2 == 1) {
                    int right = Integer.parseInt(values[i + 1]);

                    switch (values[i]) {
                        case "+":
                            result += right;
                            break;
                        case  "-":
                            result -= right;
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