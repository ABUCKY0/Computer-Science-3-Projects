#include "DoublyLinkedList.h"
#include <iostream>

/**
 * Constructs a Node
 * 
 * @param data the data to return
 * @note Time Complexity: O(1)
*/
Node::Node(int data) {
    this->data = data;
    this->prev = nullptr;
    this->next = nullptr;
}

/**
 * Constructs a DoublyLinkedList
 * 
 * @note Time Complexity: O(1)
*/
DoublyLinkedList::DoublyLinkedList() {
    this->head = nullptr;
    this->tail = nullptr;
}

/**
 * Deconstructs a DoublyLinkedList
 * 
 * @note Time Complexity: O(n)
*/
DoublyLinkedList::~DoublyLinkedList() {
    Node* current = this->head;
    while (current != nullptr) {
        Node* nextNode = current->next;
        delete current;
        current = nextNode;
    }
}

/**
 * Appends a Node to the end of the list
 * 
 * @param data the data to append
 * @note Time Complexity: O(1)
*/
void DoublyLinkedList::append(int data) {
    Node* newNode = new Node(data);
    if (this->head == nullptr) {
        this->head = newNode;
        this->tail = newNode;
    } else {
        this->tail->next = newNode;
        newNode->prev = this->tail;
        this->tail = newNode;
    }
}

/**
 * Prints the list
 * 
 * @note Time Complexity: O(n)
*/
void DoublyLinkedList::printList() {
    Node* current = this->head;
    while (current != nullptr) {
        std::cout << current->data << " ";
        current = current->next;
    }
    std::cout << std::endl;
}