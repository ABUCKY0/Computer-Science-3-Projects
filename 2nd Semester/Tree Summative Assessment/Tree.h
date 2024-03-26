
/// ENFORCE_SET enables line that searches for a node before adding it
/// @note Used for TreeSet Submission, not for the Binary Tree Choice Board
#define ENFORCE_SET false

#ifndef CUSTREE_H
#define CUSTREE_H

#include <iostream>
#include <limits.h>
#include <cmath>
#include <list>
#include <string>
#include "TreeNode.h"
#include "config.h"

using namespace std;

class Tree
{
public:
    TreeNode *root;
    int size;
    list<TreeNode *> children;
    Tree(int data);
    Tree();
    int getSize();
    TreeNode *createNode(int data, TreeNode *l = nullptr, TreeNode *r = nullptr);
    void add(int data);
    void addRecursive(TreeNode *node, int data);
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
    bool hasPathSum(TreeNode *node, int sum);
    int getDepthRecursive();
    int getDepthRecursive(TreeNode *node);
};

#endif