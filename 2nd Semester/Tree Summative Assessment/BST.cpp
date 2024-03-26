#include "BST.h"
#include "Tree.h"
#include "config.h"
#include <vector>
#include <stack>
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
 * @note Answers Question 1
 * @note Time Complexity: O(log(n)) (Worst Case O(n))
 * 
 * @param data 
 */
void BST::add(int data)
{
    add(data, this->root);
}

/**
 * @brief Recursive Adding
 * @note Answers Question 1
 * @note Time Complexity: O(log(n)) (Worst Case O(n))
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
 * Wraps the Search Function
 * 
 * @note Answers Question 1
 * @note Time Complexity: O(log(n)) (Worst Case O(n)) 
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
 * Recursively searches for a given data value by comparing wether the data value is equal to, larger than (right), or smaller than (left) the parent node
 * 
 * @note Answers Question 1
 * @note Time Complexity: O(log(n)) (Worst Case O(n)) 
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
 * Wraps the Remove method in an easy to use manner
 * 
 * @note Answers Question 1
 * @note Time Complexity: 
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
 * Removes a node from the tree recursively
 * @note Answers Question 1
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
 * @brief In Order Traversal
 * 
 * @param root 
 * @note Time Complexity: O(n)
 * @note Answers Question 5
*/
void BST::traverseInOrderIterative(TreeNode* root) {
    std::stack<TreeNode*> stack;
    TreeNode* curr = root;

    while (curr != nullptr || !stack.empty()) {
        while (curr != nullptr) {
            stack.push(curr);
            curr = curr->left;
        }

        curr = stack.top();
        stack.pop();

        std::cout << curr->data << " ";

        curr = curr->right;
    }
}

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
 * @brief Print Leaf Nodes
 * 
 * @note Time Complexity: See printLeafNodes(TreeNode* node)
 * @note Answers Question 8
 * @note This is a wrapper method that calls the recursive method
*/
void BST::printLeafNodes() {
    printLeafNodes(this->root);
}

/**
 * @brief Print Leaf Nodes
 * 
 * Recursively prints the leaf nodes of the tree
 * 
 * @param node The node to start at
 * @note Answers Question 8
 * @note Time Complexity: O(n)
*/
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


/**
 * @brief Convert to Doubly Linked List
 * 
 * @note Time Complexity: See convertToDoublyLinkedList(TreeNode* treeNode, Node** head, Node** tail)
 * @note Answers Question 10
 * @note This is a wrapper method that calls the recursive method
 * @return Node* The head of the doubly linked list
*/
Node* BST::convertToDoublyLinkedList() {
    Node* head = nullptr;
    Node* tail = nullptr;
    convertToDoublyLinkedList(this->root, &head, &tail);
    return head;
}

/**
 * @brief Convert to Doubly Linked List
 * 
 * Recursively converts the tree to a doubly linked list
 * 
 * @param treeNode The node to start at
 * @param head The head of the list
 * @param tail The tail of the list
 * @note Time Complexity: O(n)
 * @note Answers Question 10
*/
void BST::convertToDoublyLinkedList(TreeNode* treeNode, Node** head, Node** tail) {
    if (treeNode == nullptr) {
        return;
    }

    // Convert the left subtree
    convertToDoublyLinkedList(treeNode->left, head, tail);

    // Create a new node for the current tree node
    Node* newNode = new Node(treeNode->data);

    // If the list is empty, set the head to the new node
    if (*head == nullptr) {
        *head = newNode;
    } else {
        // Otherwise, append the new node to the end of the list
        (*tail)->next = newNode;
        newNode->prev = *tail;
    }

    // Update the tail to the new node
    *tail = newNode;

    // Convert the right subtree
    convertToDoublyLinkedList(treeNode->right, head, tail);
}



#if USE_WHICH_MAIN == 2
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


    /// @note Question 2
    cout << "[Q2] (PREORDER): " << endl;
    bst->traversePreOrder();
    cout << endl;

    /// @note Question 4
    cout << "[Q4] (INORDER): " << endl;
    bst->traverseInOrder();
    cout << endl;

    /// @note Question 5
    cout << "[Q5] (INORDER Iterative): " << endl;
    bst->traverseInOrderIterative(bst->root);
    cout << endl;

    /// @note Question 6
    cout << "[Q6] (POSTORDER): " << endl;
    bst->traversePostOrder();
    cout << endl;


    /// @note Question 8
    cout << "[Q8] (LEAF NODES): " << endl;
    bst->printLeafNodes();
    cout << endl;

    /// @note Question 9
    cout << "[Q9] (Binary Search on Sorted Array): This is not implemented in the BST class. It's present in the Tree Summative Assessment Google Document. \n";
    cout << "   It splits the array in half until it finds the value." << endl;

    /// @note Question 10
    cout << "[Q10] (CONVERT TO DOUBLY LINKED LIST): " << endl;
    Node* head = bst->convertToDoublyLinkedList();
    Node* current = head;
    while (current != nullptr) {
        cout << current->data << " ";
        current = current->next;
    }

    /// @note Question 11: Finding Depth of a Binary Tree 
    // make a tree
    Tree *tree = new Tree(1);
    tree->add(2);
    tree->add(3);
    tree->add(4);
    tree->add(5);
    tree->add(6);
    tree->add(7);
    tree->add(8);
    cout << "[Q11] (DEPTH): " << endl;
    cout << "Depth: " << bst->getDepthRecursive() << endl;

    /// @note Question 12: Difference Between Binary and Binary Search Trees 
    cout << "[Q12] (DIFFERENCE): This question is answered at the bottom of the Google Doc." << endl;

    /// @note GOogle Doc Link
    cout << "Google Doc Link: https://docs.google.com/document/d/1fL_gm14lreL1VObFC3duAP-crtwev7A_FHctw_bN_9o";


    


}

#endif