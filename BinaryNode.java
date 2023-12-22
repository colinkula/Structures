/**
 * The BinaryNode class represents a node in a binary search tree (BST)
 * used by the BinarySearchTree class. Each node contains a numerical value
 * and references to its left and right children.
 * 
 * @author ColinKula
 */

public class BinaryNode {
    
    // Fields
    
    /**
     * The numerical value stored in the node.
     */
    double value;
    
    /**
     * The right child of the node in the binary tree.
     */
    BinaryNode rightChild;
    
    /**
     * The left child of the node in the binary tree.
     */
    BinaryNode leftChild;
    
    // Constructors

    /**
     * Constructs a BinaryNode with the specified numerical value and
     * null references to its left and right children.
     * 
     * @param value The numerical value to be stored in the node.
     */
    BinaryNode(double value) {
        this.value = value;
        rightChild = null;
        leftChild = null;
    }
    
    // Methods
    
    /**
     * Retrieves the numerical value stored in the node.
     * 
     * @return The numerical value of the node.
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the numerical value of the node.
     * 
     * @param value The new numerical value to be set.
     */
    public void setValue(double value) {
        this.value = value;
    }

}
