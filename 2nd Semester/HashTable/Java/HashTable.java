package HashTable.Java;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class HashTable<E> {
    protected E[] array;
    protected int capacity;
    protected int size;
    protected int resizeAmt;

    /**
     * Constructor for HashTable
     * 
     * @param capacity initial capacity of the hash table
     * @param data     initial data to be inserted into the hash table
     * @note Time Complexity: O(1)
     */
    public HashTable(int capacity, E data) {
        this.capacity = capacity;
        if (capacity % 2 == 0) {
            this.capacity++;
        }

        this.array = (E[]) new Object[this.capacity];

        int spot = this.hashCode(data);
        this.array[spot] = data;
        this.size = 1;
        this.resizeAmt = 0;

    }

    /**
     * Hash function for the hash table
     * 
     * @param unformattedData data to be hashed
     * @return hashed value
     * @note Time Complexity: O(n) for String, O(1) for Double
     */
    public int hashCode(E unformattedData) {
        int returnval = 0;
        if (unformattedData instanceof String) {
            for (int i = 0; i < ((String) unformattedData).length(); i++) {
                returnval += (int) ((String) unformattedData).charAt(i);
            }
        } else if (unformattedData instanceof Double) {
            returnval = (((Double) unformattedData).intValue() & 0x7FFFFFFF);
        } else {
            throw new IllegalArgumentException();
        }
        return returnval % this.capacity; // Ensure the returned index is within the valid range
    }

    /**
     * Insert data into the hash table
     * 
     * @param unformattedData data to be inserted
     * @note Time Complexity: O(1) average case, O(n) worst case
     */
    public boolean insert(E unformattedData) {
        boolean flag = true;
        if (this.size == this.capacity) {
            this.resize();
        }

        int spot = hashCode(unformattedData) % (this.capacity - 1);

        if (array[spot] == null) {
            this.array[spot] = unformattedData;
            this.size++;
            flag = false;
        }

        // If spot is occupied, use quadratic probing.
        if (flag) {
            int collisions = 1;
            while (this.array[spot] != null) {
                spot = (spot + collisions * collisions) % this.capacity;
                spot = spot % this.capacity; // Ensure the spot is within the valid range
                collisions++;
            }

            this.array[spot] = unformattedData;
            this.size++;

        }

        if (this.loadFactor() >= 0.45 || this.size >= this.capacity) {
            this.resize();
        }
        return true;
    }

    /**
     * Get the size of the hash table
     * 
     * @return size of the hash table
     * @note Time Complexity: O(1)
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Get the capacity of the hash table
     * 
     * @return capacity of the hash table
     * @note Time Complexity: O(1)
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Get the element at a specific index in the hash table
     * 
     * @param index index of the element
     * @return element at the specified index
     * @note Time Complexity: O(1)
     */
    public E getElement(int index) {
        return this.array[index];
    }

    /**
     * Check if the hash table is empty
     * 
     * @return true if the hash table is empty, false otherwise
     * @note Time Complexity: O(1)
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Check if the hash table is full
     * 
     * @return true if the hash table is full, false otherwise
     * @note Time Complexity: O(1)
     */
    public boolean isFull() {
        return this.size == this.capacity;
    }

    /**
     * Get the keys of the hash table
     * 
     * @return list of keys in the hash table
     * @note Time Complexity: O(n)
     */
    public ArrayList<Integer> getKeys() {
        ArrayList<Integer> keys = new ArrayList<>();
        for (int i = 0; i < this.capacity; i++) {
            if (array[i] != null) {
                keys.add(i);
            }
        }
        return keys;
    }

    /**
     * Get the values of the hash table
     * 
     * @return list of values in the hash table
     * @note Time Complexity: O(n)
     */
    public ArrayList<E> getValues() {
        ArrayList<E> values = new ArrayList<>();
        for (E element : array) {
            if (element != null) {
                values.add(element);
            }
        }
        return values;
    }

    /**
     * Remove a specific data from the hash table
     * 
     * @param data data to be removed
     * @return Data Removed
     * @note Time Complexity: O(1) average case, O(n) worst case
     */
    public E remove(E data) {
        int spot = hashCode(data);
        int initialSpot = spot;
        while (array[spot] != null) {
            if (array[spot].equals(data)) {
                E removedElement = array[spot];
                array[spot] = null; // Mark the element as removed
                // Optionally update the size of the hash table
                size--;
                return removedElement;
            }
            spot = (spot + 1) % capacity;
            // If we have looped back to the initial spot, break to avoid infinite loop
            if (spot == initialSpot)
                break;
        }
        // Element not found
        return null;
    }

    /**
     * Get the load factor of the hash table
     * 
     * @return load factor of the hash table
     * @note Time Complexity: O(1)
     */
    public double loadFactor() {
        return (double) size / capacity;
    }

    /**
     * Search for a specific data in the hash table
     * 
     * @param data data to be searched
     * @return true if found, false otherwise
     * @note Time Complexity: O(1) average case, O(n) worst case
     */
    public boolean search(E data) {
        int index = hashCode(data);
        int initialIndex = index;

        if (this.array[index] != null && this.array[index].equals(data))
            return true;
        else if (this.array[index] != null && array[rehash(data)].equals(data))
            return true;
        else {
            // Loop until we find the element or encounter a null position
            while (array[index] != null) {
                // If the element is found at the current index, return true
                if (array[index] == null)
                    continue;
                if (array[index].equals(data)) {
                    return true;
                }
                // Move to the next position using linear probing
                index = (index + 1) % capacity;
                // If we have looped back to the initial index, break to avoid infinite loop
                if (index == initialIndex)
                    break;
            }
        }
        // Element not found
        return false;
    }

    /**
     * Resize the hash table when load factor exceeds 0.45 or size equals capacity
     * 
     * @note Time Complexity: O(n)
     */
    public void resize() {
        this.resizeAmt++;
        int newcap = (int) (this.capacity * 1.5);
        E[] newarr = (E[]) new Object[newcap];
        for (int i = 0; i < this.capacity; i++) {
            E elm = this.array[i];
            if (elm != null) {
                int index = hashCode(elm);
                // Handle collisions using linear probing
                while (newarr[index] != null) {
                    index = (index + 1) % newcap; // Move to the next position
                }
                newarr[index] = elm; // Insert the element into the new array
            }
        }
        this.capacity = newcap;
        this.array = newarr;
    }

    /**
     * Rehash function for the hash table
     * 
     * @param unformattedData data to be rehashed
     * @return rehashed value
     * @note Time Complexity: O(n) for String, O(1) for Double
     */
    public int rehash(E unformattedData) {
        int hash = 7; // Initial hash value, a prime number
        if (unformattedData instanceof String) {
            for (int i = 0; i < ((String) unformattedData).length(); i++) {
                hash = hash * 31 + ((String) unformattedData).charAt(i);
            }
        } else if (unformattedData instanceof Double) {
            long bits = Double.doubleToLongBits((Double) unformattedData);
            hash = hash * 31 + (int) (bits ^ (bits >>> 32));
        } else {
            throw new IllegalArgumentException();
        }
        return (hash & 0x7FFFFFFF) % this.capacity;
    }

    /**
     * Convert the hash table to a string
     * 
     * @return string representation of the hash table
     * @note Time Complexity: O(n)
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E element : array) {
            if (element != null) {
                sb.append(element.toString()).append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Create a new HashTable
        HashTable<String> hashTable = new HashTable<>(10, "initialData");

        // Test the initial state of the HashTable
        System.out.println("Initial size: " + hashTable.getSize()); // Should print: 1
        System.out.println("Initial capacity: " + hashTable.getCapacity()); // Should print: 11

        // Test inserting data into the HashTable
        hashTable.insert("data1");
        hashTable.insert("data2");
        hashTable.insert("data3");
        hashTable.insert("data4");
        hashTable.insert("data8");
        hashTable.insert("data7");
        hashTable.insert("data6");
        hashTable.insert("data5");
        System.out.println("Size after inserting 3 elements: " + hashTable.getSize()); // Should print: 4

        // Test searching for data in the HashTable
        System.out.println("Search for 'data1': " + (hashTable.search("data1"))); // Should print: true
        System.out.println("Search for 'data4': " + (hashTable.search("data4"))); // Should print: false

        // Test removing data from the HashTable
        hashTable.remove("data1");
        System.out.println("Size after removing 'data1': " + hashTable.getSize()); // Should print: 3
        System.out.println("Search for 'data1' after removal: " + (hashTable.search("data1"))); // Should print: false

        // Test resizing the HashTable
        for (int i = 4; i <= 10; i++) {
            hashTable.insert("data" + i);
        }
        System.out.println("Size after inserting 7 more elements: " + hashTable.getSize()); // Should print: 10
        System.out.println("Capacity after inserting 7 more elements: " + hashTable.getCapacity()); // Should print: 17
                                                                                                    // (If Resized)
    }
}
