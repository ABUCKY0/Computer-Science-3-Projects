import java.util.NoSuchElementException;

/**
 * A class that represents a Ring Buffer data structure.
 * A Ring Buffer is a circular data structure that uses a single, fixed-size buffer
 * as if it were connected end-to-end.
 */
public class RingBuffer {

    private int capacity; // Maximum number of elements RingBuffer can hold
    private int size; // Current number of elements in RingBuffer
    private int first; // Index of first (oldest) element in RingBuffer
    private int last; // Index of last (newest) element in RingBuffer
    private double[] buffer; // Array to store RingBuffer elements

    /**
     * Constructs a new RingBuffer with the given capacity.
     *
     * @param capacity the capacity of the RingBuffer
     * 
     * Example:
     * RingBuffer rb = new RingBuffer(5);
     */
    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.buffer = new double[capacity];
        this.first = 0;
        this.last = 0;
    }

    /**
     * Returns the current size of the ring buffer.
     * Time complexity: O(1)
     *
     * @return The current size of the ring buffer.
     * 
     * Example:
     * RingBuffer rb = new RingBuffer(5);
     * rb.size(); // returns 0
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the capacity of the ring buffer.
     * Time complexity: O(1)
     *
     * @return The capacity of the ring buffer.
     * 
     * Example:
     * RingBuffer rb = new RingBuffer(5);
     * rb.capacity(); // returns 5
     * 
     * 
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * Checks if the ring buffer is empty.
     * Time complexity: O(1)
     *
     * @return True if the ring buffer is empty, false otherwise.
     * 
     * Example:
     * RingBuffer rb = new RingBuffer(5);
     * rb.isEmpty(); // returns true
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Checks if the ring buffer is full.
     * Time complexity: O(1)
     *
     * @return True if the ring buffer is full, false otherwise.
     * 
     * Example:
     * RingBuffer rb = new RingBuffer(5);
     * rb.isFull(); // returns false
     */
    public boolean isFull() {
        return this.size == this.capacity;
    }

    /**
     * Removes and returns the item at the front of the ring buffer.
     * Time complexity: O(1)
     *
     * @return The item at the front of the ring buffer.
     * @throws NoSuchElementException if the buffer is empty
     * 
     * Example:
     * RingBuffer rb = new RingBuffer(5);
     * rb.dequeue(); // throws NoSuchElementException due to empty buffer
     */
    public double dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue from an empty buffer.");
        }
        double item = buffer[first];
        first = (first + 1) % capacity; // Circularly increment the 'first' index
        size--;
        return item;
    }
    /**
     * Adds an item to the end of the ring buffer.
     * Time complexity: O(1)
     * 
     * @param x The item to be added to the end of the ring buffer.
     * @throws IllegalStateException if the buffer is full
     * 
     * Example:
     * RingBuffer rb = new RingBuffer(5);
     * rb.enqueue(1.0);
     */
    public void enqueue(double x) {
        if (isFull()) {
            throw new IllegalStateException("Cannot enqueue to a full buffer.");
        }
        buffer[last] = x;
        last = (last + 1) % capacity; // Circularly link the buffer
        size++;
    }

    /**
     * Returns (but does not remove) the item at the front of the ring buffer.
     * Time complexity: O(1)
     *
     * @return The item at the front of the ring buffer.
     * @throws NoSuchElementException if the buffer is empty
     * 
     * Example:
     * RingBuffer rb = new RingBuffer(5);
     * rb.peek(); // throws NoSuchElementException due to empty buffer
     */
    public double peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot peek an empty buffer.");
        }
        return buffer[first];
    }

    /**
     * Returns a string representation of the ring buffer.
     * Time complexity: O(n), where n is the size of the ring buffer.
     *
     * @return A string representation of the ring buffer.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            int index = (first + i) % capacity; // Calculate the actual index in a circular manner
            sb.append(buffer[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}