#ifndef BST_H
#include "Tree.h"
#include "Nodes/TreeNode.h"
#define BST_H
class BST : public Tree{
    public:
    /*
     * Constructor
     * @param data
     */
    BST(int data);
    /*
     * Constructor
     */
    BST();
    /*
     * Deconstructor
     */
    ~BST();

    /*
     * Inserts a new node with the given data into the tree.
     *
     * @param data The data to insert.
     */
    void add(int data);
    /*
     * Inserts a new node with the given data into the tree after the specified element. INTENDED FOR PRIVATE USE ONLY
     *
     * @param data The data to insert.
     * @param node The node to start the insertion from.
     */
    void add(int data, TreeNode *node);
    /*
     * Searches for a node with the given data in the tree.
     *
     * @param data The data to search for.
     */
    bool search(int data);
    /*
     * Searches for a node with the given data in the tree. INTENDED FOR PRIVATE USE ONLY
     *
     * @param data The data to search for.
     * @param node The node to start the search from.
     */
    bool search(int data, TreeNode *node);
    /*
     * In Order Successor Wrapper
     * @note !Not Implemented!
     * @param *node the node to look for
     */
    TreeNode* ios(TreeNode* node);
    TreeNode* inOrderSuccessor(TreeNode* node);
    TreeNode *inOrderPredecessor(TreeNode *node);

};

#endif