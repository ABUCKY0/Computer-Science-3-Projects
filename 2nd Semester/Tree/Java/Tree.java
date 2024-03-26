import java.util.*;

@SuppressWarnings("unchecked")
public class Tree<E> {
    public int size;
    public int layer;
    public TreeNode<E> root;
    private ArrayList<TreeNode> children;

    public Tree(E data) {
        this.root = new TreeNode<>(data);
        this.root.setLayer(0);
        this.children = new ArrayList<TreeNode>();
        this.children.add(this.root);
        this.size = 1;
        this.layer = 0;
    }

    public Tree() {
        this.root = null;
        this.children = new ArrayList<TreeNode>();
        this.size = 0;
        this.layer = 0;
    }

    public TreeNode<E> getRoot() {
        return this.root;
    }

    public int getSize() {
        return this.size;
    }

    public int getNodeLevel(TreeNode node) {
        int index = -1;
        // Find the index of the node with the given data
        for (int i = 0; i < this.children.size(); i++) {
            if (this.children.get(i).getData().equals(node.data)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            // Node with the given data not found in the tree
            return -1;
        }

        // Calculate the level of the node based on its index
        int level = 0;
        while (index >= getMaxNodes(level)) {
            level++;
        }
        return level;
    }

    public int getMaxNodes(int level) {
        int sum = 0;
        for (int i = 0; i < level + 1; i++) {
            sum = (int) (sum + Math.pow(2, i));
        }
        return sum;
    }

    public void add(E data) {

        if (this.children.size() == 0) {
            this.root = new TreeNode<>(data);
            this.root.setLayer(0);
            this.children.add(this.root);
        } else {
            TreeNode<E> parent = this.children.get((this.size - 1) / 2); // Get the parent node
            TreeNode<E> newNode = new TreeNode<>(data);
            if (!parent.hasLeft()) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            newNode.setLayer(parent.getLayer() + 1);
            this.children.add(newNode);
        }

        this.size += 1;
    }

    public String displayLayer(int layer) {
        String s = "";
        for (TreeNode node : this.children) {
            if (this.getNodeLevel(node) == layer)
                s = s + node.getData() + " ";
        }
        return s;
    }

    public TreeNode createNode(E data) {
        TreeNode node = new TreeNode(data);
        return node;
    }

    public boolean search(E target) {
        return search(this.root, target);
    }

    private boolean search(TreeNode<E> node, E target) {
        if (node == null)
            return false;

        if (node.getData().equals(target))
            return true;

        // Recursively search in the left and right subtrees
        boolean foundLeft = search(node.left, target);
        boolean foundRight = search(node.right, target);

        // Return true if the target is found in either subtree
        return foundLeft || foundRight;
    }

    public int countLeaves() {
        return countLeaves(this.root);
    }

    private int countLeaves(TreeNode<E> node) {
        if (node == null)
            return 0;

        if (!node.hasLeft() && !node.hasRight())
            return 1;

        int leftLeaves = countLeaves(node.left);
        int rightLeaves = countLeaves(node.right);

        return leftLeaves + rightLeaves;
    }

    public Tree copy() {
        return this;
    }

    public void clear() {
        this.children.clear();
        this.root = null;
    }

    public boolean hasPathSum(int amount) {
        return false;
    }

    public void printInOrder() {
        printInOrder(this.root);
    }

    private void printInOrder(TreeNode<E> node) {
        if (node == null)
            return;

        printInOrder(node.left);
        System.out.print(node.getData() + " ");
        printInOrder(node.right);
    }

    public static void main(String[] args) {
        Tree t = new Tree("1");
        t.add("2");
        t.add("3");
        t.add("4");
        t.add("5");
        t.add("6");
        t.add("7");
        t.add("8");
        t.add("9");

        System.out.println("\nPrint the List: ");
        t.printInOrder();

        t.clear();

        System.out.println("\n\nCleared the list...");
        t.printInOrder();
    }

}