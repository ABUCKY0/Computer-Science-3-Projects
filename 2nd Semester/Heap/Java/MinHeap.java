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

    /**
     * Gets the Left Child
     * 
     * @param index the parent index
     * @return the left Child's value
     */
    public Integer getLeftChild(int index) {
        if (2 * index + 1 >= this.current_size)
            return null;
        return this.data[2 * index + 1];
    }

    /**
     * Gets the Right Child
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
        Integer lChild = this.getLeftChild(index);
        Integer rChild = this.getRightChild(index);
        if (lChild == null) {
            return;
        }
        if (lChild < this.data[index]) {
            swap(2 * index + 1, index);
            trickleDown(2 * index + 1); // update index
            return;
        }
        // If the right is null, and you've made it this far, then you're in the right
        // place and exit
        if (rChild == null) {
            return;
        }
        // If the child is less than the index, swap them and continue trickling down
        if (rChild < this.data[index]) {
            swap(2 * index + 2, index);
            trickleDown(2 * index + 2); // update index
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
     * @implNote Could get Exponential *fast* if used multiple times because
     *           {@code this.capacity = this.capacity * 2}
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
     * @param index
     */
    public void delete(int index) {
        // int save = this.data[this.current_size - 1];
        swap(index, (this.current_size - 1));
        this.current_size--;
        trickleDown(index);

        return;
    }

    public void remove(int value) {
        // Find the index of the value
        int index = -1;
        index = this.findElement(value, 0);

        // If the value is not found, return
        if (index == -1) {
            return;
        }

        delete(index);
    }

    public int findElement(int value, int index) {
        if (index >= this.current_size) {
            return -1;
        }
        if (this.data[index] == value) {
            return index;
        }

        // Recursively search in the left and right subtrees
        int leftIndex = findElement(value, 2 * index + 1);
        if (leftIndex != -1) {
            return leftIndex;
        }
        int rightIndex = findElement(value, 2 * index + 2);
        if (rightIndex != -1) {
            return rightIndex;
        }

        return -1;
    }

    /**
     * Prints the MinHeap tree as a tree like shape
     */
    public void printHeap() {
        int i = 0;
        int k = 1;
        while (i < this.current_size) {
            while ((i < k) && (i < this.current_size)) {
                System.out.print(this.data[i] + " ");
                i++;
            }
            System.out.println();
            k = k * 2 + 1;
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

        heap.printHeap();

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("[PRE] Delete (14): " + heap);
        heap.printHeap();
        heap.remove(14);
        System.out.println("[POST] Delete (14): " + heap);
        heap.printHeap();
        System.out.println("[PRE] Delete (16): " + heap);
        heap.printHeap();
        heap.remove(16);
        System.out.println("[POST] Delete (16): " + heap);
        heap.printHeap();

        System.out.println("[FINAL]: " + heap);
        System.out.println(heap.getCapacity());
    }
}
