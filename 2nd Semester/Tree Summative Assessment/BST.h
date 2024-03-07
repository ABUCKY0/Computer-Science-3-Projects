#ifndef BST_H
#define BST_H


#include "Tree.h"
#include "TreeNode.h"
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


    void traversePreOrder();
    void traversePreOrder(TreeNode* node);

    void traverseInOrder();
    void traverseInOrder(TreeNode* node);

    void traversePostOrder();
    void traversePostOrder(TreeNode* node);


    void prettyPrint();

    protected:
    void prettyPrint(TreeNode* node, int level);
    public:

    void printLeafNodes();
    void printLeafNodes(TreeNode* node);
};

int main();

#endif