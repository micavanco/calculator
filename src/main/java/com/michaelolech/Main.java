package com.michaelolech;

public class Main {
    public static void main(String[] args) {
        System.out.println(calculate("3 * -2 + 6"));
    }

    public static int calculate(String expression) {
        String[] values = expression.split(" ");
        int result = 0;

        try {
            result = Integer.parseInt(values[0]);
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
                            result *= right;
                            break;
                        case "/":
                            result /= right;
                            break;
                    }
                }
            }
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Invalid number or operator");
        }

        return result;
    }
}