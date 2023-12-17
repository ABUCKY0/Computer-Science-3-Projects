/**
 * A class representing a doubly linked list with various operations.
 * Extends the SLinkedlist class.
 */
public class DLinkedlist extends SLinkedlist {

    /**
     * Reference to the tail node of the linked list.
     */
    public Node tail;

    /**
     * Constructs a doubly linked list with a single node containing the given data.
     * Time Complexity: O(1)
     *
     * @param data The data for the initial node.
     */
    public DLinkedlist(Integer data) {
        super(data);
        Node n = new Node(data);
        this.head = n;
        this.tail = n;
    }

    /**
     * Constructs an empty doubly linked list.
     * Time Complexity: O(1)
     */
    public DLinkedlist() {
        super();
        this.tail = null;
    }

    /**
     * Adds a new node with the given data to the front of the doubly linked list.
     * Overrides the addFront method in the superclass.
     * Time Complexity: O(1)
     *
     * @param data The data for the new node.
     * @example
     *          DLinkedlist dll = new DLinkedlist();
     *          dll.addFront(10);
     *          dll.addFront(5);
     *          System.out.println(dll); // Output: 5 <-> 10
     */
    @Override
    public void addFront(Integer data) {
        super.addFront(data);
        if (this.head.next != null) {
            this.head.next.prev = this.head;
        }
    }

    /**
     * Adds a new node with the given data to the back of the doubly linked list.
     * Time Complexity: O(1)
     *
     * @param data The data for the new node.
     * @example
     *          DLinkedlist dll = new DLinkedlist();
     *          dll.addBack(10);
     *          dll.addBack(5);
     *          System.out.println(dll); // Output: 10 <-> 5
     */
    public void addBack(Integer data) {
        Node newNode = this.createNode(data);
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            if (this.tail != null) {
                this.tail.next = newNode;
                newNode.prev = this.tail;
            }
            this.tail = newNode;
        }
        this.size += 1;
    }

    /**
     * Adds a new node with the given data after a node with the specified data.
     * Overrides the addAfter method in the superclass.
     * Time Complexity: O(n)
     *
     * @param data  The data for the new node.
     * @param after The data of the node after which the new node should be added.
     * @example
     *          DLinkedlist dll = new DLinkedlist();
     *          dll.addBack(10);
     *          dll.addBack(20);
     *          dll.addAfter(15, 10);
     *          System.out.println(dll); // Output: 10 <-> 15 <-> 20
     */
    @Override
    public void addAfter(Integer data, Integer after) {
        if (!this.contains(after)) {
            this.addFront(data);
        } else {
            Node n = this.createNode(data);
            Node tmp = this.head;
            while (tmp != null && tmp.data != after) {
                tmp = tmp.next;
            }
            n.next = tmp.next;
            n.prev = tmp;
            tmp.next.prev = n;
            tmp.next = n;
            this.size += 1;
        }
    }

    /**
     * Adds a new node with the given data before a node with the specified data.
     * Overrides the addBefore method in the superclass.
     * Time Complexity: O(n)
     *
     * @param data   The data for the new node.
     * @param before The data of the node before which the new node should be added.
     * @example
     *          DLinkedlist dll = new DLinkedlist();
     *          dll.addBack(10);
     *          dll.addBack(20);
     *          dll.addBefore(15, 20);
     *          System.out.println(dll); // Output: 10 <-> 15 <-> 20
     */
    @Override
    public void addBefore(Integer data, Integer before) {
        if (!this.contains(before)) {
            this.addFront(data);
        } else {
            Node n = this.createNode(data);
            Node tmp = this.head;
            while (tmp != null && tmp.next.data != before) {
                tmp = tmp.next;
            }
            n.next = tmp.next;
            n.prev = tmp;
            tmp.next.prev = n;
            tmp.next = n;
            this.size += 1;
        }
    }

    /**
     * Deletes the front node of the doubly linked list.
     * Overrides the deleteFront method in the superclass.
     * Time Complexity: O(1)
     *
     * @return The deleted front node.
     * @example
     *          DLinkedlist dll = new DLinkedlist();
     *          dll.addFront(10);
     *          dll.addFront(5);
     *          dll.deleteFront();
     *          System.out.println(dll); // Output: 10
     */
    @Override
    public Node deleteFront() {
        Node deleted = this.head;
        this.head = this.head.next;
        this.head.prev = null;
        this.size--;
        return deleted;
    }

    /**
     * Deletes a node with the specified data from the doubly linked list.
     * Time Complexity: O(n)
     *
     * @param data The data to delete.
     * @return The deleted node.
     * @example
     *          DLinkedlist dll = new DLinkedlist();
     *          dll.addBack(10);
     *          dll.addBack(20);
     *          dll.delete(10);
     *          System.out.println(dll); // Output: 20
     */
    public Node delete(Integer data) {
        if (this.contains(data)) {
            Node deleted = this.createNode(data);
            Node tmp = this.head;
            while (tmp.data != data) {
                tmp = tmp.next;
            }
            tmp.prev.next = tmp.next;
            this.size--;
            return deleted;
        }
        return new Node(null);
    }

    /**
     * Returns a string representation of the doubly linked list.
     * Overrides the toString method in the superclass.
     * Time Complexity: O(n)
     *
     * @return The string representation of the doubly linked list.
     * @example
     *          DLinkedlist dll = new DLinkedlist();
     *          dll.addBack(10);
     *          dll.addBack(20);
     *          System.out.println(dll); // Output: 10 <-> 20
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node temp = this.head;
        while (temp.next != null) {
            s.append(temp.data);
            if (temp.next.prev == temp)
                s.append(" <-> ");
            else if (temp.next.prev == null)
                s.append(" -> ");
            else
                s.append(" <- ");
            temp = temp.next;
        }
        s.append(temp.data);
        return s.toString();
    }

    /**
     * The main method for testing the functionality of the DLinkedlist class.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        System.out.println("DLinkedlist.java");
        DLinkedlist dll = new DLinkedlist(15);
        dll.addFront(10);
        dll.addBack(20);
        dll.addBefore(14, 20);
        dll.addAfter(12, 10);
        dll.deleteFront();
        dll.deleteBack();
        dll.delete(15);
        System.out.println(dll);
        System.out.println(dll.getSize());
    }
}
