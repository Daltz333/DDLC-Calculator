package Calculator.Logic;

public class Calculations {

    private String numOne = "";
    private String numTwo = "";
    private Double doubleNumOne;
    private Double doubleNumTwo;
    private char operand;
    private int currentState = 0;
    private String result;

    public void setNumOne(double inputNumOne) {
        numOne = numOne + (int)inputNumOne;
        System.out.println("num one is set to: " + numOne);

    }

    public void setNumTwo(double inputNumTwo) {
        numTwo = numTwo + (int)inputNumTwo;
        System.out.println("num two is set to: " + numTwo);
        currentState=2;

    }

    public void setOperand(char inputOperand) {
        doubleNumOne = Double.parseDouble(numOne);
        operand = inputOperand;
        currentState = 1;

    }

    public double getCurrentState() {
        return currentState;

    }

    public void clearNumData() {
        numOne = "";
        numTwo = "";
        currentState = 0;

    }

    public String calculate() {
        doubleNumTwo = Double.parseDouble(numTwo);
        System.out.println(numOne + operand + numTwo);
        switch(operand) {
            case '+':
                result = Double.toString(doubleNumOne + doubleNumTwo);
                break;

            case '-':
                result = Double.toString(doubleNumOne - doubleNumTwo);
                break;

            case '*':
                result = Double.toString(doubleNumOne * doubleNumTwo);
                break;

            case '/':
                try {
                    result = Double.toString(doubleNumOne / doubleNumTwo);

                } catch (ArithmeticException e) {
                    result = "ERR: DIVIDE BY ZERO";

                }

                break;

        }
        clearNumData();
        return result;

    }


}
