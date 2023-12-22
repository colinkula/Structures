/**
 * The ExperimentNode class represents a node in a binary tree used to
 * construct and evaluate mathematical expressions. Each node can either
 * store an operand (variable) or an operator. It includes references to its
 * left and right children for building the tree structure.
 * 
 * @author ColinKula
 */

public class ExperimentNode {
    
    // Fields
    
    /**
     * The operator stored in the node. Used if the node represents an operator.
     */
    char operator;
    
    /**
     * The variable stored in the node. Used if the node represents an operand.
     */
    String variable;
    
    /**
     * Indicates whether the node represents an operator or operand.
     */
    boolean isOperator;
    
    /**
     * The right child of the node in the binary tree.
     */
    ExperimentNode rightChild;
    
    /**
     * The left child of the node in the binary tree.
     */
    ExperimentNode leftChild;
    
    /**
     * Constructs an ExperimentNode object representing an operand (variable).
     * 
     * @param passedVariable The variable to be stored in the node.
     */
    ExperimentNode(String passedVariable) {
        variable = passedVariable;
        isOperator = false;
    }
    
    /**
     * Constructs an ExperimentNode object representing an operator.
     * 
     * @param passedOperator The operator to be stored in the node.
     */
    ExperimentNode(char passedOperator) {
        rightChild = null;
        leftChild = null;
        operator = passedOperator;
        isOperator = false;
    }

}
