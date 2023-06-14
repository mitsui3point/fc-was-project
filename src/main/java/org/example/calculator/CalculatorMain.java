package org.example.calculator;

import org.example.ui.InputUI;
import org.example.ui.OutputUI;

import java.util.Scanner;

public class CalculatorMain {
    public static void main(String[] args) {
        InputUI inputUI = new InputUI(new Scanner(System.in));
        OutputUI outputUI = new OutputUI();

        int operand1 = inputUI.getOperand();
        String operator = inputUI.getOperation();
        int operand2 = inputUI.getOperand();

        outputUI.printCalculatorResult(operand1, operator, operand2, new Calculator());
    }

}
