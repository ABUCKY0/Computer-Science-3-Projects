#include "TreeNode.h"


/*
struct TreeNode
{
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
    void setData(int newData){
        this->data = newData;
    }
};
*/

int TreeNode::getData() {
    return this->data;
}
void TreeNode::setData(int newData) {
    this->data = newData;
}

bool TreeNode::hasLeft() {
    return this->left != nullptr;
}
bool TreeNode::hasRight() {
    return this->right != nullptr;  
}

int TreeNode::getLayer() {
    return this->layer;
}
void TreeNode::setLayer(int layer) {
    this->layer = layer;
}