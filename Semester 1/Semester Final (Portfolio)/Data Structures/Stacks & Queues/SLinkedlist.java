/**
 * A class representing a singly linked list with various operations.
 */
public class SLinkedlist extends Linkedlist {

    /**
     * Reference to the head node of the linked list.
     */
    public Node head;

    /**
     * The size of the linked list.
     */
    public int size;

    /**
     * Constructs a singly linked list with a single node containing the given data.
     *
     * @param data The data for the initial node.
     */
    public SLinkedlist(Integer data) {
        Node n = new Node(data);
        this.head = n;
        this.size = 1;
    }

    /**
     * Constructs an empty singly linked list.
     */
    public SLinkedlist() {
        this.size = 0;
    }

    /**
     * Constructs a singly linked list with a given node.
     *
     * @param n The head node of the linked list.
     */
    public SLinkedlist(Node n) {
        this.head = n;
        this.size = 0;
        Node tmp = this.head;
        // Counting the initial size of the list
        while (tmp.next != null) {
            this.size += 1;
            tmp = tmp.next;
        }
    }

    /**
     * Clears the linked list by setting the head to null and size to 0.
     * Time Complexity: O(1)
     * Example: {@code sll.clear();}
     */
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Creates a new node with the given data.
     *
     * @param data The data for the new node.
     * @return The newly created node.
     * Time Complexity: O(1)
     * Example: {@code Node newNode = sll.createNode(42);}
     */
    public Node createNode(Integer data) {
        Node newNode = new Node(data);
        return newNode;
    }

    /**
     * Checks if the linked list contains a node with the given data.
     *
     * @param data The data to search for.
     * @return True if the data is found, false otherwise.
     * Time Complexity: O(n)
     * Example: {@code boolean containsData = sll.contains(42);}
     */
    public boolean contains(Integer data) {
        Node tmp = this.head;
        while (tmp != null) {
            if (tmp.data.equals(data))
                return true;

            tmp = tmp.next;
        }
        return false;
    }

