package lists;
public class DLinkedlist extends SLinkedlist {
    private Node tail;

    public DLinkedlist(Integer data) {
        super(data);
        Node n = new Node(data);
        this.tail = n;
    }
    public DLinkedlist() {
        super();
    }
    public void addFront(Integer data) {
        super.addFront(data);
        super.head.next.prev = super.head;
    }
    public void addBack(Integer data){
        super.addBack(data);
        //this.tail.next.prev = this.tail;
        //this.tail = this.tail.next;
        System.out.println(this.tail);
        

    }
    public static void main(String[] args) {
        System.out.println("DLinkedlist.java");
        DLinkedlist dll = new DLinkedlist(15);
        dll.addFront(10);
        dll.addBack(20);
        System.out.println(dll);
        System.out.println(dll.getSize());
    }
}
