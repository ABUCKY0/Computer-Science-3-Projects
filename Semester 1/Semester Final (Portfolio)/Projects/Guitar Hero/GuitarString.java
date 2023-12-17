/**
 * A class representing a guitar string.
 */
public class GuitarString {

    public RingBuffer ringBuffer;
    public int ticCount;

    /**
     * Constructs a GuitarString with a given frequency.
     * Time complexity: O(n), where n is the capacity of the ring buffer.
     *
     * @param frequency The frequency of the guitar string.
     * 
     * Example:
     * GuitarString gs = new GuitarString(440.0);
     */
    public GuitarString(double frequency) {
        int capacity = (int)(((44100.0 / frequency) + 1));
        this.ringBuffer = new RingBuffer(capacity);
        this.ticCount = 0;
        for (int x = 0; x < ringBuffer.capacity(); x++) {
            ringBuffer.enqueue(0);
        }
    }

    /**
     * Constructs a GuitarString with a given array of initial values.
     * Time complexity: O(n), where n is the length of the init array.
     *
     * @param init The initial values for the guitar string.
     * 
     * Example:
     * double[] init = {0.2, 0.4, 0.5, 0.3, 0.6};
     * GuitarString gs = new GuitarString(init);
     */
    public GuitarString(double[] init) {
        this.ringBuffer = new RingBuffer(init.length);
        for (int x = 0; x < init.length; x++) {
            this.ringBuffer.enqueue(init[x]);
        }
        this.ticCount = 0;
    }

    /**
     * Plucks the guitar string by replacing the ring buffer with random values.
     * Time complexity: O(n), where n is the size of the ring buffer.
     * 
     * Example:
     * gs.pluck();
     * 
     */
    public void pluck() {
        int rbsize = ringBuffer.size();
        for (int x = 0; x < rbsize; x++) {
            ringBuffer.dequeue();
            double random = Math.random()  - .5;
            ringBuffer.enqueue(random);
        }
    }

    /**
     * Advances the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     * Time complexity: O(1)
     * 
     * Example:
     * gs.tic();
     * 
     */
    public void tic() {
        double removed = this.ringBuffer.dequeue();
        double avg = (this.sample() + removed) / 2;
        this.ringBuffer.enqueue(avg);
        this.ticCount++;
    }

    /**
     * Returns the current sample (the value at the front of the ring buffer).
     * Time complexity: O(1)
     * 
     * Example:
     * double sample = gs.sample();
     * 
     * @return The current sample (the value at the front of the ring buffer).
     */
    public double sample() {
        return this.ringBuffer.peek();
    }

    /**
     * Returns the number of times tic was called.
     * Time complexity: O(1)
     * 
     * Example:
     * int time = gs.time();
     * 
     * @return The number of times tic was called.
     */
    public int time() {
        return this.ticCount;
    }

}