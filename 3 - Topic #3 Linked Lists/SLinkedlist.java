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
    public void addBefore(Integer data, Integer before) {
        // if the node doesn't exist
        if (this.search(before) == false) {
            this.addFront(data);
        }
        // if the node exists
        else {
            Node n = this.createNode(data);
            Node tmp = this.head;
            while(tmp.next.data != before) { // stops right before it hits the "before data"
                tmp = tmp.next;
            }
            n.next = tmp.next;
            tmp.next = n;

            this.size += 1;
        }
    }
    public void addAfter(Integer data, Integer after) {
        // if the node doesn't exist
        if (this.search(after) == false) {
            this.addFront(data);
        }
        // if the node exists
        else {
            Node n = this.createNode(data);
            Node tmp = this.head;
            while(tmp.data != after) { // stops right as it hits the "before data"
                tmp = tmp.next;
            }
            n.next = tmp.next;
            tmp.next = n;
            this.size += 1;
        }
    }
    public boolean search(Integer data) {
        Node tmp = this.head;
        while(tmp != null) {
            if(tmp.data == data)
                return true;
            tmp = tmp.next;
        }
        return false;
    }
    public Node deleteFront() {
        if(this.size == 0)
            return null;
        else {
            Node deleted = this.head;
            this.head = this.head.next;
            this.size--;
            return deleted;
        }
    }
    public Node deleteBack() {
        if(this.size == 0)
            return null;
        else {
            Node tmp = this.head;
            while(this.size >= 2 && tmp.next.next != null) {
                tmp = tmp.next;
            }
            Node deleted = tmp.next;
            tmp.next = null;
            this.size--;
            return deleted;
        }
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
        sll.addFront(1);
        sll.addFront(2);
        sll.addFront(3);
        sll.addFront(4);
        sll.addFront(5);
        sll.addFront(6);
        sll.addFront(7);
        sll.addFront(8);
        sll.deleteFront();
        sll.deleteBack();

        System.out.println("Added: " + sll);
        System.out.println("Size: " + sll.getSize());
        /*
        System.out.println("Average: " + sll.average());
        */
    }
}
