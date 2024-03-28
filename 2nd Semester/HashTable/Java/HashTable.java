package HashTable.Java;

@SuppressWarnings("unchecked")
public class HashTable<E> {
    private E[] array;
    private int capacity;
    private int size;
    private int resizeAmt;

    /**
     * 
     * @param capacity
     * @param data
     */
    public HashTable(int capacity, E data) {
        this.capacity = capacity;
        if (capacity % 2 == 0) {
            this.capacity++;
        }

        int spot = this.hashCode(data);
        this.array[spot] = data;
        this.array = (E[]) new Object[this.capacity];
        this.size = 1;
        this.resizeAmt = 0;

    }

    /**
     * 
     * @param data
     * @return
     */
    public int hashCode(E unformattedData) {
        int returnval = 0;
        if (unformattedData instanceof String) {

            for (int i = 0; i < ((String) unformattedData).length() - 1; i++) {
                returnval += Integer.parseInt(((String) unformattedData).substring(i, i + 1));
            }
        } else if (unformattedData instanceof Double) {
            returnval = (((Double) unformattedData).intValue() & 0x7FFFFFFF) % this.capacity;
        } else {
            throw new IllegalArgumentException();
        }
        return returnval;
    }

    public void insert(E unformattedData) {

        int spot = hashCode(unformattedData);
        // this.numerical_arr[spot] = data;
        // this.size++;
        if (this.size == this.capacity) {
            return;
        }
        if (array[spot] == null) {
            // Spot is available
            this.array[spot] = unformattedData;
            this.size++;
            return; // 3/26 forgot to return out of here
        }
        // If spot is occupied, use quadratic or linear probing.
        spot = (spot + 1) % this.capacity;
        int collisions = 1;
        while (this.array[spot] != null) {
            collisions *= 2;
            spot = (spot * collisions) % this.capacity;
        }
        this.array[spot] = unformattedData;
        this.size += 1;
    }

    public static void main(String[] args) {
    }
}
