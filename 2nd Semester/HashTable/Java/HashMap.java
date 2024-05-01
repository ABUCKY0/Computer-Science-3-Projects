package HashTable.Java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.logging.*;

@SuppressWarnings("unchecked")
public class HashMap<E> { // extends HashSet<E> {

  private LinkedList<E>[] hashTable; // This is an array of LinkedList<E>
  private int size;
  private int capacity;
  public static Logger LOGGER = Logger.getLogger(HashMap.class.getName());
  /**
   * Implements a Comparator for the two types this class supports
   *
   * @implNote Time Complexity: O(1)
   */
  public Comparator<E> hashmapcompare =
      new Comparator<E>() {
        @Override
        public int compare(E p1, E p2) {
          if (p1 instanceof String && p2 instanceof String) {
            return ((String) p1).compareTo((String) p2);
          } else if (p1 instanceof Integer && p2 instanceof Integer) {
            return ((Integer) p1).compareTo((Integer) p2);
          } else if (p1 instanceof Double && p2 instanceof Double) {
            return ((Double) p1).compareTo((Double) p2);
          } else {
            throw new IllegalArgumentException("Invalid type for comparison");
          }
        }
      };

  /**
   * Constructor with Data
   *
   * @param capacity The maximum capacity of the HashMap
   * @param data a data source to insert into the array.
   * @implNote Time Complexity: O(1)
   */
  public HashMap(int capacity, E data) {
    // super(capacity, data);

    this.capacity = capacity;
    this.hashTable = new LinkedList[capacity];
    int key = hashCode(data);

    this.hashTable[key] = new LinkedList<E>();
    this.hashTable[key].addFirst(data);
    this.size = 1;
  }

  /**
   * Constructor
   *
   * @param capacity The maximum capacity of the HashMap
   * @implNote Time Complexity: O(1)
   */
  public HashMap(int capacity) {
    // super(capacity, null);
    this.hashTable = new LinkedList[capacity];

    // ("this.size = 0; this.capacity = " + capacity);
    this.size = 0;
    this.capacity = capacity;
  }

  /**
   * Gloriified HashCode wrapper
   *
   * @param data the data to hash
   * @return the hashcode
   * @see #hashCode(Object)
   */
  public int getKey(E data) {
    return this.hashCode(data);
  }

  /**
   * Inserts the provided data into the HashMap.
   *
   * @param data the data to be inserted
   * @return true if the data was inserted successfully, false otherwise
   * @implNote Time Complexity: O(1)
   */
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

  /**
   * Removes the provided data from the HashMap.
   *
   * @param data the data to be removed
   * @return the removed data if it was found, null otherwise
   * @implNote Time Complexity: O(1)
   */
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

  /**
   * Clears the HashMap.
   *
   * @implNote Time Complexity: O(1)
   */
  public void clear() {
    this.hashTable = new LinkedList[this.capacity];
    this.size = 0;
  }

  public LinkedList<E>[] copy() {
    return this.hashTable.clone();
  }

  /**
   * Checks if the HashMap contains the provided key.
   *
   * @param key the key to be checked
   * @return true if the key is found, false otherwise
   * @implNote Time Complexity: O(1)
   */
  public boolean containsKey(int key) {
    return this.hashTable[key] != null;
  }

