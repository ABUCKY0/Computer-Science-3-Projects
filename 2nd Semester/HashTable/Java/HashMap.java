package HashTable.Java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class HashMap<E> { // extends HashSet<E> {

  private LinkedList<E>[] hashTable; // This is an array of LinkedList<E>
  private int size;
  private int capacity;

  public HashMap(int capacity, E data) {
    // super(capacity, data);
    this.capacity = capacity;
    this.hashTable = new LinkedList[capacity];
    int key = hashCode(data);

    this.hashTable[key] = new LinkedList<E>();
    this.hashTable[key].addFirst(data);
    this.size = 1;
  }

  public HashMap(int capacity) {
    // super(capacity, null);
    this.hashTable = new LinkedList[capacity];

    this.size = 0;
    this.capacity = capacity;
  }

  public int getKey(E data) {
    return hashCode();
  }

  public boolean insert(E data) {
    int key = this.getKey(data);
    if (hashTable[key].contains(data)) {
      return false;
    } else {
      if (this.hashTable[key] == null) {
        this.hashTable[key] = new LinkedList<E>();
        this.hashTable[key].addFirst(data);
        this.size += 1;
        return true;
      } else {
        this.hashTable[key].addLast(data);
        this.size += 1;
        return true;
      }
    }
  }

  public E remove(E data) {
    int key = this.getKey(data);
    if (this.hashTable[key].contains(data)) {
      this.hashTable[key].remove(data);
      this.size -= 1;
      return data;
    } else {
      return null;
    }
  }

  public void clear() {
    this.hashTable = new LinkedList[this.capacity];
    this.size = 0;
  }

  public LinkedList<E>[] copy() {
    return this.hashTable.clone();
  }

  public boolean containsKey(int key) {
    return this.hashTable[key] != null;
  }

  public boolean containsValue(E data) {
    for (int i = 0; i < capacity; i++) {
      if (this.hashTable[i].contains(data) == true) {
        return true;
      }
    }
    return false;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public ArrayList<Integer> getKeySet() {
    ArrayList<Integer> keys = new ArrayList<>();
    for (int i = 0; i < this.size; i++) {
      if (this.hashTable[i] != null) {
        keys.add(i);
      }
    }
    return keys;
  }

  public ArrayList<E> getValues(int index) { // return an array of all spots that have values
    ArrayList<E> values = new ArrayList<>();
    if (index > this.size) {
      return null;
    }
    values.addAll(this.hashTable[index]);
    return values;
  }

  public ArrayList<E> bucketSort() {
    return new ArrayList<>();

    
    
  }

  public String toString() {
    return new String();
  }

  /**
   * Hash function for the hashmap
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
    } else if (unformattedData instanceof Integer) {
      returnval = (((Integer) unformattedData).intValue() & 0x7FFFFFFF);
    } else {
      throw new IllegalArgumentException(unformattedData.getClass().getSimpleName() + " isn't an allowed type.");
    }
    return returnval % this.capacity; // Ensure the returned index is within the valid range
  }

  public static void main(String args[]) {
    HashMap<Integer> e = new HashMap<Integer>(10, 1);
  }
}
