public class BinarySearchTree<E extends Comparable<E>> {
    private TreeNode root;

    public void display(String message) {
        System.out.println(message);
        this.displayInOrder(root);
    }

    private void displayInOrder(TreeNode node) {
        if (node == null) return;

        displayInOrder(node.left);
        System.out.println(node.value);
        displayInOrder(node.right);
    }

    public boolean search(E search) {
        TreeNode node = root;

        // Traverse tree until the end or found
        boolean found = false;
        while (!found && node != null) {
            if (node.compareTo(search) == 0) {
                found = true;
            } else if (node.compareTo(search) < 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        return found;
    }

    public boolean insert(E value) {
        if (root == null) {
            root = new TreeNode(value);
        }
        else {
            // Search/find the insert location
            TreeNode parent = null;
            TreeNode node = root;

            while (node != null) {
                parent = node;
                if (node.compareTo(value) == 0) {
                    return false;
                } else if (node.compareTo(value) < 0) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }

            // Add the node to the tree
            TreeNode newNode = new TreeNode(value);
            if (parent.compareTo(value) == 0) {
                return false;
            }
            else if (parent.compareTo(value) > 0) {
                parent.left = newNode;
            }
            else {
                parent.right = newNode;
            }
        }
        return true;
    }

    public boolean remove(E value) {
        // Step 1: find the node to remove
        TreeNode parent = null;
        TreeNode node = root;
        boolean done = false;

        // Finding node position
        while (!done) {
            // If found
            if (node.compareTo(value) == 0){
                done = true;
            }
            // No node to remove
            else if (node.left == null && node.right == null) {
                return false;
            }
            else if (node.compareTo(value) > 0) {
                parent = node;
                node = node.left;
            }
            else if (node.compareTo(value) < 0) {
                parent = node;
                node = node.right;
            }
        }

        // Step 2a: case for no left child
        if (node.left == null) {
            if (parent == null) {
                root = node.right;
            } else {
                // checking where the node is getting deleted relative to the parent
                if (parent.compareTo(value) < 0) {
                    parent.left = node.right;
                } else {
                    parent.right = node.right;
                }
            }
        }
        else {
            // Step 2b: case for left child
            TreeNode parentOfRight = node;
            TreeNode rightMost = node.left;

            // Finding right most node
            while (rightMost.right != null) {
                parentOfRight = rightMost;
                rightMost = rightMost.right;
            }

            // Copy the largest value into the node being removed
            node.value = rightMost.value;
            if (parentOfRight.right == rightMost) {
                parentOfRight.right = rightMost.left;
            }
            else {
                parentOfRight.left = rightMost.left;
            }
        }
        return true;
    }

    public int numberNodes() {
        return (numNodes(root));
    }

    private int numNodes(TreeNode node) {
        if (node == null) return 0;

        int result = 1;

        result += numNodes(node.left);
        result += numNodes(node.right);
        return result;
    }

    public int numberLeafNodes() {
        return numLeafNodes(root);
    }

    private int numLeafNodes(TreeNode node) {
        if (node.left == null && node.right == null) {
            return 1;
        }
        int result = 0;

        // Go to each child node if it is not null
        if (node.left != null) {
            result += numLeafNodes(node.left);
        }
        if (node.right != null){
            result += numLeafNodes(node.right);
        }
        return result;
    }

    public int height() {
        return recursionheight(root);
    }

    private int recursionheight(TreeNode node) {
        if (node.left == null && node.right == null) {
            return 0;
        }
        int depthRight = 0;
        int depthLeft = 0;
        // Go to each child node if it is not null
        if (node.right != null) {
            depthRight += recursionheight(node.right) + 1;
        }
        if (node.left != null) {
            depthLeft += recursionheight(node.left) + 1;
        }

        return Math.max(depthLeft, depthRight);
    }

    private class TreeNode {
        public E value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(E value) {
            this.value = value;
        }

        public int compareTo(E o) {
            if (this.value.compareTo(o) == 0) {
                return 0;
            } else if (this.value.compareTo(o) > 0) {
                return 1;
            }

            return -1;
        }
    }
}
