import java.util.*;

public class Tree<E extends Comparable<? super E>> {
    private BinaryTreeNode root;  // Root of tree
    private String name;     // Name of tree

    /**
     * Create an empty tree
     *
     * @param label Name of tree
     */
    public Tree(String label) {
        name = label;
    }

    /**
     * Create BST from ArrayList
     *
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public Tree(ArrayList<E> arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**
     * Create BST from Array
     *
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**
     * Return a string containing the tree contents as a tree with one node per line
     */
    public String toString() {
        String output = "";
        if (root == null) {
            return "Empty tree";
        }
        output += name + "\n";
        output += makeString(root, 0);

        return output;
    }

    private String makeString(BinaryTreeNode node, int level) {
        if (node == null) {
            return "";
        }
        String buffer = "";

        buffer += makeString(node.right, level+1);

        String indent = "   ";
        for (int i = 0; i < level; i++) {
            buffer += indent;
        }

        if (node.parent == null) {
            buffer += (node.key + " [no parent]\n");
        } else {
            buffer += (node.key + " [" + node.parent.key + "]\n");
        }

        buffer += (makeString(node.left, level+1));

        return buffer;
    }

    /**
     * Return a string containing the tree contents as a single line
     */
    public String inOrderToString() {
        if (root == null) {
            return "Empty tree";
        }
        String output = "";
        output += name + ": ";
        output += inOrder(root);

        return output;
    }

    private String inOrder(BinaryTreeNode node) {
        if (node == null) {
            return "";
        }
        String output = "";
        output += inOrder(node.left);
        output += node.key + " ";
        output += inOrder(node.right);

        return  output;
    }

    /**
     * reverse left and right children recursively
     */
    public void flip() {
        flipNode(root);
    }

    private void flipNode(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        flipNode(node.right);
        flipNode(node.left);

        BinaryTreeNode nodeTemp = node.left;
        node.left = node.right;
        node.right = nodeTemp;
    }

    /**
     * Returns the in-order successor of the specified node
     * @param node node from which to find the in-order successor
     */
    public BinaryTreeNode inOrderSuccessor(BinaryTreeNode node) {
        if (node.right != null) {
            return successorChild(node.right);
        } else if (node.parent == null) {
            return null;
        } else {
            return successorParent(node.parent, node);
        }
    }

    private BinaryTreeNode successorParent(BinaryTreeNode parentNode, BinaryTreeNode childNode) {
        if (parentNode.left == childNode) {
            return parentNode;
        }
        if (parentNode.parent == null) {
            return null;
        }
        return successorParent(parentNode.parent, parentNode);
    }

    private BinaryTreeNode successorChild(BinaryTreeNode node) {
        if (node.left == null) {
            return node;
        }
        return successorChild(node.left);
    }

    /**
     * Counts number of nodes in specified level
     *
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        return nodeLevel(level, 0, root);
    }

    private int nodeLevel(int levelTarget, int levelCurrent, BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        if (levelCurrent == levelTarget) {
            return 1;
        }
        return nodeLevel(levelTarget, levelCurrent+1, node.left) +
        nodeLevel(levelTarget, levelCurrent+1, node.right);
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        List<E> keys = new ArrayList<>();
        leafs(root, keys);
        for (E key : keys) {
            paths(root, key);
            System.out.println();
        }
    }

    private void paths(BinaryTreeNode node, E key) {
        if (node == null) {
            return;
        }

        System.out.print(node.key + " ");
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            paths(node.left, key);
        } else {
            paths(node.right, key);
        }
    }

    private List<E> leafs(BinaryTreeNode node, List<E> leaf) {
        if (node == null) {
            return null;
        }

        if (node.left == null && node.right == null) {
            leaf.add(node.key);
            return null;
        }
        leafs(node.left, leaf);
        leafs(node.right, leaf);
        return leaf;
    }



    /**
     * Counts all non-null binary search trees embedded in tree
     *
     * @return Count of embedded binary search trees
     */
    public int countBST() {
        return countBSTs(root);
    }
    private int countBSTs(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (isBST(node)) {
            count += 1;
        }
        count += countBSTs(node.left);
        count += countBSTs(node.right);

        return count;
    }

    private boolean isBST(BinaryTreeNode node) {
        return testBST(node, null, null);
    }

    private boolean testBST(BinaryTreeNode node, E min, E max) {
        if (node == null) {
            return true;
        }

        if ((min != null && node.key.compareTo(min) <= 0) || (max != null && node.key.compareTo(max) >= 0)) {
            return false;
        }

        return testBST(node.left, min, node.key) && testBST(node.right, node.key, max);
    }

    /**
     * Insert into a bst tree; duplicates are allowed
     *
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert(x, root, null);
    }

    public BinaryTreeNode getByKey(E key) {
        return contains(key, root);
    }

    private BinaryTreeNode getKey(E key, BinaryTreeNode node) {
        if (node == null) {
            return null;
        }
        BinaryTreeNode nodeTemp;
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            nodeTemp = getKey(key, node.left);
        } else {
            nodeTemp = getKey(key, node.right);
        }

        return nodeTemp;
    }

    /**
     * Balance the tree
     */
    public void balanceTree() {
        if (root == null) {
            return;
        }
        List<E> result = new ArrayList<>();
        inOrderNodes(root, result);
        root = null;
        balance(result, 0, result.size()-1);
    }

    private void inOrderNodes(BinaryTreeNode node, List<E> result) {
        if (node == null) {
            return;
        }
        inOrderNodes(node.left, result);
        result.add(node.key);
        inOrderNodes(node.right, result);
    }

    private void balance(List<E> keys, int low, int high) {
        if (low > high) {
            return;
        }
        int mid = (low + high) / 2;
        root = insert(keys.get(mid), root, null);
        balance(keys, low, mid-1);
        balance(keys, mid+1, high);
    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryTreeNode insert(E x, BinaryTreeNode t, BinaryTreeNode parent) {
        if (t == null) {
            return new BinaryTreeNode(x, null, null, parent);
        }

        int compareResult = x.compareTo(t.key);
        if (compareResult < 0) {
            t.left = insert(x, t.left, t);
        } else {
            t.right = insert(x, t.right, t);
        }

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private BinaryTreeNode contains(E x, BinaryTreeNode t) {
        if (t == null)
            return null;

        int compareResult = x.compareTo(t.key);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else {
            return t;    // Match
        }
    }

    // Basic node stored in unbalanced binary trees
    public class BinaryTreeNode {
        E key;            // The data/key for the node
        BinaryTreeNode left;   // Left child
        BinaryTreeNode right;  // Right child
        BinaryTreeNode parent; //  Parent node

        // Constructors
        BinaryTreeNode(E theElement) {
            this(theElement, null, null, null);
        }

        BinaryTreeNode(E theElement, BinaryTreeNode lt, BinaryTreeNode rt, BinaryTreeNode pt) {
            key = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(key);
            if (parent == null) {
                sb.append("<>");
            } else {
                sb.append("<");
                sb.append(parent.key);
                sb.append(">");
            }

            return sb.toString();
        }
    }
}
