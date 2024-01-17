import java.util.Arrays;

public class Set {
    private int cardinality; // num of elements present
    private int size; // max num of elements
    private int[] elements;

    /**
     * Size Only Constructor for the Set Class
     * 
     * @param size The size of the set
     */
    public Set(int size) {
        this.size = size;
        this.elements = new int[size];
        this.cardinality = 0;
    }

    /**
     * Adds the given element to the set if possible.
     * 
     * @param element The element to add
     * @return true if successful, false if not
     */
    public boolean add(int element) { // O'(N)
        if (this.size == this.cardinality) {
            return false;
        }
        if (this.contains(element) == false) {
            this.elements[cardinality] = element;
            this.cardinality += 1;
            return true;
        }
        return false;
    }

    /**
     * Adds all the elements from the given array to the set.
     * 
     * @param arr the array containing the elements to be added
     * @return true if all elements were successfully added, false otherwise
     */
    public boolean addAll(int[] arr) {// O(N^2)
        int availableSpace = (this.size - this.cardinality);
        if (arr.length > availableSpace) {
            return false;
        }

        boolean addAllFlag = true;
        for (int element : arr) {
            if (this.add(element) == false) {
                addAllFlag = false;
                System.out.println("Couldn't add " + element);
            }
        }
        return addAllFlag;
    }

    /**
     * Clears the array
     */
    public void clear() { // O(1)
        this.elements = null;
        this.cardinality = 0;
    }

    /**
     * Checks if the set contains a specific element.
     * 
     * @param input the element to be checked
     * @return true if the set contains the element, false otherwise
     */
    public boolean contains(int input) { // O(N)
        for (int element : this.elements) {
            if (element == input) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the main array contains all of the given elements
     * 
     * @param arr The array of elements to check for
     * @return true if all elements are found, false otherwise.
     */
    public boolean containsAll(int[] arr) { // O'(N^2)
        for (int element : arr) {
            if (this.contains(element) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the two sets are equal
     * 
     * @param compare The set to compare
     * @return returns true if the sets are the same
     */
    public boolean equals(Set compare) {
        int[] compareArr = compare.getSet();
        boolean areArrEqual = Arrays.equals(this.elements, compareArr);
        boolean isCardinalityEqual = this.cardinality == compare.getCardinality();
        if (areArrEqual && isCardinalityEqual) {
            return true;
        }
        return false;
    }

    /**
     * Finds the index of a given element
     * 
     * @param element to search for
     * @return returns the index of an element
     */
    public int findIndex(int element) { // O(N)
        for (int i = 0; i < this.getCardinality(); i++) {
            if (this.elements[i] == element) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes the specified element from the set.
     *
     * @param element the element to be removed
     * @return true if the element was successfully removed, false otherwise
     */
    public boolean remove(int element) { // O(2N)
        int index = this.findIndex(element);
        if (index != -1) {
            for (int i = index; i < this.getCardinality() - 1; i++) {
                this.elements[i] = this.elements[i + 1];
            }
            this.cardinality -= 1;
            return true;
        }
        return false;
    }

    /**
     * Returns the cardinality of the set.
     *
     * @return the number of elements in the set
     */
    public int getCardinality() {
        return this.cardinality;
    }

    /**
     * Returns an array representation of the set.
     *
     * @return an array containing the elements of the set
     */
    public int[] getSet() {
        return elements;
    }

    /**
     * Checks if the set is empty.
     * 
     * @return true if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.cardinality == 0;
    }

    /**
     * Returns a string representation of the set.
     * The string representation consists of a list of the set's elements enclosed in square brackets.
     * If the set is empty, an empty set representation is returned.
     *
     * @return a string representation of the set
     */
    public String toString() {
        String s = "[";
        if (this.cardinality >= 1 && this.elements != null) {
            for (int i = 0; i < this.cardinality - 1; i++) {
                s += this.elements[i];
                s += ",";
            }

            s += this.elements[this.cardinality - 1];
        }
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        // Create a new Set object
        Set set = new Set(10);

        // Test add method
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        // Test addAll method
        int[] arr = { 6, 7, 8, 9, 10 };
        set.addAll(arr);

        // Test contains method
        System.out.println("Set contains 3: " + set.contains(3)); // Expected output: true
        System.out.println("Set contains 11: " + set.contains(11)); // Expected output: false

        // Test containsAll method
        int[] arr2 = { 1, 2, 3 };
        System.out.println("Set contains all elements in arr2: " + set.containsAll(arr2)); // Expected output: true

        int[] arr3 = { 1, 2, 11 };
        System.out.println("Set contains all elements in arr3: " + set.containsAll(arr3)); // Expected output: false

        // Test remove method
        System.out.println("Remove element 4: " + set.remove(4)); // Expected output: true
        System.out.println("Remove element 11: " + set.remove(11)); // Expected output: false

        // Test getCardinality method
        System.out.println("Cardinality of set: " + set.getCardinality()); // Expected output: 9

        // Test getSet method
        int[] setArray = set.getSet();
        System.out.println("Set array: " + Arrays.toString(setArray)); // Expected output: [1, 2, 3, 5, 6, 7, 8, 9, 10]

        // Test isEmpty method
        System.out.println("Is set empty: " + set.isEmpty()); // Expected output: false

        // Test clear method
        set.clear();
        System.out.println("Is set empty after clear: " + set.isEmpty()); // Expected output: true
    }
}