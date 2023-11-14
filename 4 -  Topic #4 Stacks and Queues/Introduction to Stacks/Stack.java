public class Stack extends SLinkedlist{

   private Node top;

  

   public Stack(Integer data){

       super(data);

       this.top = super.head;

   }

  

   public Stack(){

       super();

       this.top = null;

   }

  

   public boolean isEmpty(){ // tests whether the stack is empty or the top is null
    return 0 == this.size;
   }

   public void push(Integer data){// Adds an item to the top of the linkedlist
        Node tmp = this.top;
        this.top = 
      

   }

  

   public Node pop(){ // deletes the top node from the linkedlist

       if(this.top == null)

           return null;

       else{

          

       }

  

   }

  

   public Integer peek(){// prints out the top data

      

   }

  

   public String toString(){

       String s = "";

       Node tmp = this.top;

       while(tmp.next != null){

           s+=tmp.data;

           s+=" -> ";

           tmp = tmp.next;

       }

       s+=tmp.data;

       return s;

   }

   public static void main(String[] args) {

       Stack s = new Stack(15);

       s.push(14);

       s.push(13);

       s.push(12);

       s.push(11);

       System.out.println("Adding to Stack: " + s);

       System.out.println("Pop: "+ s.pop());

       System.out.println("Peek: " +s.peek());

       System.out.println("\nStack: " + s);

       System.out.println("IsEmpty: " + s.isEmpty());

      

   }

}