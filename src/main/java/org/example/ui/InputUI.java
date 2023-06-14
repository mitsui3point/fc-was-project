package org.example.ui;

import java.util.Scanner;

public class InputUI {
    private final Scanner scanner;

    public InputUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getOperation() {
        System.out.println("연산자를 입력하세요.");
        String operator = String.valueOf(scanner.next().charAt(0));
        return operator;
    }

    public int getOperand() {
        System.out.println("숫자를 입력하세요.");
        int operand1 = scanner.nextInt();
        return operand1;
    }

    public int getContinueFlag() {
        System.out.println("계속 입력하시겠습니까?(0: 계속, 1: 종료)");
        return scanner.nextInt();
    }

    public String getCourseGrade() {
        System.out.println("교과목 평점을 입력하세요.");
        return scanner.next();
    }

    public int getCoursePoint() {
        System.out.println("학점을 입력하세요.");
        return scanner.nextInt();
    }

    public String getCourseName() {
        System.out.println("교과목명을 입력하세요.");
        return scanner.next();
    }
}