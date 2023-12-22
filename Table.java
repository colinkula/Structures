import java.util.ArrayList;

/**
 * The Table class represents a table data structure used in conjunction with
 * the HashBasedSet class for organizing elements based on their hash codes.
 * 
 * <p>The table consists of an array of TableNode objects, where each node may
 * contain a linked list of elements with the same hash code. The class provides
 * methods for adding nodes, displaying the contents of the table, checking
 * whether a variable is present, retrieving the numerical value of a variable,
 * and obtaining all numerical values stored in the table.
 * 
 * @author ColinKula
 */

public class Table {
    // Fields

    /**
     * The size of the table, determined by the hash set size.
     */
    int size;

    /**
     * An array of TableNode objects, representing the table structure.
     */
    TableNode []table;

    /**
     * An array storing the number of nodes in each slot of the table.
     */
    int []numberOfNodes;

    // Constructors

    /**
     * Constructs a Table object with the specified size. Initializes the table
     * array and the numberOfNodes array, setting up the initial state of the
     * table structure.
     *
     * @param size The size of the table.
     */
    public Table(int size) {
        this.size = size;
        table = new TableNode[size];
        numberOfNodes = new int[size];

        for (int i = 0; i < size; i++) {
            table[i] = new TableNode();
            numberOfNodes[i] = 0;
        }
    }

    // Methods

    /**
     * Adds a TableNode to the table at the specified index, updating the linked
     * list of nodes in that position.
     *
     * @param node  The TableNode to be added.
     * @param index The index in the table array where the node should be added.
     */
    public void add(TableNode node, int index) {
        node.next = table[index].next;
        table[index].next = node;
        numberOfNodes[index]++;
    }

    /**
     * Displays the contents of the table, including expressions, hash indices,
     * and numerical values associated with each expression.
     */
    public void show() {
        TableNode ptr = new TableNode();

        for (int i = 0; i < size; i++) {
            if (!table[i].equals(null)) {
                ptr = table[i];
                for (int j = 0; j < numberOfNodes[i]; j++) {
                    ptr = ptr.next;
                    System.out.println("(Hash Index: " + i
                            + ", Expression: " + ptr.expression
                            + ", Numerical Value: "
                            + ptr.getValue() + ")");
                }
            }
        }
    }

    /**
     * Checks whether the table contains a variable with the specified name.
     *
     * @param variable The variable name to check for.
     * @return true if the variable is found in the table; false otherwise.
     */
    public boolean contains(String variable) {
        for (int i = 0; i < size; i++) {
            TableNode ptr = table[i].next; // Skip the dummy node

            for (int j = 0; j < numberOfNodes[i]; j++) {
                if (ptr.expression.equalsIgnoreCase(variable)) {
                    return true; // Variable found
                }
                ptr = ptr.next;
            }
        }
        return false; // Variable not found
    }

    /**
     * Retrieves the numerical value associated with the specified variable from
     * the table.
     *
     * @param variable The variable name to retrieve the value for.
     * @return The numerical value associated with the variable, or -1.0 if the
     *         variable is not found.
     */
    public double getValueFor(String variable) {
        for (int i = 0; i < size; i++) {
            TableNode ptr = table[i].next; // Skip the dummy node
            for (int j = 0; j < numberOfNodes[i]; j++) {
                if (ptr.expression.equalsIgnoreCase(variable)) {
                    // Variable found, return its val
                    return (double) ptr.getValue(); 
                }
                ptr = ptr.next;
            }
        }
        return -1.0;
    }

    /**
     * Retrieves all numerical values stored in the table, returning them as an
     * ArrayList.
     *
     * @return An ArrayList containing all numerical values stored in the table.
     */
    public ArrayList<Double> getAllValues() {
        ArrayList<Double> allValues = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            TableNode ptr = table[i].next; // Skip the dummy node

            for (int j = 0; j < numberOfNodes[i]; j++) {
                allValues.add((double) ptr.getValue());
                ptr = ptr.next;
            }
        }

        return allValues;
    }

}
