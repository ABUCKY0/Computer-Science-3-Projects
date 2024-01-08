public class GuitarString {

    public RingBuffer ringBuffer;
    public int ticCount;
    public GuitarString(double frequency) {

        // implement constructor
        int capacity = (int) ((44100 / frequency) + 1);
        this.ringBuffer = new RingBuffer(capacity);
        this.ticCount = 0;
    }

    public GuitarString(double[] init) {

        // implement constructor
        this.ringBuffer = new RingBuffer(init.length);
        for (int x = 0; x < init.length; x++) {
            this.ringBuffer.enqueue(init[x]);
        }
        this.ticCount = 0;
    }

    public void pluck() {

        // implement pluck method
        int rbsize = ringBuffer.size();
        for (int x = 0; x < rbsize; x++) {
            ringBuffer.dequeue();
            double random = Math.random()  - .5;
            ringBuffer.enqueue(random);
        }
    }

    public void tic() {

        // implement tic method
        double removed = this.ringBuffer.dequeue();
        double avg = (this.sample() + removed) / 2;
        this.ringBuffer.enqueue(avg);
        this.ticCount++;
    }

    public double sample() {

        // implement sample method
        return this.ringBuffer.peek();
    }

    public int time() {

        // implement time method
        return this.ticCount;
    }

}