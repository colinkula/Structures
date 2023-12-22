import java.util.Stack;

/**
 * The InfixPostfix class converts infix expressions to postfix expressions
 * using a custom Stack class. It provides methods for checking if a character
 * is an operator or operand, determining the precedence of an operator, and
 * performing the conversion from infix to postfix. The program handles
 * parentheses and supports operators '+', '-', '*', '/', and '^'.
 * 
 * @author ColinKula
 */

public class InfixPostfix {

    // Fields

    /**
     * The infix expression provided to the InfixPostfix object.
     */
    String infixPresentation = "";

    /**
     * The resulting postfix expression after conversion.
     */
    String postfixRepresentation = "";

    // Constructors
    /**
     * Constructs a InfixPostfix object with an infix string and calls infix
     * to postfix to convert the passed infix expression.
     */
    public InfixPostfix(String passedInfix) {
        infixPresentation = passedInfix;
        infixToPostfix();
    }

    // Methods

    /**
     * Checks if the provided literal is an operator.
     * 
     * @param literal The character to be checked.
     * @return true if the character is an operator; false otherwise.
     */
    public boolean isOperator(char literal) {
        return (literal == '+' || literal == '-' || literal == '*'
                || literal == '/' || literal == '^');
    }

    /**
     * Checks if the provided literal is an operand.
     * 
     * @param literal The character to be checked.
     * @return true if the character is an operand; false otherwise.
     */
    public boolean isOperand(char literal) {
        return (Character.isLetterOrDigit(literal));
    }

    /**
     * Determines the precedence of an operator.
     * 
     * @param operator The operator to be evaluated.
     * @return The precedence value of the operator.
     */
    public int precedence(char operator) {
        int returnValue = 0;
        if (operator == '^') {
            returnValue = 3;
        } else {
            if (operator == '*' || operator == '/') {
                returnValue = 2;
            } else {
                if (operator == '+' || operator == '-') {
                    returnValue = 1;
                }
            }
        }
        return returnValue;
    }

    /**
     * Constructs an InfixPostfix object with the provided infix expression and
     * immediately converts it to a postfix expression.
     * 
     * @param passedInfix The infix expression to be converted.
     */
    public void infixToPostfix() {
        String infix = infixPresentation;
        Stack<Character> operatorStack = new Stack<Character>();
        char scannedLiteral;

        // Loop the length of the infix expression and evaluate each character.
        for (int i = 0; i < infix.length(); ++i) {
            scannedLiteral = infix.charAt(i);

            // If the scanned literal is a operand, add it to the postfix
            // expression.
            if (isOperand(scannedLiteral)) {
                postfixRepresentation += infix.charAt(i);
                // If the scanned literal is a '(', push it to the operator
                // stack.
            } else if (scannedLiteral == '(') {
                operatorStack.push(scannedLiteral);
            } else if (scannedLiteral == ')') {
                endParenthesisLoop(operatorStack);
            } else {
                operatorLoop(operatorStack, scannedLiteral);
            }
        }
        emptyStackLoop(operatorStack);
    }

    /**
     * Handles the process when a ')' is found in the infix expression. Pops
     * operators from the stack until a '(' is encountered.
     * 
     * @param operatorStack The stack containing operators.
     */
    public void endParenthesisLoop(Stack<Character> operatorStack) {
        while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
            postfixRepresentation += operatorStack.peek();
            operatorStack.pop();
        }
        operatorStack.pop();
    }

    /**
     * Handles the process when an operator is found in the infix expression.
     * Pops operators from the stack based on their precedence.
     * 
     * @param operatorStack  The stack containing operators.
     * @param scannedLiteral The current character in the infix expression.
     */
    public void operatorLoop(Stack<Character> operatorStack, char scannedLiteral) {
        while (!operatorStack.isEmpty() && precedence(scannedLiteral) <= precedence(operatorStack.peek())) {
            if (scannedLiteral == '^' && operatorStack.peek() == '^') {
                operatorStack.push(scannedLiteral);
                return;
            }
            postfixRepresentation += operatorStack.peek();
            operatorStack.pop();
        }
        operatorStack.push(scannedLiteral);
    }

    /**
     * Handles the process after scanning all characters from the infix
     * expression. Pops any remaining operators from the stack.
     * 
     * @param operatorStack The stack containing operators.
     */
    public void emptyStackLoop(Stack<Character> operatorStack) {
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() == '(') {
                postfixRepresentation = "Infix expression is invalid.";
            }
            postfixRepresentation += operatorStack.peek();
            operatorStack.pop();
        }

    }

}
