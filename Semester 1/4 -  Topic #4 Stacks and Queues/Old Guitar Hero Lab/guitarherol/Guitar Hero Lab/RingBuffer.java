import java.util.NoSuchElementException;

public class RingBuffer {

    private int capacity;
    private int size;
    private int first;
    private int last;
    private double[] buffer;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.buffer = new double[capacity];
        this.first = 0;
        this.last = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }

    public void enqueue(double x) {
        if (isFull()) {
            throw new IllegalStateException("Cannot enqueue to a full buffer.");
        }
        buffer[last] = x;
        last = (last + 1) % capacity; // Circularly link the buffer
        size++;
    }

    public double dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue from an empty buffer.");
        }
        double item = buffer[first];
        first = (first + 1) % capacity; // Circularly link the buffer
        size--;
        return item;
    }

    public double peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot peek an empty buffer.");
        }
        return buffer[first];
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            int index = (first + i) % capacity;
            sb.append(buffer[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}