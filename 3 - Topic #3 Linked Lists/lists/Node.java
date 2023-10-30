package lists;
public class Node
{
    public Integer data;
    public Node next;
    public Node prev;

    public Node(Integer data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString() {
        return this.data+" ";
    }
}