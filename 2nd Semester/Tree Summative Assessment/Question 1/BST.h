#ifndef BST_H
#define BST_H


#include "Tree.h"
#include "Nodes/TreeNode.h"
class BST : public Tree{
    public:
    BST(int data);
    BST();
    ~BST();
    void add(int data);
    void add(int data, TreeNode *node);
    bool search(int data);
    bool search(int data, TreeNode *node);
    TreeNode* remove(int value);
    TreeNode* remove(TreeNode* root, int value);
    TreeNode* ios(TreeNode* node);
    TreeNode* inOrderSuccessor(TreeNode* node);
    TreeNode *inOrderPredecessor(TreeNode *node);

};

int main();

#endif