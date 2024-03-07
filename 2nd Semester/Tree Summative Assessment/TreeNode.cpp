/**
 * @copyright Copyright (c) 2024
 *
 */
#include "TreeNode.h"
// Main Struct is in TreeNode.h

/**
 * @brief Getter
 *
 * Gets the Data of the Node
 * @note Time Complexity: O(1)
 *
 * @return The Data of the Node
 */
int TreeNode::getData()
{
    return this->data;
}
/**
 * @brief Data Setter
 *  Set the data of this node to a given value
 * @note Time Complexity: O(1)
 * @param newData the data to set
 */
void TreeNode::setData(int newData)
{
    this->data = newData;
}

/**
 * @brief Checks if this node has a Left Value
 *
 * @note Time Complexity: O(1)
 *
 * @return true If has Left
 * @return false If left is null
 */
bool TreeNode::hasLeft()
{
    return this->left != nullptr;
}

/**
 * @brief Checks if this node has a right Value
 * 
 * @note Time Complexity: O(1)
 *
 * @return true If has right
 * @return false If right is null
 */
bool TreeNode::hasRight()
{
    return this->right != nullptr;
}

/**
 * @brief Gets the layer this node is own
 *
 * @note Probably not accurate or updated, wouldn't recommend using
 * @note Time Complexity: O(1)
 *
 * @return int the layer this element is on
 */
int TreeNode::getLayer()
{
    return this->layer;
}

/**
 * @brief Sets the layer this node is on
 * @note Time Complexity: O(1)
 *
 * @param layer int value the layer is on
 */
void TreeNode::setLayer(int layer)
{
    this->layer = layer;
}
