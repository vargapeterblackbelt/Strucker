package hu.elte.strucker.helper;

import hu.elte.strucker.model.diagram.Statement;
import hu.elte.strucker.model.diagram.Structogram;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class TreeHelper {
    public static void insert(JTree tree, TreeNode node, TreeNode parent) {
        ((DefaultTreeModel)tree.getModel()).insertNodeInto((MutableTreeNode)node, (MutableTreeNode)parent, parent.getChildCount());
        reload(tree, parent);
    }

    public static void insertBefore(JTree tree, TreeNode node, TreeNode parent, TreeNode sibling) {
        ((DefaultTreeModel)tree.getModel()).insertNodeInto((MutableTreeNode)node, (MutableTreeNode)parent, parent.getIndex(sibling));
        reload(tree, parent);
    }

    public static void insertAfter(JTree tree, TreeNode node, TreeNode parent, TreeNode sibling) {
        ((DefaultTreeModel)tree.getModel()).insertNodeInto((MutableTreeNode) node, (MutableTreeNode) parent, parent.getIndex(sibling)+1);
        reload(tree, parent);
    }

    public static void remove(JTree tree, TreeNode node) {
        ((DefaultTreeModel)tree.getModel()).removeNodeFromParent((MutableTreeNode) node);
        reload(tree, node.getParent());
    }

    public static void reload(JTree tree, TreeNode node) {
//            tree.repaint();
        ((DefaultTreeModel)tree.getModel()).reload(node);
    }

    public static void move(JTree tree, TreeNode node, int index) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        if(parent != null) {
            ((DefaultTreeModel)tree.getModel()).removeNodeFromParent((MutableTreeNode) node);
            ((DefaultTreeModel)tree.getModel()).insertNodeInto((MutableTreeNode) node, parent, index);
            reload(tree, node.getParent());
        }
    }

    public static <T> T extract(DefaultMutableTreeNode node, Class<T> clazz) {
        if(node != null && node.getUserObject() != null && clazz.isInstance(node.getUserObject())) {
            return (T) node.getUserObject();
        }
        return null;
    }

    public static <T> boolean canExtract(TreeNode node, Class<T> clazz) {
        if(node != null && ((DefaultMutableTreeNode)node).getUserObject() != null ) {
            return clazz.isInstance(((DefaultMutableTreeNode)node).getUserObject());
        }
        return false;
    }

    public static void main(String[] args) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new Statement("alma"));
        Structogram extract = extract(node, Structogram.class);
        System.out.println(extract);
    }
}
