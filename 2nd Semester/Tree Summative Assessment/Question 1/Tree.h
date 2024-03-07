#ifndef TREE_H
#define TREE_H


#include <iostream>
#include <limits.h>
#include <cmath>
#include <list>
#include <string>
#include "Nodes/TreeNode.h"


using namespace std;

class Tree {
    public:
        TreeNode *root;
        int size;
        list<TreeNode *> children;
        Tree(int data);
        Tree();
        int getSize();
        TreeNode *createNode(int data, TreeNode *l = nullptr, TreeNode *r = nullptr);
        void add(int data);
        string displayLayer(int layer);
        bool search(int target);
        bool search(TreeNode *node, int target);
        int countLeaves();
        int countLeaves(TreeNode *node);
        Tree *copy();
        void clear();
        void printInOrder();
        void printInOrder(TreeNode *node);
        bool hasPathSum(int sum);
        bool hasPathSum(TreeNode* node, int sum);
};


#endif