import java.util.NoSuchElementException;

public class RingBuffer {

    private int capacity;

    private int size;

    private int first;

    private int last;

    private double[] buffer;

    public RingBuffer(int capacity) {

        // implement constructor
        this.capacity = capacity;
        this.size = 0;
        this.buffer = new double[capacity];
        this.first = 0;
        this.last = 0;
    }

    public int size() {
        // implement size method
        return this.size;
    }

    public boolean isEmpty() {
        // implement isEmpty method
        return this.size == 0;
    }

    public boolean isFull() {
        // implement isFull method
        return this.size == this.capacity;
    }

    public void enqueue(double x) {
        // implement enqueue method
        this.buffer[this.size] = x;
        this.size++;
    }

    public double dequeue() {

        // implement dequeue method

    }

    public double peek() {

        // implement peek method

    }

    public String toString() {

        // implement toString method

    }

}
