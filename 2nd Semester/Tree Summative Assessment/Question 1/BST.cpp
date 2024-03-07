#include "BST.h"


/**
 * @brief Construct a new BST::BST object
 * 
 * @param data Root Node
 */
BST::BST(int data) : Tree(data)
{
    cout << "BST Constructor" << endl;
};
/**
 * @brief Construct a new BST::BST object w/o a root node
 * 
 */
BST::BST() : Tree(){};

/**
 * @brief Destroy the BST::BST object
 * 
 */
BST::~BST(){};

/**
 * @brief Add Element "Wrapper"
 * @relates add(int data, TreeNode *node)
 * 
 * @param data 
 */
void BST::add(int data)
{
    add(data, this->root);
}

/**
 * @brief Recursive Adding
 * 
 * @param data 
 * @param node 
 */
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

/**
 * @brief Search "Wrapper"
 * 
 * @param data Value to Search for
 * @return true found
 * @return false not found
 */
bool BST::search(int data)
{
    return search(data, this->root);
};

/**
 * @brief Search Recursively
 * 
 * @param data Value to search for
 * @param node Where to search from
 * @return true 
 * @return false 
 */
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


TreeNode* BST::remove(TreeNode* root, int value) {
    if (root == nullptr) {
        return root;
    }
    if (value < root->data) {
        root->left = remove(root->left, value);
    }
    else if (value > root->data) {
        root->right = remove(root->right, value);
    }
    else {
        if (root->left == nullptr) {
            TreeNode* temp = root->right;
            delete root;
            return temp;
        }
        else if (root->right == nullptr) {
            TreeNode* temp = root->left;
            delete root;
            return temp;
        }
        TreeNode* temp = ios(root->right);
        root->data = temp->data;
        root->right = remove(root->right, temp->data);
    }

    return root;
 }

TreeNode* BST::ios(TreeNode* node) {
    if (node->left != nullptr) {
        return ios(node->left);
    }
    return node;
}

// TreeNode* BST::inOrderSuccessor(TreeNode* node)
// {
//     // left of the root and all the way right.
//     if (node->left == nullptr)
//     {
//         return nullptr;
//     }
//     else if (node->left->right == nullptr)
//     {
//         return node->left;
//     }
// }

// /** 
//  * @note Not Implemented
// //
// TreeNode *BST::inOrderPredecessor(TreeNode *node)
// {
//     // right of the root and all the way left
//     return nullptr;
// }

// */


int main()
{
    BST *bst = new BST(15);
    bst->add(7);
    bst->add(3);
    bst->add(8);
    bst->add(25);
    bst->add(16);
    bst->add(30);
    
    //cout << "In Order: ";
    //bst->printInOrder();
    

    // search
    cout << "Search 5: " << bst->search(5) << endl;
    cout << "Search 15: " << bst->search(15) << endl;

    // remove
    
    bst->printInOrder();
    cout << endl;
    bst->remove(bst->root, 7);
    bst->printInOrder();

    /*
    cout << "In Order: ";
    bst->printInOrder();*/
}