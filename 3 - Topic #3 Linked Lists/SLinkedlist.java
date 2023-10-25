public class SLinkedlist extends Node{
    private Node head;
    private int size;
    public SLinkedlist(Integer data) {
        super(data);
        Node n = new Node(data);
        this.head = n;
        this.size = 1;
    }
    public SLinkedlist() {
        super(null);
        this.size = 0;
    }
    public Node createNode(Integer data) {
        Node newNode = new Node(data);
        return newNode;
    }
    public void addFront(Integer data) {
        
        Node newNode = createNode(data);
        // no data in linkedlist
        if (this.size == 0) {
            this.head = newNode;
        }
        // on or more nodes in linked list
        else {
            newNode.next = this.head;
            this.head = newNode;
        }
        this.head = newNode;
        this.size++;
    }

    public void addBack(Integer data) {
        Node newNode = createNode(data);
        // no data in linkedlist
        if (this.size == 0) {
            this.head = newNode;
        }
        // on or more nodes in linked list
        else {
            Node tmp = this.head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
        this.size++;
    }

    // returns size of linked list
    public int getSize() {
        return this.size;
    }

    public double average() {
        Node tmp = this.head;
        Integer value = 0;
        Integer divisor = 0;
        while (tmp != null) {
            value += tmp.data;
            tmp = tmp.next;
            divisor++;
        }
        return (double)value/divisor;
    }

    // toString method: 
    /*
     * Prints out the linked list in the following format:
     * 7 -> 9 -> 8
    */
    public String toString() {
        String s = " ";
        Node tmp = this.head;
        while (tmp.next != null) {
            s += tmp.data + " -> ";
            tmp = tmp.next;
        }
        s += tmp.data;
        return s;

    }
    public static void main(String[] args) {
        SLinkedlist sll = new SLinkedlist();
        sll.addFront(7);
        sll.addFront(9);
        sll.addFront(8);
        System.out.println("Adding 7, 9, 8 to Front:  " + sll);
        sll.addBack(10);
        System.out.println("Adding 10 to Back:  " + sll);
        System.out.println("Size: " + sll.getSize());

        System.out.println("Average: " + sll.average());
    }
}
