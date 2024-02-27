package Heap.Java;

public class MinHeap {
    private int[] data;
    private int capacity;
    private int current_size;

    /**
     * Constructor
     * 
     * @param value       A value to become the parent
     * @param maxCapacity The capacity of the tree
     */
    public MinHeap(int value, int maxCapacity) {
        this.capacity = maxCapacity;
        this.data = new int[this.capacity];
        this.current_size = 0;
        this.data[this.current_size++] = value;
    }

    /**
     * Constructor
     * 
     * @param maxCapacity The capacity of the tree
     */
    public MinHeap(int maxCapacity) {
        this.capacity = maxCapacity;
        this.data = new int[this.capacity];
        this.current_size = 0;
    }

    /** Gets the Left Child
     * 
     * @param index the parent index
     * @return the left Child's value
     */
    public Integer getLeftChild(int index) {
        if (2 * index + 1 >= this.current_size)
            return null;
        return this.data[2 * index + 1];
    }
    /** Gets the Right Child
     * 
     * @param index the parent index
     * @return the right Child's value
     */
    public Integer getRightChild(int index) {
        if (2 * index + 2 >= this.current_size)
            return null;
        return this.data[2 * index + 2];
    }

    /**
     * Returns the parent value
     * 
     * @param index the index of the parent
     * @return the parent
     */
    public Integer getParent(int index) {
        if (index < this.current_size) {
            return (int) ((index - 1) / 2);
        } else {
            return null;
        }
    }

    /**
     * Gets the Current Size of the arr
     * 
     * @return the current size
     */
    public int getCurrentSize() {
        return this.current_size;
    }

    /**
     * Gets the Capacity of the arr
     * 
     * @return the capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Adds an item to the tree
     * 
     * @param value the Int to add to the tree
     */
    public void add(int value) {
        if (this.current_size >= this.capacity) {
            this.resize();
        }
        this.data[this.current_size++] = value;
        this.trickleUp(this.current_size - 1);
    }

    /**
     * Trickles the current data at a specified index up
     * 
     * @param index the index to move
     */
    public void trickleUp(int index) {
        System.out.println("trickleup");
        if (index == 0)
            return;

        int parentIndex = this.getParent(index);

        if (this.data[index] < this.data[parentIndex]) {
            this.swap(index, parentIndex);
            this.trickleUp(parentIndex);
        }
    }

    /**
     * Trickles the current data at a specified index down
     * 
     * @param index the index to move
     */
    public void trickleDown(int index) {
        System.out.println("trickledown");
        Integer lChild = this.getLeftChild(index);
        Integer rChild = this.getRightChild(index);

        System.out.println("Left Child: " + lChild);
        System.out.println("Right Child: " + rChild);
        if (lChild == null) {
            return;
        }
        if (lChild < this.data[index]) {
            swap(2 * index + 1, index);
            trickleDown(index);
            return;
        }
        // If the right is null, and youve made it this far, then you're in the right place and exit
        if (rChild == null) {
            return;
        }
        // If the child is less than the index, swap them and continue trickling down
        if (rChild < this.data[index]) {
            swap(2 * index + 2, index);
            trickleDown(index);
            return;
        }

    }

    /**
     * Swaps the 2 given indexes
     * 
     * @param index1 the parent index to swap
     * @param index2 the victim index to swap
     */
    public void swap(int index1, int index2) {
        int tmp = this.data[index1];
        this.data[index1] = this.data[index2];
        this.data[index2] = tmp;
    }

    /**
     * Resizes the Array to Double its current size.
     * 
     * @implNote Could get Exponential *fast* if used multiple times because {@code this.capacity = this.capacity * 2}
     */
    public void resize() {
        this.capacity *= 2;
        int[] newData = new int[this.capacity];
        System.arraycopy(this.data, 0, newData, 0, this.current_size);
        this.data = newData;
    }

    /**
     * Deletes the specified index
     * 
     * @implNote not implemented
     * @param index
     */
    public void delete() {

        /* IMPLEMENTATION:
         *
         * 
         * 1 - Save first element
         * 2 - Swap (first, last)
         * 3 - decrease size
         * 4 - trickle down
         * 
         * --------------------------------
         * 
         * 1 - Check Parent -> children (l/r)
         * 2 - Swap with less (trickledown (current_index))
         */
        //int save = this.data[this.current_size - 1];
        swap(0, (this.current_size - 1));
        this.current_size--;
        trickleDown(0);

        
        return;
    }

    /**
     * Copied from StackOverflow PseudoCode and adapted to work here
     * @see https://stackoverflow.com/questions/8964279/coding-a-basic-pretty-printer-for-trees-in-java
     */
    public void printSubtree( int indent, int index ) {
        for( int i = 0; i < indent; ++i) {
          System.out.print(" ");
        }
        
        Integer leftChild = (index - 1) / 2;

        if(leftChild == null) { // has left child
          System.out.println("(" + this.data[index]);     
          printSubtree(indent + 1, 2 * index + 1); //this is a recursive call, alternatively use the indent formula above if you don't use recursion
          printSubtree(indent + 1, 2 * index + 2);
      
          //we have a new line so print the indent again
          for( int i = 0; i < indent; ++i) {
            System.out.print(" ");
          }
          System.out.println(")"); 
        } else if( /*  */) {
          System.out.println(this.data[index]);
        } else { //empty/non existing node
          System.out.println("()");
        }
      }


    /**
     * Returns the MinHeap tree as an array
     */
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < this.current_size - 1; i++) {
            s.append(this.data[i]).append(", ");
        }
        if (this.current_size > 0) {
            s.append(this.data[this.current_size - 1]);
        }
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap(35, 10);
        heap.add(20);
        heap.add(25);
        heap.add(40);
        heap.add(16);
        heap.add(14);
        heap.add(27);
        heap.add(5);
        
        System.out.println("[PRE] Resize: " + heap.getCapacity());
        heap.resize();
        System.out.println("[POST] Resize: " + heap.getCapacity());

        
        System.out.println("[PRE] Delete: " + heap);
        heap.delete();
        System.out.println("[POST] Delete: " + heap);

        heap.printSubtree(1, 0);


        System.out.println("[FINAL]: " +heap);
        System.out.println(heap.getCapacity());
    }
}
