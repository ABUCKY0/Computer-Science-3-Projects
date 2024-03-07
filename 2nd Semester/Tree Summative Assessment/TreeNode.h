#ifndef TREENODE_H
#define TREENODE_H
struct TreeNode {
    int data;
    int layer;
    TreeNode* left;
    TreeNode* right;
    int getData();
    void setData(int newData);
    bool hasLeft();
    bool hasRight();
    int getLayer();
    void setLayer(int layer);
};
#endif