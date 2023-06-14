package org.example.ui;

import org.example.calculator.Calculator;
import org.example.calculator.vo.PositiveNumber;

public class OutputUI {

    public void printCalculatorResult(int operand1, String operator, int operand2, Calculator calculator) {
        int result = calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
        System.out.println(result);
    }
}