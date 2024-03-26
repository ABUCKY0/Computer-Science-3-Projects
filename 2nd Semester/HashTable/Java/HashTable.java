package HashTable.Java;

public class HashTable {

    private int capacity;
    private int size;
    private String[] string_arr;
    private Double[] numerical_arr;

    /**
     * HashTable constructor
     * 
     * @param capacity max capacity
     * @param data     string to be stored
     * @implNote Time Complexity: O()
     */
    public HashTable(int capacity, String data) {

        this.capacity = capacity;

        // Keep it Odd
        if (capacity % 2 == 0) {
            this.capacity++;
        }
        this.string_arr = new String[this.capacity];

        int adder = 0;
        for (int i = 0; i < data.length() - 1; i++) {
            adder += Integer.parseInt(data.substring(i, i + 1));
        }

        // 0x7FFFFFFF is the maximum value of a 32-bit signed integer
        int spot = (adder & 0x7FFFFFFF) % this.capacity;
        this.string_arr[spot] = data;
        this.size = 1;
    }

    /**
     * HashTable constructor
     * 
     * @param capacity max capacity
     * @param data     number to be stored
     */
    public HashTable(int capacity, Double data) {

        this.capacity = capacity;

        // Keep it Odd
        if (capacity % 2 == 0) {
            this.capacity++;
        }


        this.numerical_arr = new Double[this.capacity];

        int spot = (data.intValue() & 0x7FFFFFFF) % this.capacity;
        this.numerical_arr[spot] = data;
        this.size = 1;
    }

    /**
     * Hash function for string data
     * 
     * @param data string to be hashed
     * @return hash value
     */
    public int hashCode(String data) {
        int adder = 0;
        for (int i = 0; i < data.length() - 1; i++) {
            adder += Integer.parseInt(data.substring(i, i + 1));
        }

        return (adder & 0x7FFFFFFF) % capacity;
    }

    /**
     * Hash function for numerical data
     * 
     * @param data number to be hashed
     * @return hash value
     */
    public int hashCode(Double data) {
        return (data.intValue() & 0x7FFFFFFF) % this.capacity;
    }

    /**
     * Insert string data into the hash table
     * 
     * @param data string to be inserted
     */
    public void insert(String data) {
        int spot = hashCode(data);
        if (this.size == this.capacity) {
            return;
        }
        if (string_arr[spot] == null) {
            // Spot is Available
            this.string_arr[spot] = data;
            this.size++;
            return; // 3/26 forgot to return out of here
        }
        // If spot is occupied, use quadratic probing.
        spot = (spot + 1) % this.capacity;
        int collisions = 1;
        while (this.string_arr[spot] != null) {
            // Keeps going until you make it to a null value.
            collisions *= 2;
            spot = (spot * collisions) % this.capacity;
        }
        this.string_arr[spot] = data;
        this.size += 1;
    }
    
    /**
     * Insert numerical data into the hash table
     * 
     * @param data number to be inserted
     */
    public void insert(Double data) {
        int spot = hashCode(data);
        // this.numerical_arr[spot] = data;
        // this.size++;
        if (this.size == this.capacity) {
            return;
        }
        if (numerical_arr[spot] == null) {
            //Spot is available
            this.numerical_arr[spot] = data;
            this.size++;
            return; // 3/26 forgot to return out of here
        }
        // If spot is occupied, use quadratic or linear probing.
        spot = (spot + 1) % this.capacity;
        int collisions = 1;
        while (this.numerical_arr[spot] != null) {
            collisions *= 2;
            spot = (spot * collisions) % this.capacity;
        }
        this.numerical_arr[spot] = data;
        this.size += 1;
    }

    public static void main(String[] args) {

    }
}