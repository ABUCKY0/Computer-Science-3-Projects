/**
 * A class representing a circular doubly linked list with various operations.
 * Extends the DLinkedlist class.
 */
public class CDLinkedlist extends DLinkedlist {

    /**
     * Constructs a circular doubly linked list with a single node containing the given data.
     * Overrides the constructor in the superclass.
     * Time Complexity: O(1)
     *
     * @param data The data for the initial node.
     * @example
     * CDLinkedlist cdl = new CDLinkedlist(5);
     * cdl.addFront(6);
     * cdl.addBack(8);
     * System.out.println(cdl); // Output: 6 <-> 5 <-> 8
     */
    public CDLinkedlist(Integer data) {
        super(data);
        Node n = new Node(data);
        this.head = n;
        this.tail = n;

        this.tail.next = this.head;
        this.head.prev = this.tail;
    }

    /**
     * Constructs an empty circular doubly linked list.
     * Overrides the constructor in the superclass.
     * Time Complexity: O(1)
     */
    public CDLinkedlist() {
        super();
    }

    /**
     * Adds a new node with the given data to the front of the circular doubly linked list.
     * Overrides the addFront method in the superclass.
     * Time Complexity: O(1)
     *
     * @param data The data for the new node.
     * @example
     * CDLinkedlist cdl = new CDLinkedlist();
     * cdl.addFront(6);
     * cdl.addBack(8);
     * System.out.println(cdl); // Output: 6 <-> 5 <-> 8
     */
    @Override
    public void addFront(Integer data) {
        super.addFront(data);
        this.head.prev = this.tail;
    }

    /**
     * Adds a new node with the given data to the back of the circular doubly linked list.
     * Overrides the addBack method in the superclass.
     * Time Complexity: O(1)
     *
     * @param data The data for the new node.
     * @example
     * CDLinkedlist cdl = new CDLinkedlist();
     * cdl.addFront(6);
     * cdl.addBack(8);
     * System.out.println(cdl); // Output: 6 <-> 5 <-> 8
     */
    @Override
    public void addBack(Integer data) {
        super.addBack(data);
        this.tail.next = this.head;
    }

    /**
     * Returns a string representation of the circular doubly linked list.
     * Overrides the toString method in the superclass.
     * Time Complexity: O(n)
     *
     * @return The string representation of the circular doubly linked list.
     * @example
     * CDLinkedlist cdl = new CDLinkedlist(5);
     * cdl.addFront(6);
     * cdl.addBack(8);
     * System.out.println(cdl); // Output: 6 <-> 5 <-> 8
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
                s.append(" <-> ");
                tmp = tmp.next;
            }
            s.append(tmp.data);
            return s.toString();
        }
    }

    /**
     * The main method for testing the functionality of the CDLinkedlist class.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        CDLinkedlist cdl = new CDLinkedlist(5);
        cdl.addFront(6);
        cdl.addBack(8);
        System.out.println(cdl);
    }
}
