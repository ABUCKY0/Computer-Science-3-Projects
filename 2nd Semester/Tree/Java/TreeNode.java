@SuppressWarnings("unchecked")
public class TreeNode<E> implements Comparable<TreeNode<E>> {
    E data;
    int layer;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E data) {
        this.data = data;
        this.layer = 0;
        this.left = null;
        this.right = null;
    }

    public E getData() {
        return this.data;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    public int getLayer() {
        return this.layer;
    }

    public void setLayer(int l) {
        this.layer = l;
    }

    @Override
    public int compareTo(TreeNode<E> value) {
        // Assuming E is a Comparable type, you can directly compare the data
        if (this.data instanceof Comparable && value.data instanceof Comparable) {
            return ((Comparable<E>) this.data).compareTo(value.data);
        }
        throw new IllegalArgumentException("The data does not implement Comparable");
    }

    public String toString() {
        return this.data + " ";
    }
}