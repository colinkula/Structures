import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The HashBasedSet class represents a set using a hash map for efficient
 * storage and retrieval of key-value pairs. It maintains a hash map and a table
 * structure for organizing and managing elements in the set.
 * 
 * <p>The key-value pairs consist of strings as keys and integer values. The
 * class provides methods for updating, removing, and displaying elements in
 * the set.
 * 
 * <p>The set is initialized by reading values from a file named "Values.txt"
 * and creating a hash map with keys as expressions (strings) and values as 
 * integers. The set size is determined based on the number of lines in 
 * "Values.txt".
 * 
 * <p>The class uses a hash map (HashMap) for efficient key-value storage and a
 * custom table structure (Table) for organizing elements based on their hash
 * codes.
 * 
 * <p>The set can be updated and modified using methods to add, remove, and
 * display elements. The internal representation of the set is synchronized
 * between the hash map and the table.
 * 
 * @author ColinKula
 */

public class HashBasedSet {

    // Fields
    
    /**
     * The hash map used for storing key-value pairs, where keys are expressions
     * (strings) and values are integers.
     */
    HashMap<String, Integer> map;
    
    /**
     * The size of the set, determined by the number of elements in
     * "Values.txt".
     */
    int size;
    
    /**
     * The table structure used for organizing elements based on their
     * hash codes.
     */
    Table table;
    
    
    // Constructors

    /**
     * Constructs a HashBasedSet object. Initializes the hash map, set size, and
     * table.
     */
    public HashBasedSet() {
        this.map = new HashMap();
        this.size = getTextSize();
        this.table = new Table(size);
    }
    
    // Methods

    /**
     * Reads values from "Values.txt" and creates a hash map with expressions as
     * keys and values as integers. The key-value pairs are updated in the
     * internal map.
     *
     * @return The hash map containing expressions as keys and corresponding
     *         integer values.
     */
    public HashMap<String, Integer> createMap() {
        HashMap<String, Integer> valuesMap = new HashMap<>();
        int value = 1;
        
        try {
            Scanner in = new Scanner(new File("Values.txt"));
            while (in.hasNextLine()) {
                String key = in.nextLine();
                update(key, value++);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.err.println("Values.txt was not found");
        }
        
        return valuesMap;
    }
    
    /**
     * Retrieves the size of the set based on the number of lines in
     * "Values.txt".
     *
     * @return The size of the set.
     */
    public int getTextSize() {
        int size = 0;
        try {
            Scanner in = new Scanner(new File("Values.txt"));
            while (in.hasNextLine()) {
                size++;
                in.nextLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.err.println("Values.txt was not found");
        }

        return size;
    }

    /**
     * Updates the internal hash map with a new key-value pair without
     * synchronizing the table representation. This method should be used
     * carefully to avoid inconsistencies between the hash map and the table.
     *
     * @param key   The expression (string) key.
     * @param value The integer value associated with the key.
     */
    public void updateInMap(String key, int value) {
        map.put(key, value);
    }
    
    /**
     * Removes an element with the specified key from the internal hash map
     * without synchronizing the table representation. This method should be
     * used carefully to avoid inconsistencies between the hash map and the
     * table.
     *
     * @param key The expression (string) key to be removed.
     */
    public void removeInMap(String key) {
        map.remove(key);
    }
    
    /**
     * Updates the internal hash map with a new key-value pair and synchronizes
     * the table representation.
     *
     * @param key   The expression (string) key.
     * @param value The integer value associated with the key.
     */
    public void update(String key, int value) {
        updateInMap(key, value);
        mapToTable();
    }
    
    /**
     * Removes an element with the specified key from the set and synchronizes
     * the table representation.
     *
     * @param key The expression (string) key to be removed.
     */
    public void remove(String key) {
        map.remove(key);
        mapToTable();
    }
    
    /**
     * Converts the elements in the hash map to a table representation based on
     * their hash codes. Synchronizes the internal table with the hash map.
     */
    public void mapToTable() { 
        table = new Table(size);
        HashFunction hashObject = new HashFunction(size);
        hashObject.setExpression(null);
        TableNode node;

        for (String expression : map.keySet()) {
            String variable = "";
            int i = 0;
            while (expression.charAt(i) != ' ') {
                variable += expression.charAt(i);
                i++;
            }
            hashObject.setExpression(variable);
            node = new TableNode();
            node.setExpression(variable);
            node.setValue(Integer.parseInt(expression.substring(i + 3)));  
            table.add(node, hashObject.hash());
        }        
    }
    
    /**
     * Displays the contents of the set using the internal table structure.
     */
    public void show() {
        table.show();
    }

}
