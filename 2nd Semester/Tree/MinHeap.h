#ifndef MINHEAP_H
#define MINHEAP_H


#include "Tree.h"
using namespace std;

class MinHeap : public Tree { 
    MinHeap(int data);
    ~MinHeap();
    TreeNode *getRightChild(TreeNode *parent);
    TreeNode *getLeftChild(TreeNode *parent);
    TreeNode *get(int index, list<TreeNode *> l);
    void add(int data);
    void trickleUp(int index);
};


#endif