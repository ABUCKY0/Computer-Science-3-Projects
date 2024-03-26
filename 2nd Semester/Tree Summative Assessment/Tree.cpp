#include "Tree.h"
#include "config.h"
/**
 * Constructs a Tree
 *
 * @param data the data to return
 * @note Time Complexity: O(1)
 */
Tree::Tree(int data)
{
  this->root = new TreeNode;
  this->root->data = data;
  this->root->left = nullptr;
  this->root->right = nullptr;
  this->size = 1;
  this->root->layer = 0;
}
/**
 * No Argument Tree Constructor
 * 
 * @note Time Complexity: O(1)
 */
Tree::Tree()
{
  this->root = nullptr;
  this->size = 0;
}

/**
 * Size
 * 
 * @note Time Complexity: O(1)
 * @return this->size
 */
int Tree::getSize() { return this->size; }

/**
 * Data
 *
 * @param data the data to return
 * @param l the left node
 * @param r the right node
 * @note Time Complexity: O(1)
 * @return TreeNode
 */
TreeNode *Tree::createNode(int data, TreeNode *l, TreeNode *r)
{
  TreeNode *newNode = new TreeNode;
  newNode->data = data;
  newNode->left = l;
  newNode->right = r;
  newNode->layer = 0;
  return newNode;
}

/**
 * Add
 *
 * @param data the data to add
 * @note Time Complexity: O(n)
 */
void Tree::add(int data) {
#if ENFORCE_SET == true // Defined in Tree.h
    if (search(data)) {
        return;
    }
#endif

    if (this->size == 0) {
        this->root = createNode(data);
    } else {
        // Use a recursive function to find the appropriate parent node
        addRecursive(this->root, data);
    }

    this->size += 1;
}


/**
 * Add Recursive
 *
 * @param node the node to add to
 * @param data the data to add
 * @note Time Complexity: O(n)
 */
void Tree::addRecursive(TreeNode* node, int data) {
    if (!node->hasLeft()) {
        node->left = createNode(data);
        node->left->layer = node->layer + 1;
    } else if (!node->hasRight()) {
        node->right = createNode(data);
        node->right->layer = node->layer + 1;
    } else {
        // Recursively search for the appropriate parent node
        addRecursive(node->left, data);
    }
}

/**
 * Display Layer
 *
 * @param layer the layer to display
 * @note Time Complexity: O(n)
 * @return string
 */
string Tree::displayLayer(int layer)
{
  string s = "";

  // Add data of nodes in the layer
  for (auto node : this->children)
  {
    if (node->layer == layer)
    {
      s += to_string(node->getData());
      s += " ";
    }
  }
  return s;
}

/**
 * Search
 *
 * @param target the target to search for
 * @note Time Complexity: O(n)
 * @return bool
 */
bool Tree::search(int target) { return search(this->root, target); }

/**
 * Search
 *
 * @param node the node to search
 * @param target the target to search for
 * @note Time Complexity: O(n)
 * @return bool
 */
bool Tree::search(TreeNode *node, int target)
{
  if (node == nullptr)
  {
    return false;
  }

  if (node->getData() == target)
  {
    return true;
  }

  bool left = search(node->left, target);
  bool right = search(node->right, target);

  return left || right;
}

/**
 * Count Leaves
 *
 * @note Time Complexity: O(n)
 * @return int
 */
int Tree::countLeaves() { return countLeaves(this->root); }

/**
 * Count Leaves
 *
 * @param node the node to count leaves from
 * @note Time Complexity: O(n)
 * @return int
 */
int Tree::countLeaves(TreeNode *node)
{
  if (node == nullptr)
  {
    return 0;
  }
  if (!node->hasLeft() && !node->hasRight())
  {
    return 1;
  }

  return countLeaves(node->left) + countLeaves(node->right);
}

/**
 * Copy
 *
 * @note Time Complexity: O(n)
 * @return Tree
 */
Tree *Tree::copy() { return this; }

/**
 * Clear the tree
 *
 * @note Time Complexity: O(1)
 */
void Tree::clear()
{
  this->children.clear();
  this->root = NULL;
  this->size = 0;
}

/**
 * Print In Order
 *
 * @note Time Complexity: O(n)
 */
void Tree::printInOrder() { printInOrder(this->root); }

/**
 * Print In Order
 *
 * @param node the node to print in order
 * @note Time Complexity: O(n)
 */
void Tree::printInOrder(TreeNode *node)
{
  if (node == nullptr)
  {
    return;
  }
  printInOrder(node->left);
  cout << node->getData() << " ";
  printInOrder(node->right);
}

/*
bool Tree::hasPathSum(int sum) { return hasPathSum(this->root, sum); }
bool Tree::hasPathSum(TreeNode *node, int sum)
{
  if (node == nullptr)
  {
    return false;
  }
  if (node->getData() == sum && !node->hasLeft() && !node->hasRight())
  {
    return true;
  }
  return hasPathSum(node->left, sum - node->getData()) ||
         hasPathSum(node->right, sum - node->getData());
}
*/

int Tree::getDepthRecursive() {
	return this->getDepthRecursive(this->root);
}
int Tree::getDepthRecursive(TreeNode* node) {
	if (node->left == nullptr) {return 1;}
return this->getDepthRecursive(node->left) + 1;
}
