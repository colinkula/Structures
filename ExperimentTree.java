import java.util.Stack;

/**
 * The ExperimentTree class represents a binary tree structure used to construct
 * and evaluate mathematical expressions provided in postfix notation. It
 * utilizes a custom ExperimentNode class and supports operators '+', '-', '*',
 * '/', and '^'. The tree can be created from a postfix expression, and its
 * prefix form can be printed. Additionally, it can evaluate the expression
 * using a provided HashBasedSet.
 * 
 * @author ColinKula
 */

public class ExperimentTree {

    // Fields

    /**
     * The root node of the binary tree.
     */
    public ExperimentNode root;
    
    /**
     * The postifx expression used to represent the tree.
     */
    public String postfixExpression;

    // Constructors

    /**
     * Constructs an ExperimentTree object with a null root.
     */
    public ExperimentTree(String postfixExpression) {
        this.postfixExpression = postfixExpression;
        root = null;
    }

    // Methods

    /**
     * Creates a binary tree from the provided postfix expression using a stack
     * for efficient construction.
     */
    public void createTree() {
        Stack<ExperimentNode> stack = new Stack<>();
        char scannedLiteral;

        for (int i = 0; i < postfixExpression.length(); i++) {
            scannedLiteral = postfixExpression.charAt(i);
            String variable = "";

            if (isOperand(scannedLiteral)) {
                boolean digitFound = false;
                while (i < postfixExpression.length() && Character.isLetterOrDigit(postfixExpression.charAt(i))) {
                    if (Character.isDigit(postfixExpression.charAt(i))) {
                        digitFound = true;
                    }

                    if (digitFound && Character.isLetter(postfixExpression.charAt(i))) {
                        break;
                    }
                    variable += postfixExpression.charAt(i);
                    i++;
                }
                i--;

                stack.push(new ExperimentNode(variable));
                
            } else if (isOperator(scannedLiteral)) {
                ExperimentNode operatorNode = new ExperimentNode(scannedLiteral);
                operatorNode.isOperator = true;
                operatorNode.rightChild = stack.pop();
                operatorNode.leftChild = stack.pop();
                stack.push(operatorNode);
            }
        }

        root = stack.pop();
    }

    /**
     * Checks if the provided character is an operator.
     * 
     * @param literal The character to be checked.
     * @return true if the character is an operator; false otherwise.
     */
    public boolean isOperator(char literal) {
        return (literal == '+' || literal == '-' || literal == '*'
                || literal == '/' || literal == '^');
    }

    /**
     * Checks if the provided character is an operand.
     * 
     * @param literal The character to be checked.
     * @return true if the character is an operand; false otherwise.
     */
    public boolean isOperand(char literal) {
        return (Character.isLetterOrDigit(literal));
    }

    /**
     * Prints the prefix form of the expression represented by the tree.
     */
    public void printPrefix() {
        printPrefix(root);
    }

    /**
     * Recursively prints the prefix form of the expression starting from the
     * given node.
     * 
     * @param node The current node in the traversal.
     */
    private void printPrefix(ExperimentNode node) {
        if (node != null) {
            if (node.isOperator) {
                System.out.print(node.operator);
            } else {
                System.out.print(node.variable);
            }
            printPrefix(node.leftChild);
            printPrefix(node.rightChild);
        }
    }

    /**
     * Evaluates the postfix expression represented by the tree using the
     * provided HashBasedSet for variable values.
     * 
     * @param set The set containing variable values.
     * @return The result of the evaluation.
     */
    public double evaluatePostfix(HashBasedSet set) {
        Stack<Double> stack = new Stack<>();

        char scannedLiteral;

        for (int i = 0; i < postfixExpression.length(); i++) {
            scannedLiteral = postfixExpression.charAt(i);
            String variable = "";

            if (isOperand(scannedLiteral)) {
                boolean digitFound = false;
                while (i < postfixExpression.length() && Character.isLetterOrDigit(postfixExpression.charAt(i))) {
                    if (Character.isDigit(postfixExpression.charAt(i))) {
                        digitFound = true;
                    }

                    if (digitFound && Character.isLetter(postfixExpression.charAt(i))) {
                        break;
                    }
                    variable += postfixExpression.charAt(i);
                    i++;
                }
                i--;

                if (set.table.contains(variable)) {
                    stack.push(set.table.getValueFor(variable));
                }
            } else if (isOperator(scannedLiteral)) {
                stack.push(combineTwoValues(stack.pop(), stack.pop(), scannedLiteral));
            }
        }
        return stack.pop();
    }

    /**
     * Combines two values based on the given operator.
     * 
     * @param firstPoppedValue  The first value popped from the stack.
     * @param secondPoppedValue The second value popped from the stack.
     * @param operator          The operator to be applied.
     * @return The result of the operation.
     */
    public double combineTwoValues(double firstPoppedValue, double secondPoppedValue, char operator) {

        double toBeReturned = 0;

        if (operator == '+') {
            toBeReturned = secondPoppedValue + firstPoppedValue;
        } else if (operator == '-') {
            toBeReturned = secondPoppedValue - firstPoppedValue;
        } else if (operator == '*') {
            toBeReturned = secondPoppedValue * firstPoppedValue;
        } else if (operator == '/') {
            toBeReturned = secondPoppedValue / firstPoppedValue;
        } else if (operator == '^') {
            toBeReturned = Math.pow(secondPoppedValue, firstPoppedValue);
        }

        return toBeReturned;
    }
}
