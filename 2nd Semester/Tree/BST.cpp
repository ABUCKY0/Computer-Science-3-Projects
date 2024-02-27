#include "BST.h"


BST::BST(int data) : Tree(data)
{
    cout << "BST Constructor" << endl;
};
BST::BST() : Tree(){};
BST::~BST(){};


void BST::add(int data)
{
    add(data, this->root);
}


void BST::add(int data, TreeNode *node)
{
    if (data < node->data)
    {
        if (node->left == nullptr)
        {
            node->left = createNode(data);
        }
        else
        {
            add(data, node->left);
        }
    }
    else
    {
        if (node->right == nullptr)
        {
            node->right = createNode(data);
        }
        else
        {
            add(data, node->right);
        }
    }
}


bool BST::search(int data)
{
    return search(data, this->root);
}

bool BST::search(int data, TreeNode *node)
{
    if (node == nullptr)
    {
        return false;
    }
    if (node->data == data)
    {
        return true;
    }
    if (data < node->data)
    {
        return search(data, node->left);
    }
    else
    {
        return search(data, node->right);
    }
}

TreeNode* BST::ios(TreeNode* node) {
    if (node->right == nullptr) {
        return node;
    }
    return ios(node->right);
}

TreeNode* BST::inOrderSuccessor(TreeNode* node)
{
    // left of the root and all the way right.
    if (node->left == nullptr)
    {
        return nullptr;
    }
    else if (node->left->right == nullptr)
    {
        return node->left;
    }
}

TreeNode *BST::inOrderPredecessor(TreeNode *node)
{
    // right of the root and all the way left
    return nullptr;
}

// int main()
// {
//     BST *bst = new BST(15);
//     bst->add(7);
//     bst->add(3);
//     bst->add(8);
//     bst->add(25);
//     bst->add(16);
//     bst->add(30);

//     cout << "In Order: ";
//     bst->printInOrder();
    

//     // search
//     cout << "Search 5: " << bst->search(5) << endl;
//     cout << "Search 15: " << bst->search(15) << endl;

//     /*
//     cout << "In Order: ";
//     bst->printInOrder();*/
// }