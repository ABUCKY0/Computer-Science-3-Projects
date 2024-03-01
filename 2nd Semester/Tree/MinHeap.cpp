#include "MinHeap.h"

MinHeap::MinHeap(int data) : Tree(data) {}

MinHeap::~MinHeap() {}

TreeNode * MinHeap::getRightChild(TreeNode *parent) {
  if (parent->hasRight())
    return parent->right;
  return nullptr;
}

TreeNode* MinHeap::getLeftChild(TreeNode *parent) {
  if (parent->hasLeft())
    return parent->left;
  return nullptr;
}

// TreeNode* add(TreeNode* node, int data) {
//     if (node == nullptr)
//         return Tree::createNode(data);
//     if (data <= node->data)
//         node->left = this->add(node->left,data);
//     if (data > node-> data)
//         node->right = this->add(node->right, data);
//     return node;
// }
TreeNode * MinHeap::get(int index, list<TreeNode *> l) {
  list<TreeNode *>::iterator(it) = l.begin();
  for (int i = 0; i <= index; i++) {
    ++it;
  }
  return *it;
}
void MinHeap::add(int data) {
  if (this->root == nullptr) {
    this->root = this->createNode(data);
    this->root->setLayer(0);
    this->children.push_back(this->root);
  } else {
    TreeNode *newNode = this->createNode(data);
    TreeNode *parent = this->get((this->size - 1) / 2, this->children);
    if (!(parent->hasLeft())) {
      parent->left = newNode;
    } else {
      parent->right = newNode;
    }
    newNode->setLayer(parent->getLayer() + 1);
    this->children.push_back(newNode);
    this->trickleUp(this->size);
  }
  this->size += 1;
}

void MinHeap::trickleUp(int index) {
  if (index == 0) {
    return; // Base case: reached the root
  }

  int parentIndex = (index - 1) / 2;
  TreeNode *current = this->get(index, this->children);
  TreeNode *parent = this->get(parentIndex, this->children);

  if (current->getData() > parent->getData()) {
    // Swap current node with its parent
    int temp = current->getData();
    current->setData(parent->getData());
    parent->setData(temp);
    this->trickleUp(parentIndex);
  }
}
