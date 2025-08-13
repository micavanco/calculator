package com.michaelolech;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> expressions = Map.of(
                "2 + 2 - 6 / 2 * 5 + 2", -9,
                "3 * -2 + 6", 0,
                "6 / 2 / 6 + 5 + 15 / 3", 10
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
        boolean isfirstOfPartialResult = true;
        int partialResult = 0;
        int firstIndexOfPartialResult = 0;
        int finalResult = 0;

        try {
            for (int i = 0; i < values.length; i++) {
                if (i % 2 == 1) {
                    int right = Integer.parseInt(values[i + 1]);

                    switch (values[i]) {
                        case "+":
                        case "-":
                            if (!isfirstOfPartialResult) {
                                isfirstOfPartialResult = true;
                                values[firstIndexOfPartialResult] = String.valueOf(partialResult);
                                values[i - 1] = String.valueOf(partialResult);
                                partialResult = 0;
                            }

                            break;
                        case "*":
                            if (isfirstOfPartialResult) {
                                isfirstOfPartialResult = false;
                                firstIndexOfPartialResult = i - 1;
                                partialResult = Integer.parseInt(values[firstIndexOfPartialResult]);
                            }

                            partialResult *= right;
                            break;
                        case "/":
                            if (isfirstOfPartialResult) {
                                isfirstOfPartialResult = false;
                                firstIndexOfPartialResult = i - 1;
                                partialResult = Integer.parseInt(values[firstIndexOfPartialResult]);
                            }

                            partialResult /= right;
                            break;
                        default:
                            throw new IllegalArgumentException("Incorrect operator.");
                    }
                }
            }

            if (!isfirstOfPartialResult) {
                values[firstIndexOfPartialResult] = String.valueOf(partialResult);
            }

            boolean firstValue = true;

            for (int i = 0; i < values.length; i++) {
                if (i % 2 == 1) {
                    int right = Integer.parseInt(values[i + 1]);

                    if (firstValue) {
                        firstValue = false;
                        finalResult = Integer.parseInt(values[i - 1]);
                    }

                    switch (values[i]) {
                        case "+":
                            finalResult += right;
                            break;
                        case  "-":
                            finalResult -= right;
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

        return finalResult;
    }
}