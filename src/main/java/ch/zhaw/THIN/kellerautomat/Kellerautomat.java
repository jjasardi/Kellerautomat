package ch.zhaw.THIN.kellerautomat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ch.zhaw.THIN.kellerautomat.Stack.STACK_EMPTY;
import static ch.zhaw.THIN.kellerautomat.State.*;

public class Kellerautomat {


    private Stack stack = new Stack();
    private String result;
    public static final char EPSILON = 'e';
    public static final int FIRST = 0;
    State currentState;


    public String run(String input) {
        currentState = q0;
        char inputSymbol;
        boolean finished = false;
        int symbolPosition = FIRST;

        while (!finished) {
            if (symbolPosition < input.length()) {
                inputSymbol = input.charAt(symbolPosition);
            } else {
                inputSymbol = EPSILON;
            }
            symbolPosition++;

            switch (currentState) {
                case q0 -> stateQ0(inputSymbol);
                case q1 -> stateQ1(inputSymbol);
                case q2 -> stateQ2(inputSymbol);
                case q3 -> finished = true;
                case failed -> {
                    result = "Wrong Input!";
                    finished = true;
                }
            }
        }
        return result;
    }

    private void stateQ0(char inputSymbol) {
        if (Character.isDigit(inputSymbol)) {
            stack.push(String.valueOf(inputSymbol));
        } else if (inputSymbol == '+' || inputSymbol == '*') {
            try {
                stack.push((calculate(inputSymbol)));
                currentState = q1;
            } catch (StackEmptyException e) {
                currentState = failed;
            }
        } else {
            currentState = failed;
        }
    }

    private void stateQ1(char inputSymbol) {
        if (Character.isDigit(inputSymbol)) {
            stack.push(String.valueOf(inputSymbol));
            currentState = q0;
        } else if (inputSymbol == '+' || inputSymbol == '*') {
            try {
                stack.push((calculate(inputSymbol)));
                currentState = q1;
            } catch (StackEmptyException e) {
                currentState = failed;
            }
        } else if (inputSymbol == 'e') {
            result = stack.popAndReturn();
            currentState = q2;
        } else {
            currentState = failed;
        }
    }

    private void stateQ2(char inputSymbol) {
        if (inputSymbol == EPSILON && stack.popAndReturn().equals(STACK_EMPTY)) {
            currentState = q3;
        } else {
            currentState = failed;
        }
    }

    private int calculate(char inputSymbol) throws StackEmptyException {
        String firstString = stack.popAndReturn();
        String secondString = stack.popAndReturn();
        if (firstString.equals(STACK_EMPTY) || secondString.equals(STACK_EMPTY)) {
            throw new StackEmptyException();
        }

        int firstNumber = Integer.parseInt(firstString);
        int secondNumber = Integer.parseInt(secondString);

        int calcResult = 0;

        if (inputSymbol == '+') {
            calcResult = firstNumber + secondNumber;
        }
        if (inputSymbol == '*') {
            calcResult = firstNumber * secondNumber;
        }
        return calcResult;
    }


    public static void main(String[] args) throws IOException {
        printWelcomeMessage();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Kellerautomat kellerautomat = new Kellerautomat();
        boolean running = true;
        while (running) {
            System.out.println("\nyour input:");
            String word = reader.readLine();
            if (word.equals("exit")) {
                running = false;
            } else {
                String result = kellerautomat.run(word);
                System.out.println("\nthe result is:");
                System.out.println(result);
                kellerautomat.stack = new Stack();
            }
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("\n'Kellerautomat' is ready for input.");
        System.out.println("You can always end the program with writing exit");
    }
}
