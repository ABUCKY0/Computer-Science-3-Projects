/**
 * Node class represents a node in a doubly linked list.
 */
public class Node {
    /**
     * Integer data stored in the node.
     */
    public Integer data;

    /**
     * Reference to the next node in the list.
     */
    public Node next;

    /**
     * Reference to the previous node in the list.
     */
    public Node prev;

    /**
     * Constructs a new node with the given data.
     *
     * @param data Integer data to be stored in the node.
     * @TimeComplexity O(1)
     */
    public Node(Integer data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * Returns a string representation of the node.
     *
     * @return String representation of the node's data.
     * @TimeComplexity O(1)
     */
    @Override
    public String toString() {
        return this.data + " ";
    }

    /**
     * Example Usage:
     * <pre>
     * {@code
     * Node node = new Node(42);
     * System.out.println(node.toString()); // Output: 42
     * }
     * </pre>
     */

    /**
     * Example Usage:
     * <pre>
     * {@code
     * Node node = new Node(42);
     * Node nextNode = new Node(23);
     * node.next = nextNode;
     * System.out.println(node.next.toString()); // Output: 23
     * }
     * </pre>
     */

    /**
     * Example Usage:
     * <pre>
     * {@code
     * Node node = new Node(42);
     * Node prevNode = new Node(23);
     * node.prev = prevNode;
     * System.out.println(node.prev.toString()); // Output: 23
     * }
     * </pre>
     */
}
