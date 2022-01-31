package weblab;

import java.util.*;

class Solution {

    /**
     * Computes whether the BinaryTree is complete.
     *
     * @param tree the BinaryTree to check.
     * @return true iff the BinaryTree is complete, else false.
     */
    public static boolean isTreeComplete(BinaryTree tree) {

        List<BinaryTree> queue = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        if(tree == null){
            return true;
        }

        queue.add(tree);

        while(!queue.isEmpty()){

            BinaryTree tr = queue.remove(0);

            if(tr != null){
                list.add(tr.getKey());
                if(tr.hasLeft()){
                    queue.add(tr.getLeft());
                }
                if(!tr.hasLeft()){
                    queue.add(null);
                }
                if(tr.hasRight()){
                    queue.add(tr.getRight());
                }
                if(!tr.hasRight()){
                    queue.add(null);
                }
            }
            else{
                list.add(null);
            }


        }

        for (int i = 0; i < list.size()-1; i++) {

            if(list.get(i) == null && list.get(i+1) != null){
                return false;
            }

        }
        return true;

    }


}


class BinaryTree {

    private int key;

    private BinaryTree left, right;

    /**
     * Simple constructor.
     *
     * @param key to set as key.
     */
    public BinaryTree(int key) {
        this.key = key;
    }

    /**
     * Extended constructor.
     *
     * @param key to set as key.
     * @param left to set as left child.
     * @param right to set as right child.
     */
    public BinaryTree(int key, BinaryTree left, BinaryTree right) {
        this.key = key;
        setLeft(left);
        setRight(right);
    }

    public int getKey() {
        return key;
    }

    /**
     * Gives the left child of this node.
     *
     * @return the left child.
     */
    public BinaryTree getLeft() {
        return left;
    }

    /**
     * Gives the right child of this node.
     *
     * @return the right child.
     */
    public BinaryTree getRight() {
        return right;
    }

    /**
     * Indicates whether this node has a left child.
     *
     * @return true if there is a left child, false otherwise
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * Indicates whether this node has a right child.
     *
     * @return true if there is a right child, false otherwise
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * Sets the left child of this node.
     *
     * @param left to set
     */
    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    /**
     * Sets the right child of this node.
     *
     * @param right to set
     */
    public void setRight(BinaryTree right) {
        this.right = right;
    }
}
