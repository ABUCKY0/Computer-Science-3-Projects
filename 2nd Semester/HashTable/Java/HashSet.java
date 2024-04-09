package HashTable.Java;
import java.util.List;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class HashSet<E> extends HashTable<E> {

    /**
     * Constructor for HashSet
     * 
     * @param capacity initial capacity of the hash table
     * @param data     initial data to be inserted into the hash table
     * @note Time Complexity: O(1)
     */
    public HashSet(int capacity, E data) {
        super(capacity, data);
    }

    /**
     * Insert into the hashset
     * 
     * @param data data to be added
     * @return true if it wasn't found, false otherwise
     * @note Time Complexity: O(1) average case, O(n) worst case
     */
    public boolean insert(E data) {
        if (this.search(data) == false) {
            super.insert(data);
            return true;
        }
        return false;
    }

    /**
     * Clear the Array
     * 
     * @note Time Complexity: O(1)
     * @note Just throws everything off into memory to be Garbage Collected
     */
    public void clear() {
        this.capacity = 11;
        this.array = (E[]) new Object[this.capacity];
        this.size = 0;
        this.resizeAmt = 0;
    }

    /**
     * Returns a copy of the array
     * 
     * @note Time Complexity: O(1)
     */
    public E[] clone() {
        return this.array.clone();
    }

    /**
     * Removes all elements in the Table that are in collection
     * @param collection elements to remove
     * @return True if all were removed, else false.
     * 
     * @note Time Complexity: O(n*m) where n is the size of the collection and m is the size of the table
     * @implNote 
     */
    public boolean removeAll(E[] collection) {
        boolean flag = true;
        for (E object : collection) {
            E obj = super.remove(object);
            if (obj == null) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 
     * @param collection
     * @note Time Complexity: O(n)
     * @return
     */
    public boolean addAll(E[] collection) {
        boolean flag = true;
        for (E object : collection) {
            boolean obj = super.insert(object);
            if (obj == false) {
                flag = false;
            }
        }
        return flag;
    }
    /**
     * 
     * @param collection
     * @note Time Complexity: O(n)
     * @return
     */
    public boolean containsAll(E[] collection) {
        for (E object : collection) {
            boolean obj = super.search(object);
            if (obj == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @return Internal Array as List
     * @note Time Complexity: O(n)
     */
    public List<E> toList() {
        return Arrays.asList(this.array);
    }
    

    public static void main(String[] args) {
        // Initial setup
        HashSet<String> hashSet = new HashSet<>(10, "init");
        System.out.println("Initial insert: " + hashSet.insert("One")); // Expected: true

        // Test insert
        System.out.println("Duplicate insert (should be false): " + hashSet.insert("One")); // Expected: false
        System.out.println("New insert: " + hashSet.insert("Two")); // Expected: true

        // Test clear
        hashSet.clear();
        System.out.println("After clear, contains 'One': " + hashSet.search("One")); // Expected: false

        // Test removeAll
        String[] toRemove = {"Three"};
        System.out.println("Remove 'Three': " + hashSet.removeAll(toRemove)); // Expected: true
        System.out.println("Contains 'Three' after removal: " + hashSet.search("Three")); // Expected: false

        // Test addAll
        String[] toAdd = {"Four", "Five"};
        hashSet.addAll(toAdd);
        System.out.println("After addAll, contains 'Four': " + hashSet.search("Four")); // Expected: true

        // Test containsAll
        System.out.println("Contains all ['Four', 'Five']: " + hashSet.containsAll(toAdd)); // Expected: true

        // Test toList
        System.out.println("List representation: " + hashSet.toList()); // Expected: [null, null, ..., "Four", "Five", ...]
    }

}