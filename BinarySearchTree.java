/**
 * The BinarySearchTree class represents a binary search tree (BST) data 
 * structure that stores values in a sorted order. It provides methods for
 * inserting nodes, displaying the tree in an inorder representation, searching
 * for a specific value, and removing nodes.
 * 
 * <p>The tree is built using BinaryNode objects, where each node has a 
 * numerical value and references to its left and right children.
 * 
 * @author ColinKula
 */

public class BinarySearchTree {   
    
    // Fields
     
    /**
     * The root node of the binary search tree.
     */
    BinaryNode root;
    
    // Constructors
    
    /**
     * Constructs an empty BinarySearchTree with a null root.
     */        
    BinarySearchTree() {
        root = null;
    }   
    
    // Methods
    
    /**
     * Inserts a new BinaryNode into the binary search tree.
     * 
     * @param node The BinaryNode to be inserted.
     */
    public void insert(BinaryNode node) {
        root = insertHelper(root, node);
    }
    
    /**
     * Recursively inserts a BinaryNode into the binary search tree.
     * 
     * @param root The current root of the subtree.
     * @param node The BinaryNode to be inserted.
     * @return The root of the modified subtree.
     */
    private BinaryNode insertHelper(BinaryNode root, BinaryNode node) {
        double value = node.value;
        
        if (root == null) {
            root = node;
            return node;
        } else if (value < root.value) {
            root.leftChild = insertHelper(root.leftChild, node);
        } else {
            root.rightChild = insertHelper(root.rightChild, node);
        }
        
        return root;
    }
    
    /**
     * Displays the inorder representation of all evaluations in the tree.
     */
    public void display() {
        System.out.println("\nInorder representation of all evaluations: ");
        displayHelper(root);
    }
    
    /**
     * Recursively displays the inorder representation of the subtree rooted at
     * the given node.
     * 
     * @param root The root of the current subtree.
     */
    private void displayHelper(BinaryNode root) {
        if (root != null) {
            displayHelper(root.leftChild);
            System.out.print(root.value + " ");
            displayHelper(root.rightChild);
        } 
    }
    
    /**
     * Searches for a specific value in the binary search tree.
     * 
     * @param value The value to search for.
     * @return true if the value is found; false otherwise.
     */
    public boolean search(double value) {
        return searchHelper(root, value);
    }
    
    /**
     * Recursively searches for a specific value in the subtree rooted at the
     * given node.
     * 
     * @param root  The root of the current subtree.
     * @param value The value to search for.
     * @return true if the value is found; false otherwise.
     */
    public boolean searchHelper(BinaryNode root, double value) {
        if (root == null) {
            return false;
        } else if (root.value == value) {
            return true;
        } else if (root.value > value) {
            return searchHelper(root.leftChild, value);
        } else {
            return searchHelper(root.rightChild, value);
        }
    }
    
    /**
     * Removes a node with the specified value from the binary search tree.
     * 
     * @param value The value to be removed.
     */
    public void remove(int value) {
        if (search(value)) {
            removeHelper(root, value);
        } 
    }
    
    /**
     * Recursively removes a node with the specified value from the subtree
     * rooted at the given node.
     * 
     * @param root  The root of the current subtree.
     * @param value The value to be removed.
     * @return The root of the modified subtree.
     */
    public BinaryNode removeHelper(BinaryNode root, double value) {
        if (root == null) {
            return root;
        } else if (value < root.value) {
            root.leftChild = removeHelper(root.leftChild, value);
        } else if (value > root.value) {
            root.rightChild = removeHelper(root.rightChild, value);
        } else {
            if (root.leftChild == null && root.rightChild == null) {
                root = null;
            } else if (root.rightChild != null) {
                root.value = successor(root);
                root.rightChild = removeHelper(root.rightChild, root.value);
            } else {
                root.value = predecessor(root);
                root.leftChild = removeHelper(root.leftChild, root.value);
            }
        }
        return root;
    }

    /**
     * Finds the successor of a given node in the binary search tree.
     * 
     * @param root The node for which the successor is to be found.
     * @return The value of the successor.
     */
    private double successor(BinaryNode root) {
        root = root.rightChild;
        
        while (root.leftChild != null) {
            root = root.leftChild;
        }
        
        return root.value;
    }
    
    /**
     * Finds the predecessor of a given node in the binary search tree.
     * 
     * @param root The node for which the predecessor is to be found.
     * @return The value of the predecessor.
     */
    private double predecessor(BinaryNode root) {
        root = root.leftChild;
        
        while (root.rightChild != null) {
            root = root.rightChild;
        }
        
        return root.value;
    }

    
}
