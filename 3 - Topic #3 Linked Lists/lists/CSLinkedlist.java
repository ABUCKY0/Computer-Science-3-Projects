public class CSLinkedlist extends SLinkedlist{
    
    public CSLinkedlist(Integer data) {
        super(data);
        Node n = new Node(data);
        this.head = n;
        n.next = this.head;
    }
    public CSLinkedlist() {
        super();
    }
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
    /*
    public Node deleteBack(){
        return null;
    }*/

    @Override 
    public String toString() {
        if (this.size == 0) {
            return null;
        }
        else {
            String s = "";
            Node tmp = this.head;
            for (int i = 0; i < this.size - 1; i++) {
                s+= tmp.data;
                s+= "->";
                tmp = tmp.next;
            }
            s += tmp.data;
            return s;
        }
    }
    public static void main(String[] args) {
        CSLinkedlist csl =  new CSLinkedlist(5);
        csl.addFront(6);
        csl.addBack(8);
        System.out.println(csl);
    }
}
