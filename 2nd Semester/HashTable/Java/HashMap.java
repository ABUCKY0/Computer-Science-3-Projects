package HashTable.Java;

import java.util.LinkedList;
import java.util.ArrayList;
@SuppressWarnings("unchecked")
public class HashMap<E> extends HashSet<E> {

    private LinkedList<E>[] hashTable;
    private int size;

    public HashMap(int capacity, E data) {
        super(capacity, data);
        this.hashTable = new LinkedList[capacity];
        int key = super.hashCode(data);

        this.hashTable[key] = new LinkedList<E>();
        this.hashTable[key].addFirst(data);
        this.size = 1;
    }

    public HashMap(int capacity) {
        super(capacity, null);
        this.hashTable = new LinkedList[capacity];
        
        this.size = 0;

    }

    public int getKey(E data) {
        return super.hashCode();
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
        }
        else {
            return null;
        }
    }

    public void clear() {}

    
    public LinkedList<E>[] copy(){}
    public boolean containsKey(int key) {}
    public boolean containsValue(E data) {}
    public boolean isEmpty() {}
    public int[] getKeySet() {} // return an array of all spots that have values
    public ArrayList<E> getValues() {}
    public ArrayList<E> bucketSort() {}
    public String toString() {}

    public static void main(String args[]) {

    }
}
