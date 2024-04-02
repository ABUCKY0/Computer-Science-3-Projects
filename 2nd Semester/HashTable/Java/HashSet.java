package HashTable.Java;

import java.util.List;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class HashSet<E> extends HashTable<E> {
    public HashSet(int capacity, E data) {
        super(capacity, data);
    }

    public boolean insert(E data) {
        if (this.search(data) == false) {
            super.insert(data);
            return true;
        }
        return false;
    }

    public void clear() {
        this.capacity = 11;
        this.array = (E[]) new Object[this.capacity];
        this.size = 0;
        this.resizeAmt = 0;
    }

    public E[] clone() {
        return this.array.clone();
    }

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

    public boolean containsAll(E[] collection) {
        for (E object : collection) {
            boolean obj = super.search(object);
            if (obj == false) {
                return false;
            }
        }
        return true;
    }


    // public List<E> toList() {
    //     return Arrays.asList(this.array);
    // }
    
    public  List<E> toList() { 
        return this.getValues();
    }

    public static void main(String[] args) {

    }

}