    /**
     * Adds a new node with the given data to the front of the linked list.
     *
     * @param data The data for the new node.
     * Time Complexity: O(1)
     * Example: {@code sll.addFront(42);}
     */
    public void addFront(Integer data) {
        Node newNode = this.createNode(data);
        if (this.size == 0) {
            this.head = newNode;
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
        this.size += 1;
    }

    /**
     * Adds a new node with the given data to the back of the linked list.
     *
     * @param data The data for the new node.
     * Time Complexity: O(n)
     * Example: {@code sll.addBack(42);}
     */
    public void addBack(Integer data) {
        Node newNode = this.createNode(data);
        if (this.size == 0 || this.head == null) {
            this.head = newNode;
        } else {
            Node tmp = this.head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
        this.size += 1;
    }

    /**
     * Adds a new node with the given data before a node with the specified data.
     *
     * @param data   The data for the new node.
     * @param before The data of the node before which the new node should be added.
     * Time Complexity: O(n)
     * Example: {@code sll.addBefore(42, 5);}
     */
    public void addBefore(Integer data, Integer before) {
        if (!this.contains(before)) {
            this.addFront(data);
        } else {
            Node n = this.createNode(data);
            Node tmp = this.head;
            while (tmp.next != null && !tmp.next.data.equals(before)) {
                tmp = tmp.next;
            }
            n.next = tmp.next;
            tmp.next = n;
            this.size += 1;
        }
    }

    /**
     * Adds a new node with the given data after a node with the specified data.
     *
     * @param data  The data for the new node.
     * @param after The data of the node after which the new node should be added.
     * Time Complexity: O(n)
     * Example: {@code sll.addAfter(42, 5);}
     */
    public void addAfter(Integer data, Integer after) {
        if (!this.contains(after)) {
            this.addBack(data);
        } else {
            Node n = this.createNode(data);
            Node tmp = this.head;
            while (tmp != null && !tmp.data.equals(after)) {
                tmp = tmp.next;
            }
            n.next = tmp.next;
            tmp.next = n;
            this.size += 1;
        }
    }

    /**
     * Inserts a new node with the given data at the specified position.
     *
     * @param data The data for the new node.
     * @param pos  The position at which the node should be inserted.
     * Time Complexity: O(n)
     * Example: {@code sll.addAt(42, 3);}
     */
    public void addAt(Integer data, Integer pos) {
        if (pos > this.size) {
            this.addBack(data);
        } else {
            Node n = this.createNode(data);
            Node tmp = this.head;
            for (int i = 0; i < pos - 1; i++) {
                tmp = tmp.next;
            }
            n.next = tmp.next;
            tmp.next = n;
            this.size += 1;
        }
    }

    /**
     * Finds and returns the node at a specified position counting from the end.
     *
     * @param pos The position from the end.
     * @return The node at the specified position from the end.
     * Time Complexity: O(n)
     * Example: {@code Node node = sll.findFromEnd(3);}
     */
    public Node findFromEnd(Integer pos) {
        Node tmp = this.head;
        for (int i = 0; i < this.size - pos; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    /**
     * Splits the linked list in half and returns the second half as a new linked list.
     *
     * @return The second half of the linked list.
     * @throws IllegalStateException If attempting to split a circular linked list.
     * Time Complexity: O(n)
     * Example: {@code SLinkedlist secondHalf = sll.splitInHalf();}
     */
    public SLinkedlist splitInHalf() {
        Node tmp = this.head;
        Node prev = null;
        for (int i = 0; i < this.size / 2; i++) {
            prev = tmp;
            tmp = tmp.next;
            if (tmp == this.head) {
                throw new IllegalStateException("Cannot split a circular linked list using this method.");
            }
        }

        prev.next = null;
        return new SLinkedlist(tmp);
    }

    /**
     * Finds the mode (most frequently occurring element) in the linked list.
     *
     * @return The mode of the linked list.
     * Time Complexity: O(n^2)
     * Example: {@code Integer mode = sll.findMode();}
     */
    public Integer findMode() {
        if (this.head == null) {
            return null; // If the list is empty, return null
        }

        Node current = this.head;
        int maxCount = 0;
        Integer mode = null;

        while (current != null) {
            int count = 0;
            Node temp = this.head;
            while (temp != null) {
                if (temp.data.equals(current.data)) {
                    count++;
                }
                temp = temp.next;
            }

            if (count > maxCount) {
                maxCount = count;
                mode = current.data;
            }

            current = current.next;
        }

        return mode;
    }

    /**
     * Removes duplicate nodes from the linked list.
     * Time Complexity: O(n^2)
     * Example: {@code sll.removeDuplicates();}
     */
    public void removeDuplicates() {
        Node current = this.head;
        while (current != null) {
            Node temp = current;
            while (temp != null && temp.data.equals(current.data)) {
                temp = temp.next;
            }
            current.next = temp;
            current = current.next;
        }
    }

    /**
     * Deletes the front node of the linked list.
     *
     * @return The deleted front node.
     * Time Complexity: O(1)
     * Example: {@code Node deleted = sll.deleteFront();}
     */
    public Node deleteFront() {
        if (this.size == 0)
            return null;

        else {
            Node deleted = this.head;
            this.head = this.head.next;
            System.out.println("Head Node: " + this.head);
            this.size--;
            return deleted;
        }
    }

    /**
     * Deletes the back node of the linked list.
     *
     * @return The deleted back node.
     * Time Complexity: O(n)
     * Example: {@code Node deleted = sll.deleteBack();}
     */
    public Node deleteBack() {
        if (this.size == 0)
            return null;

        else {
            Node tmp = this.head;
            while (this.size >= 2 && tmp.next.next != null) {
                tmp = tmp.next;
            }
            Node deleted = tmp.next;
            tmp.next = null;
            this.size--;
            return deleted;
        }
    }

    /**
     * Decrements the size of the linked list.
     * Time Complexity: O(1)
     * Example: {@code sll.decrementSize();}
     */
    public void decrementSize() {
        this.size--;
    }

    /**
     * Increments the size of the linked list.
     * Time Complexity: O(1)
     * Example: {@code sll.incrementSize();}
     */
    public void incrementSize() {
        this.size++;
    }

    /**
     * Retrieves the size of the linked list.
     *
     * @return The size of the linked list.
     * Time Complexity: O(1)
     * Example: {@code int size = sll.getSize();}
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return The string representation of the linked list.
     * Time Complexity: O(n)
     * Example: {@code String representation = sll.toString();}
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node tmp = this.head;
        while (tmp.next != null) {
            s.append(tmp.data);
            s.append(" -> ");
            tmp = tmp.next;
        }
        s.append(tmp.data);
        return s.toString();
    }
    /**
     * The main method for testing the functionality of the SLinkedlist class.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        SLinkedlist sll = new SLinkedlist();
        sll.addFront(1);
        sll.addFront(2);
        sll.addFront(3);
        sll.addFront(4);
        sll.addFront(5);
        sll.addFront(6);
        sll.addFront(7);
        sll.addFront(8);

        // Singly Linked List Addition 1
        sll.addAt(5, 3);
        sll.addAt(5, 3);

        System.out.println("Singly Linked List: " + sll);
        System.out.println();

        // Singly Linked List Addition 2
        System.out.println("Three from end: " + sll.findFromEnd(3));

        // Singly Linked List Addition 3
        SLinkedlist sltwo = sll.splitInHalf();
        System.out.println("First half: " + sll);
        System.out.println("Second half: " + sltwo);
        // Singly Linked List Addition 4
        System.out.println("\nMode:" + sll.findMode() + "\n");

        // Singly Linked List Addition 5
        System.out.println("Singly Linked List: " + sll);
        sll.removeDuplicates();
        System.out.println("After removing duplicates Singly Linked List: " + sll);
        System.out.println("Deleted Front: " + sll.deleteFront());
        System.out.println("\nSingly Linked List: " + sll);
        System.out.println("Deleted Back: " + sll.deleteBack());
        System.out.println("\nSingly Linked List: " + sll);
        System.out.println("Size: " + sll.getSize());
    }
}