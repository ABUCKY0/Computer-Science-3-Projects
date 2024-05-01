package Tree.Java;
@SuppressWarnings("unchecked")
public class BST<E extends Comparable<E>> extends Tree<E> {

    public BST(E data){
        super(data);
        super.size = 1;
    }
    
    public BST(){
        super(null);
        super.size = 0;
    }
    
    public void add(E data){
        this.addRecur(super.root, data);
        super.size += 1;
    }
    
    public TreeNode<E> addRecur(TreeNode<E> root, E data){
        layer = 0;
        if(root == null){
            root = super.createNode(data);
            root.setLayer(layer);
        }
        else if(root.compareTo(super.createNode(data)) >= 0){
            root.left = addRecur(root.left, data);
            layer += 1;
            root.setLayer(layer);
        }
        else if(root.compareTo(super.createNode(data)) < 0){
            root.right = addRecur(root.right, data);
            layer += 1;
            root.setLayer(layer);
        }
        return root; // Return the modified root
    }
    
    public String displayLayer(int layerToDisplay) {
        return displayLayerHelper(super.root, layerToDisplay);
}

    private String displayLayerHelper(TreeNode<E> root, int currentLayer) {
        StringBuilder result = new StringBuilder();
        if (root == null)
            return result.toString();
        if (currentLayer == 1)
            result.append(root.data).append(" ");
        else if (currentLayer > 1) {
            result.append(displayLayerHelper(root.left, currentLayer - 1));
            result.append(displayLayerHelper(root.right, currentLayer - 1));
        }
        return result.toString();
    }

    
    public static void main(String[] args){
        BST<Integer> tree = new BST<>(10);
        tree.add(5);
        tree.add(12);
        tree.add(50);
        tree.add(7);
        tree.add(6);
        tree.add(9);
        tree.add(45);
        tree.add(53);
        tree.add(56);
        
        // Display nodes at layer 3 for 4 times
        for(int i = 1; i < 6; i++) {
            System.out.println("Layer " + (i - 1) + ": " + tree.displayLayer(i));
        }
        System.out.println("\nIn Order Traversal: ");
        tree.printInOrder();
    }
}