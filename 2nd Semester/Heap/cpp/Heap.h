#ifndef HEAP_H
#define HEAP_H

class MinHeap {
    private:
        int data[];
        int capacity;
        int current_size;
    public:
        MinHeap(int value, int c);
        int getLeftChild(int index);
        int getRightChild(int index);
};

#endif