public class Doubly_LL_Queue extends DLinkedlist{
    public Doubly_LL_Queue(){
        super();
    }
    public void enqueue(int data){
        super.addBack(data);
    }
    public int dequeue(){
        return super.deleteFront().data;
    }
    public int peek(){
        return super.head.data;
    }
    public int size(){
        return super.size;
    }
    public boolean isEmpty(){
        return super.size == 0;
    }
    public void display(){
        super.toString();
    }
    public static void main(String[] args) {
        Doubly_LL_Queue queue = new Doubly_LL_Queue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.display();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.peek());
        System.out.println(queue.size());
        System.out.println(queue.isEmpty());
        queue.display();
    }
}