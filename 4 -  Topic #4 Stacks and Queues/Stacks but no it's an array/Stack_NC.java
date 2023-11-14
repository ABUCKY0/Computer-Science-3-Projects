public class Stack_NC {
    
    private Integer[] stack;
    private int topIndex;
    private int currentSize;
    private int size;

    public Stack_NC(int size, Integer data) {
        this.size = size;
        this.stack = new Integer[this.size];
        this.topIndex = 0;
        this.stack[topIndex] = data;
        this.currentSize = 1;
    }
    public Stack_NC(int size) {
        this.size = size;
        this.stack = new Integer[this.size];
        this.topIndex = -1;
        this.currentSize = 0;
    }

    public boolean isEmpty() { // O(1)
        return this.stack[this.topIndex] == null;
    }

    public void push(Integer data) {// O(1)
        if (this.currentSize <= this.size) {
            this.stack[++this.topIndex] = data;
            this.currentSize++;
        }
        else {
            System.out.println("The Array is full and can not insert anytyhing else!");
        }
    }
    public Integer pop() {
        Integer deletedElement = null;
        if (this.currentSize != 0) {
            deletedElement = this.stack[this.topIndex];
            this.stack[this.topIndex] = null;
            this.topIndex--;
            this.currentSize--;
        }
        else {
            System.out.println("The Array does not have any elements to remove!");
        }
        return deletedElement;

    }
    public Integer peek() {
        return this.stack[this.topIndex];
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < this.currentSize-1; i++) {
            s += this.stack[i];
            s+= " -> ";
        }
        s += this.stack[this.currentSize - 1];
        return s;
    }

    public static void main(String[] args){

        Stack_NC s = new Stack_NC(5, 2);
        System.out.println("Push 2 on the Stack: " + s);
        s.push(3);
        System.out.println("Push 3 on the Stack: " + s);
        s.push(4);
        System.out.println("Push 4 on the Stack: " + s);
        
 
        System.out.println("Pop: "+ s.pop());
        System.out.println("Stack: " + s);

        System.out.println("Pop: "+ s.pop());
        System.out.println("Stack: " + s);
 
        System.out.println("Peek: " + s.peek());
        
        System.out.println("\nStack: " + s);
        System.out.println("IsEmpty: " + s.isEmpty());
 
       
 
    }
}
