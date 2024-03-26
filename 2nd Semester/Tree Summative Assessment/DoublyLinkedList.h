#ifndef DOUBLYLINKEDLIST_H
#define DOUBLYLINKEDLIST_H

class Node {
public:
    int data;
    Node* prev;
    Node* next;

    Node(int data);
};

class DoublyLinkedList {
private:
    Node* head;
    Node* tail;
public:
    DoublyLinkedList();
    ~DoublyLinkedList();
    void append(int data);
    void printList();
};

#endif // DOUBLYLINKEDLIST_H