  /**
   * Checks if the HashMap contains the provided value.
   *
   * @param data the value to be checked
   * @return true if the value is found, false otherwise
   * @implNote Time Complexity: O(n)
   */
  public boolean containsValue(E data) {
    for (int i = 0; i < this.capacity; i++) {
      if (this.hashTable[i] != null && this.hashTable[i].contains(data) == true) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the HashMap is empty.
   *
   * @return true if the HashMap is empty, false otherwise
   * @implNote Time Complexity: O(1)
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns a set of all keys in the HashMap.
   *
   * @return an ArrayList of all keys
   * @implNote Time Complexity: O(n)
   */
  public ArrayList<Integer> getKeySet() {
    ArrayList<Integer> keys = new ArrayList<>();
    for (int i = 0; i < this.capacity; i++) {
      if (this.hashTable[i] != null) {
        keys.add(i);
      }
    }
    return keys;
  }

  /**
   * Returns all values at the provided index in the HashMap.
   *
   * @param index the index to get values from
   * @return an ArrayList of all values at the index
   * @implNote Time Complexity: O(1)
   */
  public ArrayList<E> getValues(int index) { // return an array of all spots that have values
    ArrayList<E> values = new ArrayList<>();
    if (index > this.size) {
      return null;
    }
    values.addAll(this.hashTable[index]);
    return values;
  }

  /**
   * Sorts each LinkedList individually, then adds all elements one by one to an ArrayList
   *
   * @implNote Time Complexity: O()
   * @return
   */
  public ArrayList<E> bucketSort() {
    ArrayList<E> result = new ArrayList<>();
    for (LinkedList<E> bucket : hashTable) {
      if (bucket != null) {
        ArrayList<E> bucketList = new ArrayList<>(bucket);
        Collections.sort(bucketList, this.hashmapcompare);
        result.addAll(bucketList);
      }
    }
    return result;
  }

  /**
   * returns a string representing the builtin array (and linkedlists) logically
   *
   * @return a logical layout of the array
   * @implNote Time Complexity: O(n) where n represents the number of individual elements across all
   *     linkedlists
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < this.hashTable.length; i++) {
      if (this.hashTable[i] == null) {
        sb.append("null");
        if (i != this.hashTable.length - 1) {
          sb.append(",\t");
        }
      } else {
        sb.append("l").append(i + 1);
        if (i != this.hashTable.length - 1) {
          sb.append(",\t");
        }
      }
    }
    sb.append("]\n");

    int maxBucketSize =
        Arrays.stream(this.hashTable)
            .filter(bucket -> bucket != null)
            .mapToInt(LinkedList::size)
            .max()
            .orElse(0);

    for (int j = 0; j < maxBucketSize; j++) {
      for (int i = 0; i < this.hashTable.length; i++) {
        if (this.hashTable[i] != null && j < this.hashTable[i].size()) {
          sb.append(String.format("%-8s", this.hashTable[i].get(j)));
        } else {
          sb.append(String.format("%-8s", " "));
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
   * @implNote Time Complexity: O(n) for String, O(1) for Double
   * @throws IllegalArgumentException if datatype isn't Double, Int, or String
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
      throw new IllegalArgumentException(
          unformattedData.getClass().getSimpleName() + " isn't an allowed type.");
    }
    return returnval % this.capacity; // Ensure the returned index is within the valid range
  }

  public static void main(String[] args) {
    try {
      // CodeHS
      FileInputStream codehs = new FileInputStream("logging.properties");
      LogManager.getLogManager().readConfiguration(codehs);
      LOGGER.log(Level.INFO, "Loaded on CodeHS Successfully");
    } catch (IOException e) {
      // GH CodeSpaces
      LOGGER.log(
          Level.SEVERE, "Could not read 'log.properties' file from default CodeHS location.", e);
      try {
        FileInputStream codespaces =
            new FileInputStream(
                "/workspaces/Computer-Science-3-Projects/other utilities/logging.properties");
        LogManager.getLogManager().readConfiguration(codespaces);
        LOGGER.log(Level.INFO, "Loaded on Codespace Successfully");
      } catch (IOException x) {
        // None worked
        LOGGER.log(Level.SEVERE, "Could not read 'log.properties' file.", x);
      }
    }

    LOGGER.info("Expand the console window to the full window width for the bext views.");

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
      if (i == 3 || i == 13 || i == 8 || i == 18 || i == 5) {
        continue;
      }
      map.insert(i);
    }

    ArrayList<Integer> keys = map.getKeySet();
    LOGGER.info("Key set: " + keys.toString());

    ArrayList<Integer> values = map.getValues(map.getKey(1));
    LOGGER.info("Values for key 1: " + values.toString());

    LOGGER.info("HashMap ToString:\n" + map.toString());

    LOGGER.info("BucketSort output" + map.bucketSort());
  }
}
