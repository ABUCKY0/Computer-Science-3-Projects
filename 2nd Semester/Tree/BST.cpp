#include "Tree.cpp"

class BST : public Tree
{
public:
    BST(int data) : Tree(data){};
    BST() : Tree(){};
    ~BST(){};

    /*
     * Inserts a new node with the given data into the tree.
     */
    void insert(int data)
    {
        TreeNode *newNode = new TreeNode;
        newNode->data = data;
        newNode->left = nullptr;
        newNode->right = nullptr;
        newNode->layer = 0;

        TreeNode *current = this->root;
        TreeNode *parent = nullptr;

        while (current != nullptr)
        {
            parent = current;
            if (data < current->data)
            {
                current = current->left;
            }
            else
            {
                current = current->right;
            }
        }

        if (parent == nullptr)
        {
            this->root = newNode;
        }
        else if (data < parent->data)
        {
            parent->left = newNode;
        }
        else
        {
            parent->right = newNode;
        }
    }

    bool search(int data)
    {
        return search(data, this->root);
    }
    bool search(int data, TreeNode *node)
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
};

int main()
{
    BST *bst = new BST(10);
    bst->insert(5);
    bst->insert(15);
    bst->insert(3);
    bst->insert(7);
    bst->insert(12);
    bst->insert(17);
    bst->insert(1);
    bst->insert(4);
    bst->insert(6);
    bst->insert(8);
    bst->insert(11);
    bst->insert(13);
    bst->insert(16);
    bst->insert(18);
    bst->insert(2);
    bst->insert(9);
    bst->insert(14);

    cout << "In Order: ";
    bst->printInOrder();
}