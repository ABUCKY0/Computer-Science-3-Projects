public class CDLinkedlist extends DLinkedlist {
    public CDLinkedlist(Integer data) {
        super(data);
        Node n = new Node(data);
        this.head = n;
        this.tail = n;

        this.tail.next = this.head;
        this.head.prev = this.tail;
    }
    public CDLinkedlist() {super();}

    public void addFront(Integer data) {
        super.addFront(data);
        this.head.prev = this.tail;
    }
    public void addBack(Integer data) {
        super.addBack(data);
        this.tail.next = this.head;
    }
    public String toString() {
        if (this.size == 0) {
            return null;
        }
        else {
            String s = "";
            Node tmp = this.head;
            for (int i = 0; i < this.size - 1; i++) {
                s += tmp.data;
                s += " <-> ";
                tmp = tmp.next;
            }
            s += tmp.data;
            return s;
        }
    }
}
