public class DLinkedlist extends SLinkedlist {
    public Node tail;

    public DLinkedlist(Integer data) {
        super(data);
        Node n = new Node(data);
        this.head = n;
        this.tail = n;
    }
    public DLinkedlist() {
        super();
    }
    public void addFront(Integer data) {
        super.addFront(data);
        this.head.next.prev = this.head;
    }
    public void addBack(Integer data){
        Node newNode = this.createNode(data);
        this.tail.next = newNode;
        newNode.prev = this.tail;
        this.tail = newNode;
        this.size += 1;
        

    }

    public void addAfter(Integer data, Integer after) {
        if (this.contains(after) == false) {
            this.addFront(data);
        }
        else {
            Node n = this.createNode(data);
            Node tmp = this.head;
            while(tmp != null && tmp.data != after) {
                tmp = tmp.next;
            }
            n.next = tmp.next;
            n.prev = tmp;
            tmp.next.prev = n;
            tmp.next = n;
            this.size += 1;
        }
    }
    public void addBefore(Integer data, Integer before) {
        if (this.contains(before) == false) {
            this.addFront(data);
        }
        else {
            Node n = this.createNode(data);
            Node tmp = this.head;
            while(tmp != null && tmp.next.data != before) {
                tmp = tmp.next;
            }
            n.next = tmp.next;
            n.prev = tmp;
            tmp.next.prev = n;
            tmp.next = n;
            this.size += 1;
        }
    }

    public Node deleteFront() {
        Node deleted = this.head;
        this.head = this.head.next;
        this.head.prev = null;
        this.size--;
        return deleted;
    }

    public String toString() {
        String s = "";
        Node temp = this.head;
        while (temp.next != null) {
            s += temp.data;
            if(temp.next.prev == temp) 
                s+=" <-> ";
            else if (temp.next.prev == null) 
                s+=" -> ";
            else
                s+= " <- ";
            temp = temp.next;
        }
        s+= temp.data;
        return s;
    }

    public Node delete(Integer data) {
        if (this.contains(data) == true) {
            Node deleted = this.createNode(data);
            Node tmp = this.head;
            while(tmp.data != data) {
                tmp = tmp.next;
            }
            tmp.prev.next = tmp.next;
            this.size--;
            return deleted;
        }
        return new Node(null);
    }
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
