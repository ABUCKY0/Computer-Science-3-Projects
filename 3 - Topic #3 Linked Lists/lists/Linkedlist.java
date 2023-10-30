package lists;
public abstract class Linkedlist {
    protected Node head;
    protected int size;

    public abstract void addFront(Integer data);
    public abstract void addBefore(Integer data, Integer after);
    public abstract void addAfter(Integer data, Integer before);
    public abstract void addBack(Integer data);

    public abstract Node deleteFront();
    //public abstract Node delete(Integer data);
    public abstract Node deleteBack();

    public abstract boolean contains(Integer data);
    public abstract int getSize();
    public abstract void clear();
}
