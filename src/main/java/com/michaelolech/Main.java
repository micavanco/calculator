package com.michaelolech;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }

    public static int calculate(String expression) {
        String[] values = expression.split(" ");
        List<Integer> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();

        try {
            for(String value : values) {
                switch (value) {
                    case "-":
                    case "+":
                    case "*":
                    case "/":
                        operators.add(value);
                        break;
                    default:
                        numbers.add(Integer.parseInt(value));
                        break;
                }
            }
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Invalid number or operator");
        }


    }
}