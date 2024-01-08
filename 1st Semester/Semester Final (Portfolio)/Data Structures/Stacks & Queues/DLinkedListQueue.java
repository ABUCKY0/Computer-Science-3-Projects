import java.util.NoSuchElementException;

/**
 * A class that represents a Queue data structure.
 * This Queue extends a doubly linked list (DLinkedlist) to use its methods.
 */
public class DLinkedListQueue extends DLinkedlist {

    /**
     * Constructs a new Queue.
     * Time complexity: O(1)
     */
    public DLinkedListQueue() {
        super();
    }

    /**
     * Adds a new element to the back of the queue.
     * Time complexity: O(1)
     *
     * @param data The data to be added to the queue.
     * 
     * Example:
     * Queue q = new Queue();
     * q.add(10);
     */
    public void add(Integer data) {
        this.addBack(data);
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * Time complexity: O(1)
     *
     * @return The element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     * 
     * Example:
     * Queue q = new Queue();
     * q.add(10);
     * int front = q.element(); // front = 10
     */
    public Integer element() {
        if (this.getSize() == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return this.head.data;
    }

    /**
     * Adds a new element to the back of the queue.
     * Time complexity: O(1)
     *
     * @param data The data to be added to the queue.
     * @return true if the element was added successfully, false otherwise.
     * 
     * Example:
     * Queue q = new Queue();
     * boolean isAdded = q.offer(10); // isAdded = true
     */
    public boolean offer(Integer data) {
        try {
            this.addBack(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * Time complexity: O(1)
     *
     * @return The element at the front of the queue, or null if the queue is empty.
     * 
     * Example:
     * Queue q = new Queue();
     * q.add(10);
     * int front = q.peek(); // front = 10
     */
    public Integer peek() {
        if (this.getSize() == 0) {
            return null;
        }
        return this.head.data;
    }

    /**
     * Removes and returns the element at the front of the queue.
     * Time complexity: O(1)
     *
     * @return The element at the front of the queue, or null if the queue is empty.
     * 
     * Example:
     * Queue q = new Queue();
     * q.add(10);
     * int front = q.poll(); // front = 10
     */
    public Integer poll() {
        if (this.getSize() == 0) {
            return null;
        }
        Integer data = this.head.data;
        this.deleteFront();
        return data;
    }

    /**
     * Removes and returns the element at the front of the queue.
     * Time complexity: O(1)
     *
     * @return The element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     * 
     * Example:
     * Queue q = new Queue();
     * q.add(10);
     * int front = q.remove(); // front = 10
     */
    public Integer remove() {
        if (this.getSize() == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        Integer data = this.head.data;
        this.deleteFront();
        return data;
    }

    public static void main(String[] args) {
    DLinkedListQueue q = new DLinkedListQueue();

    // Test add method
    q.add(10);
    q.add(20);
    q.add(30);
    System.out.println("After adding 10, 20, 30: " + q); // Expected output: 10 <-> 20 <-> 30

    // Test element method
    System.out.println("Element at front: " + q.element()); // Expected output: 10

    // Test offer method
    System.out.println("Offer 40: " + q.offer(40)); // Expected output: true
    System.out.println("After offering 40: " + q); // Expected output: 10 <-> 20 <-> 30 <-> 40

    // Test peek method
    System.out.println("Peek at front: " + q.peek()); // Expected output: 10

    // Test poll method
    System.out.println("Poll from front: " + q.poll()); // Expected output: 10
    System.out.println("After polling: " + q); // Expected output: 20 <-> 30 <-> 40

    // Test remove method
    System.out.println("Remove from front: " + q.remove()); // Expected output: 20
    System.out.println("After removing: " + q); // Expected output: 30 <-> 40
}
}