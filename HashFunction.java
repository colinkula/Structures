/**
 * The HashFunction class represents a simple hash function used for mapping
 * expressions to hash codes and compressing those hash codes to fit within a
 * specified modulo. It is designed to be used in conjunction with the
 * HashBasedSet class.
 * 
 * @author ColinKula
 */

public class HashFunction {

    // Fields
    
    /**
     * The expression to be hashed.
     */
    String expression;
    
    /**
     * The modulo used for compressing the hash code.
     */
    int modulo;
    
    // Constructors
    
    /**
     * Constructs a HashFunction with the specified modulo, ensuring that
     * modulo is at least 1.
     * 
     * @param modulo The modulo value for compressing the hash code.
     */
    public HashFunction(int modulo) {
        this.modulo = modulo;
    }
    
    // Methods
    
    /**
     * Sets the expression to be hashed.
     * 
     * @param str The expression to be hashed.
     */
    public void setExpression(String str) {
        expression = str;         
    }
    
    /**
     * Computes the hash code for the current expression using a custom
     * hashing algorithm.
     * 
     * @return The computed hash code.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        final int primeMultiplier = 31;
                 
        for (int i = 0; i < expression.length(); i++) {             
            hash += hash * primeMultiplier + expression.charAt(i);
        }
        
        return hash;       
    }
    
    /**
     * Compresses the given hash code to fit within the specified modulo.
     * 
     * @param hash The original hash code to be compressed.
     * @return The compressed hash code.
     */

    public int compress(int hash) {
        int newHash = hash % modulo;
       
        if (newHash < 0) {
            newHash += modulo;
        }
     
        return newHash;
    }
   
    /**
     * Computes the final hash code for the current expression by applying
     * the compression function to the hash code.
     * 
     * @return The final hash code.
     */
    public int hash() {
        return compress(hashCode());
    }
    

}
