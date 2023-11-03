public class SLinkedlist extends Linkedlist{
    public Node head;
    public int size;
    
    public SLinkedlist(Integer data){
        Node n = new Node(data);
        this.head = n;
        this.size = 1;
    }
    public SLinkedlist(){
        this.size = 0;
    }
    public void clear(){
        this.head = null;
    }
    public Node createNode(Integer data){
        Node newNode = new Node(data);
        return newNode;
    }
    public boolean contains(Integer data){
        Node tmp = this.head;
        while(tmp != null){
            if(tmp.data == data)
                return true;
                
            tmp = tmp.next;
        }
        return false;
    }
    public void addFront(Integer data){
        Node newNode = this.createNode(data);
        // no data in the linkedlist
        if(this.size == 0){
            this.head = newNode;
        }
        // one or more nodes in the linkedlist
        else{
            newNode.next = this.head;
            this.head = newNode;
        }
        this.size += 1;
    }
    public void addBack(Integer data){
        Node newNode = this.createNode(data);
        // no data in the linkedlist
        if(this.size == 0){
            this.head = newNode;
        }
        // one or more nodes in the linkedlist
        else{
            Node tmp = this.head;
            while(tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
        this.size += 1;
        
    }
    public void addBefore(Integer data, Integer before){
        // if the node doesn't exist
        if(this.contains(before) == false){
           this.addFront(data);
        }
        // if the node exists
        else{
            Node n = this.createNode(data);
            Node tmp = this.head;
            while(tmp.next.data != before){ // stops right before it hits "before" data
                tmp = tmp.next;
            }
            n.next = tmp.next;
            tmp.next = n;
            this.size +=1;
        }
        
    }
    
    public void addAfter(Integer data, Integer after){
        // if the node doesn't exist
        if(this.contains(after) == false){
           this.addBack(data);
        }
        // if the node exists
        else{
            Node n = this.createNode(data);
            Node tmp = this.head;
            while(tmp.data != after){
                tmp = tmp.next;
            }
            n.next = tmp.next;
            tmp.next = n;
            this.size +=1;
        }
        
    }

    public void addAt(Integer data, Integer pos) {
        if (pos > this.size) {
            this.addBack(data);
        }
        else {
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
    public Node deleteFront(){
        if(this.size == 0)
            return null;
            
        else{
            Node deleted = this.head;
            this.head = this.head.next;
            System.out.println("Head Node: "+ this.head);
            this.size--;
            return deleted;
        }
    }
    public Node deleteBack(){
        if(this.size == 0)
            return null;
        
        else{
            Node tmp = this.head;
            while(this.size >= 2 && tmp.next.next != null){
                tmp = tmp.next;
            }
            Node deleted = tmp.next;
            tmp.next = null;
            this.size--;
            return deleted;
        }
    }
    public void decrementSize(){
        this.size--;
    }
    public void incrementSize(){
        this.size++;
    }
    public int getSize(){
        return this.size;
    }
    public String toString(){
        String s = "";
        Node tmp = this.head;
        while(tmp.next != null){
            s+= tmp.data;
            s+=" -> ";
            tmp = tmp.next;
        }
        s+=tmp.data;
        return s;
    }
    public static void main(String[] args){
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
        sll.addAt(8, 3);

        // Singly Linked List Addition 2
        //sll.findFromEnd(3); // NOT IMPLEMENTED

        // Singly Linked List Addition 3
        //sll.splitInHalf(); // NOT IMPLEMENTED

        // Singly Linked List Addition 4
        //sll.findMode(); // NOT IMPLEMENTED

        // Singly Linked List Addition 5
        //.removeDuplicates(); // NOT IMPLEMENTED
        System.out.println("Singly Linked List: "+ sll);
        System.out.println("Deleted Front: "+ sll.deleteFront());
        System.out.println("\nSingly Linked List: "+ sll);
        System.out.println("Deleted Back: "+ sll.deleteBack());
        System.out.println("\nSingly Linked List: "+ sll);
        System.out.println("Size: " + sll.getSize());
    }
}