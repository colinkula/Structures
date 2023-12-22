import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Main class contains the main method and various utility methods to
 * perform experiments on mathematical expressions, evaluate them, and create
 * binary search trees based on the evaluation results.
 * 
 * @author ColinKula
 */
public class Main {

    public static void main(String[] args) {
        
        // Initialize and display a hash-based set
        HashBasedSet set = new HashBasedSet();
        set.createMap();
        set.show();
        
        // Make experiments, create expression trees, and evaluate
        ArrayList<InfixPostfix> experiments = makeExperiments();
        ArrayList<ExperimentTree> experimentTrees = makeExperimentTrees(experiments);
        ArrayList<Double> experimentEvaluations = new ArrayList<Double>();
        
        // Perform experiments and display results
        for (int i = 0; i < experimentTrees.size(); i++) {
            System.out.println("\nExperiment #" + (i + 1) + ":");
            System.out.println("Infix Notation: " + experiments.get(i).infixPresentation);
            System.out.print("Prefix Notation: "); experimentTrees.get(i).printPrefix();
            System.out.println("\nPostfix Notation: " + experiments.get(i).postfixRepresentation);
            
            // Evaluate the expression and display the result
            experimentEvaluations.add(experimentTrees.get(i).evaluatePostfix(set));
            System.out.println("Evaluation = " + experimentTrees.get(i).evaluatePostfix(set));
        }
        
        // Create and display a binary search tree based on experiment evaluations
        BinarySearchTree binarySearchTree = makeBinarySearchTree(experimentEvaluations, experiments.size());
        binarySearchTree.display();
    }
    
    /**
     * Reads experiments from a file and creates a list of InfixPostfix objects.
     *
     * @return ArrayList of InfixPostfix objects representing mathematical expressions.
     */
    public static ArrayList<InfixPostfix> makeExperiments() {

        ArrayList<InfixPostfix> experiments = new ArrayList<InfixPostfix>() ;       
        try {
            Scanner in = new Scanner(new File("Experiments.txt"));
            while (in.hasNextLine()) {
                String currentLine = in.nextLine();
                if (currentLine != "") {
                    InfixPostfix experiment = new InfixPostfix(currentLine);
                    experiments.add(experiment);
                }
            }
            
            in.close();
        } catch (FileNotFoundException e) {
            System.err.println("Experiments.txt was not found");
        }

        return experiments;
    }
    
    /**
     * Creates ExperimentTree objects from a list of InfixPostfix expressions.
     *
     * @param experiments ArrayList of InfixPostfix objects.
     * @return ArrayList of ExperimentTree objects representing expression trees.
     */
    public static ArrayList<ExperimentTree> makeExperimentTrees(ArrayList<InfixPostfix> experiments) {
        
        ArrayList<ExperimentTree> experimentTrees = new ArrayList<ExperimentTree>();

        for (int i = 0; i < experiments.size(); i++) {
            if (experiments.get(i).postfixRepresentation != "") {
                ExperimentTree expressionTree = new ExperimentTree(experiments.get(i).postfixRepresentation);
                expressionTree.createTree();
                experimentTrees.add(expressionTree);
            }
        }
        return experimentTrees;
    }
    
    /**
     * Creates binary search tree based on a list of experiment evaluations.
     *
     * @param experimentEvaluations ArrayList of Double values representing experiment evaluations.
     * @param size                  The size of the experiments.
     * @return BinarySearchTree object containing the binary search tree.
     */
    private static BinarySearchTree makeBinarySearchTree(ArrayList<Double> experimentEvaluations, int size) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int i = 0; i < size; i++) {
            tree.insert(new BinaryNode(experimentEvaluations.get(i)));
        }
        
        return tree;
    }
       
}
