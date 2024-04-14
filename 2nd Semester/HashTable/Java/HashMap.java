package HashTable.Java;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.logging.*;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class HashMap<E> { // extends HashSet<E> {

  private LinkedList<E>[] hashTable; // This is an array of LinkedList<E>
  private int size;
  private int capacity;
  public static Logger LOGGER = Logger.getLogger(HashMap.class.getName());

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
    LOGGER.info("Creating a new HashMap with capacity " + capacity + " and no data.");
    this.hashTable = new LinkedList[capacity];

    // ("this.size = 0; this.capacity = " + capacity);
    this.size = 0;
    this.capacity = capacity;
  }

  public int getKey(E data) {
    return this.hashCode(data);
  }

  public boolean insert(E data) {
    int key = this.getKey(data);
    if (hashTable[key] != null && hashTable[key].contains(data)) {
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
    for (int i = 0; i < this.capacity; i++) {
      if (this.hashTable[i] != null && this.hashTable[i].contains(data) == true) {
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
    for (int i = 0; i < this.capacity; i++) {
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
    // Loop through each LinkedList, and if the size is greater than 1, sort;
    for (int i = 0; i < this.hashTable.length; i++) {
      if (this.hashTable[i].size() > 1) {
        LinkedList<E>[] temp = (LinkedList<E>[]) this.hashTable[i].toArray();
        Arrays.sort(temp);
        this.hashTable[i] = new LinkedList<E>();
        for (int j = 0; j < temp.length; j++) {
          this.hashTable[i].add((E) temp[j]);
        }
      }

    }
    // Now that the Array is sorted, add each bucket to an ArrayList.
    ArrayList<E> sorted = new ArrayList<>();
    for (int j = 0; j < this.hashTable.length; j++) {
      sorted.addAll(this.hashTable[j]);
    }

    return sorted;

  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < this.hashTable.length; i++) {
      sb.append("b").append(i + 1);
      if (i != this.hashTable.length - 1) {
        sb.append(", ");
      }
    }
    sb.append("]\n");

    int maxBucketSize = Arrays.stream(this.hashTable).filter(bucket -> bucket != null).mapToInt(LinkedList::size).max()
        .orElse(0);

    for (int j = 0; j < maxBucketSize; j++) {
      for (int i = 0; i < this.hashTable.length; i++) {
        if (this.hashTable[i] != null && j < this.hashTable[i].size()) {
          sb.append(this.hashTable[i].get(j));
        } else {
          sb.append(" ");
        }
        if (i != this.hashTable.length - 1) {
          sb.append("\t");
        }
      }
      sb.append("\n");
    }

    return sb.toString();
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

  public static void main(String[] args) {

    try {
      LogManager.getLogManager().readConfiguration(
          new FileInputStream("/workspaces/Computer-Science-3-Projects/other utilities/logging.properties"));
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Could not read 'log.properties' file.", e);
      try {
        LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
      } catch (IOException x) {
        LOGGER.log(Level.SEVERE, "Could not read 'log.properties' file.", x);
      }
    }


    HashMap<Integer> map = new HashMap<>(10);
    LOGGER.info("HashMap after constructor: " + map.toString());

    map.insert(1);
    LOGGER.info("HashMap after inserting 1: " + map.toString());

    Integer removedValue = map.remove(1);
    LOGGER.info("Removed value: " + removedValue);
    LOGGER.info("HashMap after removing 1: " + map.toString());

    map.clear();
    LOGGER.info("HashMap after clear: " + map.toString());

    map.insert(1);
    LOGGER.info("HashMap after inserting 1: " + map.toString());

    boolean containsKey = map.containsKey(map.getKey(1));
    LOGGER.info("Contains key for 1: " + containsKey);

    boolean containsValue = map.containsValue(1);
    LOGGER.info("Contains value 1: " + containsValue);

    for (int i = 2; i <= 20; i++) {
      map.insert(i);
  }

    ArrayList<Integer> keys = map.getKeySet();
    LOGGER.info("Key set: " + keys.toString());

    ArrayList<Integer> values = map.getValues(map.getKey(1));
    LOGGER.info("Values for key 1: " + values.toString());
  }
}
