/**
 * The TableNode class represents a node used in the linked list within the
 * Table class. Each node stores an expression (string), a numerical value, and
 * a reference to the next node.
 *
 * <p>Provides methods to access and modify the expression and numerical value.
 * The linked list structure allows multiple elements with the same hash code
 * to be stored in a single slot of the hash table.
 * 
 * @author ColinKula
 */

public class TableNode {
    
    // Fields
    
    /**
     * The expression (string) stored in the node.
     */
    String expression;
    
    /**
     * The numerical value associated with the expression.
     */
    int value;
    
    /**
     * Reference to the next node in the linked list.
     */
    TableNode next;

    // Constructors
    
    /**
     * Constructs a TableNode object with a null reference to the next node.
     */
    public TableNode() {
        this.next = null;
    }
    
    // Accessor and Mutator Methods

    /**
     * Gets the expression stored in the node.
     *
     * @return The expression stored in the node.
     */
    public String getExpression() {
        return expression;
    }
    
    /**
     * Sets the expression in the node to the specified value.
     *
     * @param expression The expression to be set in the node.
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }
    
    /**
     * Gets the numerical value stored in the node.
     *
     * @return The numerical value stored in the node.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the numerical value in the node to the specified value.
     *
     * @param value The numerical value to be set in the node.
     */
    public void setValue(int value) {
        this.value = value;
    }

}
