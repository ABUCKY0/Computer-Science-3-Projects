/**
 * A class representing a circular singly linked list with various operations.
 * Extends the SLinkedlist class.
 */
public class CSLinkedlist extends SLinkedlist {

    /**
     * Constructs a circular singly linked list with a single node containing the given data.
     * Overrides the constructor in the superclass.
     * Time Complexity: O(1)
     *
     * @param data The data for the initial node.
     * @example
     * CSLinkedlist csl = new CSLinkedlist(5);
     * csl.addFront(6);
     * csl.addBack(8);
     * System.out.println(csl); // Output: 6 -> 5 -> 8
     */
    public CSLinkedlist(Integer data) {
        super(data);
        Node n = new Node(data);
        this.head = n;
        n.next = this.head;
    }

    /**
     * Constructs an empty circular singly linked list.
     * Overrides the constructor in the superclass.
     * Time Complexity: O(1)
     */
    public CSLinkedlist() {
        super();
    }

    /**
     * Adds a new node with the given data to the back of the circular singly linked list.
     * Overrides the addBack method in the superclass.
     * Time Complexity: O(n)
     *
     * @param data The data for the new node.
     * @example
     * CSLinkedlist csl = new CSLinkedlist();
     * csl.addFront(6);
     * csl.addBack(8);
     * System.out.println(csl); // Output: 6 -> 5 -> 8
     */
    @Override
    public void addBack(Integer data) {
        Node n = this.createNode(data);
        Node tmp = this.head;
        for (int i = 0; i < this.size; i++) {
            tmp = tmp.next;
        }
        tmp.next = n;
        n.next = this.head;
        this.size++;
    }

    /**
     * Returns a string representation of the circular singly linked list.
     * Overrides the toString method in the superclass.
     * Time Complexity: O(n)
     *
     * @return The string representation of the circular singly linked list.
     * @example
     * CSLinkedlist csl = new CSLinkedlist(5);
     * csl.addFront(6);
     * csl.addBack(8);
     * System.out.println(csl); // Output: 6 -> 5 -> 8
     */
    @Override
    public String toString() {
        if (this.size == 0) {
            return null;
        } else {
            StringBuilder s = new StringBuilder();
            Node tmp = this.head;
            for (int i = 0; i < this.size - 1; i++) {
                s.append(tmp.data);
                s.append("->");
                tmp = tmp.next;
            }
            s.append(tmp.data);
            return s.toString();
        }
    }

    /**
     * The main method for testing the functionality of the CSLinkedlist class.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        CSLinkedlist csl = new CSLinkedlist(5);
        csl.addFront(6);
        csl.addBack(8);
        System.out.println(csl);
    }
}
