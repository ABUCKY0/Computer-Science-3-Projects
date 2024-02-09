#include <iostream>
#include <limits.h>
#include <cmath>
#include <list>
#include <string>

using namespace std;

struct TreeNode {
    int data;
    int layer;
    TreeNode *left;
    TreeNode *right;

    int getData() {
        return this->data;
    }
    bool hasLeft() {
        return this->left != nullptr;
    }
    bool hasRight() {
        return this->right != nullptr;  
    }
    int getLayer() {
        return this->layer;
    }
    void setLayer(int newLayer) {
        this->layer = newLayer;
    }
};

class Tree {
private:
    TreeNode* root;
    int size;
    list<TreeNode*> children;

public:
    Tree(int data) {
        this->root = new TreeNode;
        this->root->data = data;
        this->root->left = nullptr;
        this->root->right = nullptr;
        this->size = 1;
        this->root->layer = 0;
        this->children.push_back(this->root);
    }
    Tree() {
        this->root = nullptr;
        this->size = 0;
    }
    ~Tree() {};

    int getSize() {
        return this->size;
    }

    TreeNode* createNode(int data, TreeNode* l = nullptr, TreeNode* r = nullptr) {
        TreeNode* newNode = new TreeNode;
        newNode->data = data;
        newNode->left = l;
        newNode->right = r;
        newNode->layer = 0;
        return newNode;
    }

    void add(int data) {
        if (this->size == 0) {
            this->root = createNode(data);
            this->children.push_back(this->root);
        } else {
            for (auto node : this->children) {
                if (!node->hasLeft()) {
                    node->left = createNode(data);
                    node->left->layer = node->layer + 1;
                    this->children.push_back(node->left);
                    break;
                } else if (!node->hasRight()) {
                    node->right = createNode(data);
                    node->right->layer = node->layer + 1;
                    this->children.push_back(node->right);
                    break;
                }
            }
        }
        this->size += 1;
    }

    string displayLayer(int layer) {
        string s = "";
    
        // Add data of nodes in the layer
        for (auto node : this->children) {
            if (node->layer == layer) {
                s += to_string(node->getData());
                s += " ";
        }
        }
        return s;
    }

    bool search(int target) {
        return search(this->root, target);
    }

    bool search(TreeNode* node, int target) {
        if (node == nullptr) {
            return false;
        }

        if (node->getData() == target) {
            return true;
        }

        bool left = search(node->left, target);
        bool right = search(node->right, target);

        return left || right;
    }

    int countLeaves() {
        return countLeaves(this->root);
    }
    int countLeaves(TreeNode* node) {
        if (node == nullptr) {
            return 0;
        }
        if (!node->hasLeft() && !node->hasRight()) {
            return 1;
        }

        return countLeaves(node->left) + countLeaves(node->right);
    }

    Tree* copy() {
        return this;
    }

    void clear() {
        this->children.clear();
        this->root = NULL;
    }
    void printInOrder() {
        printInOrder(this->root);
    }
    void printInOrder(TreeNode* node) {
        if (node == nullptr) {
            return;
        }
        printInOrder(node->left);
        cout << node->getData() << " ";
        printInOrder(node->right);
    }

    bool hasPathSum(int sum) {
        return hasPathSum(this->root, sum);
    }
    bool hasPathSum(TreeNode* node, int sum) {
        if (node == nullptr) {
            return false;
        }
        if (node->getData() == sum && !node->hasLeft() && !node->hasRight()) {
            return true;
        }
        return hasPathSum(node->left, sum - node->getData()) || hasPathSum(node->right, sum - node->getData());
    }
};

int main() {
    Tree* t = new Tree(1);
    t->add(2);
    t->add(3);
    t->add(4);
    t->add(5);
    t->add(6);
    t->add(7);
    t->add(8);
    t->add(9);
    t->add(10);
    t->add(11);
    t->add(12);
    t->add(13);
    t->add(14);
    t->add(15);

    Tree* copy = t->copy();

    t->printInOrder();

    cout << "\n" << endl;

    //pathsum
    cout << "PathSum True: "  << t->hasPathSum(15) << endl;

    // should be false
    cout << "PathSum false: " << t->hasPathSum(122) << endl;

    t->clear();
    cout << "The tree has been cleared." << endl;
    cout << "Size: " << t->getSize() << endl;

    cout << t->getSize() << endl;
    cout << copy->getSize() << endl;

    cout << t->search(9) << endl;
    for(int i=0; i<4; i++){
        cout << t->displayLayer(i) << endl;
    }
    return 0;
}