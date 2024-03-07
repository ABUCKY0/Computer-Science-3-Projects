#include "BST.h"
#include "Tree.h"

/**
* @file BST.cpp
* @brief Binary Search Tree Implementation
* @details Implementation of the Binary Search Tree
* @date 2024-03-06 (YYYY-MM-DD)
* 
*
* Each Method has an @note with it's time complexity, and a brief description of what it does.
* These are contained in @note tags to follow the Doxygen format.
* Empty methods such as BST::BST() are subclased from the Tree class.
*/




/**
 * 
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

/**
 * @brief Remove Wrapper
 * 
 * @param value Value to remove
 * @return TreeNode* 
 */
TreeNode* BST::remove(int value) {
    return remove(this->root, value);
}

/**
 * @brief Remove
 * 
 * Removes a node from the tree
 * 
 * @param value Value to remove
 * @return TreeNode*
 * @note Time Complexity: O(h) where h is the height of the tree
 */
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

/**
 * @brief in Order Successor
 * 
 * Gets the In Order Successor of a given node
 * 
 * @param node
 * @return TreeNode* 
 * @note Time Complexity: O(h) where h is the height of the tree
*/
TreeNode* BST::ios(TreeNode* node) {
    if (node->left != nullptr) {
        return ios(node->left);
    }
    return node;
}

/**
 * @brief Traverses the tree in PreOrder (Root Wrapper)
 * 
 * Wraps the recursive method to start at the root for easy use
 * 
 * @note Time Complexity: See traversePreOrder(TreeNode* node)
 * 
 */
void BST::traversePreOrder() {
    traversePreOrder(this->root);
}

/**
* @brief Traverses the tree in PreOrder
*
* Recursively traverses the tree in PreOrder (Root, Left, Right)
*
* @note Time Complexity: O(n)
*/
void BST::traversePreOrder(TreeNode* node) {
    // Base Case
    if (node == nullptr) {
        return;
    }

    // Print the data
    cout << node->data << " ";

    // Recurse Left
    traversePreOrder(node->left);

    // Recurse Right
    traversePreOrder(node->right);
};

/**
 * @brief Traverses the tree in InOrder (Root Wrapper)
 * 
 * @note Time Complexity: See traverseInOrder(TreeNode* node)
*/
void BST::traverseInOrder() {
    traverseInOrder(this->root);
};

/**
 * @brief Traverses the tree in InOrder
 * 
 * Recursively traverses the tree in InOrder (Left, Root, Right)
 * 
 * @note Time Complexity: O(n)
*/
void BST::traverseInOrder(TreeNode* node) {
    if (node == nullptr) {
        return;
    }

    traverseInOrder(node->left);
    cout << node->data << " ";
    traverseInOrder(node->right);
};


/**
 * @brief Traverses the tree in PostOrder (Root Wrapper)
 * 
 * @note Time Complexity: See traversePostOrder(TreeNode* node)
*/
void BST::traversePostOrder() {
    traversePostOrder(this->root);
};

/**
 * @brief Traverses the tree in PostOrder
 * 
 * Recursively traverses the tree in PostOrder (Left, Right, Root)
 * 
 * @note Time Complexity: O(n)
*/
void BST::traversePostOrder(TreeNode* node) {
    if (node == nullptr) {
        return;
    }

    traversePostOrder(node->left);
    traversePostOrder(node->right);
    cout << node->data << " ";
}

/**
 * @brief Pretty Print
 * @public
 * Prints the tree in a "pretty" format (easier to read and understand)
 * 
 * @note Time Complexity: See prettyPrint(TreeNode* node, int level)
 * @note Generated by AI for Debugging purposes ONLY, should not be included in final grade.
 * @author Github Copilot (GPT-4) (AI)
*/
void BST::prettyPrint() {
    char* user = getenv("USER");
    if (user && std::string(user) != "codespace") {
        cout << "NOT A DEBUGGING ENVIRONMENT, AI GENERATED PRETTY PRINT WILL NOT BE EXECUTED." << endl;
        return;
    }

    cout << "PRETTY PRINT GENERATED BY AI FOR DEBUGGING PURPOSES, SHOULD NOT BE INCLUDED IN FINAL GRADE." << endl;
    // Prints the tree in a "pretty" format (easier to read and understand)
    // This is a wrapper method that calls the recursive method
    prettyPrint(this->root, 0);
}


/**
 * @brief Pretty Print
 * @private
 * Prints the tree in a "pretty" format (easier to read and understand)
 * 
 * @param node The node to start at
 * @param space The space between levels
 * @note Time Complexity: O(n)
 * @note Generated by AI for Debugging purposes ONLY, should not be included in final grade.
 * @author Github Copilot (GPT-4) (AI)
*/

void BST::prettyPrint(TreeNode* node, int space) {
    // Base Case
    if (node == nullptr) {
        return;
    }

    // Increase distance between levels
    space += 10;

    // Process right child first
    prettyPrint(node->right, space);

    // Print current node after space
    cout << endl;
    for (int i = 10; i < space; i++) {
        cout << " ";
    }
    cout << node->data << "\n";

    // Process left child
    prettyPrint(node->left, space);
}


void BST::printLeafNodes() {
    printLeafNodes(this->root);
}

void BST::printLeafNodes(TreeNode* node) {
    if (node == nullptr) {
        return;
    }
    if (node->left == nullptr && node->right == nullptr) {
        cout << node->data << " ";
    }
    printLeafNodes(node->left);
    printLeafNodes(node->right);
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
    /**
     * @subsection Testing
    */

    /// @note Question 1
    cout << "[Q1] (ADD): Adding 7, 3, 8, 25, 16, 30" << endl;
    bst->add(7);
    bst->add(3);
    bst->add(8);
    bst->add(25);
    bst->add(16);
    bst->add(30);

    cout << "[Q1] (SEARCH): Searching for 7" << endl;
    cout << "RESULT: " << bst->search(7) << endl;
    cout << "EXPECTED RESULT << 1" << endl;

    cout << "[Q1] (REMOVE): Removing 3, 7" << endl;
    bst->remove(3);
    bst->remove(7);

    cout << "[Q1] (SEARCH): Searching for 7" << endl;
    cout << "RESULT: " <<  bst->search(7) << endl;
    cout << "EXPECTED RESULT << 0" << endl;

    cout << "[Q1] (SEARCH): Searching for 3" << endl;
    cout << "RESULT: " << bst->search(3) << endl;
    cout << "EXPECTED RESULT << 0" << endl;

    cout << "[Q2] (PREORDER): " << endl;
    bst->traversePreOrder();
    cout << endl;

    cout << "[Q4] (INORDER): " << endl;
    bst->traverseInOrder();
    cout << endl;

    cout << "[Q6] (POSTORDER): " << endl;
    bst->traversePostOrder();
    cout << endl;

    cout << "[DEBUG] (PRETTY PRINT): " << endl;
    bst->prettyPrint();

    cout << "[Q8] (LEAF NODES): " << endl;
    bst->printLeafNodes();
    cout << endl;




}