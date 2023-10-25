
public class Node
{
    public Integer data;
    public Node next;

    public Node(Integer data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        return this.data+" ";
    }

    public static void main(String[] args) {

        Node head; // the header node - where the linked list starts

        // all of the unlinked nodes
        Node a = new Node(5);
        Node b = new Node(6);
        Node c = new Node(7);
        Node d = new Node(8);

        //linked list starting with head going to d 
        head = a; //head node is a
        a.next = b;
        b.next = c;
        c.next = d;

        // this is how we loop through a inked list every time
        Node tmp = head;
        Integer value = 0;
        Integer divisor = 0;
        while (tmp != null) {
            System.out.print(tmp);
            value += tmp.data;
            tmp = tmp.next;
            divisor++;
        }
        System.out.println("\nCount: " + divisor);
        System.out.println("Average: " + (double)value/divisor);
    }
}