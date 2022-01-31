package weblab;

class Solution {

    /**
     * Checks whether the given BinaryTree is a Red Black Tree.
     *
     * @param tree
     *     BinaryTree to check.
     * @return True if the given tree is a Red Black Tree, false otherwise.
     */
    public static boolean isRedBlackTree(BinaryTree tree) {
        if(tree == null){
            return true;
        }

        if(tree.isRed()){
            return false;
        }

        if(!tree.hasRight() && !tree.hasLeft()){
            return true;
        }

        if(!isTreeBST(tree)){
            return false;
        }

        if(!noRedRed(tree)){
            return false;
        }

        return blackNodeCount(tree) != -1;


    }


    public static boolean noRedRed(BinaryTree tree){
        if(tree == null){
            return true;
        }
        if(!tree.hasLeft() && !tree.hasRight()){
            return true;
        }
        if(tree.isRed()){
            if(tree.hasRight()){
                if(tree.getRight().isRed()){
                    return false;
                }
            }
            if(tree.hasLeft()){
                if(tree.getLeft().isRed()){
                    return false;
                }
            }
        }
        return noRedRed(tree.getLeft()) && noRedRed(tree.getRight());

    }


    public static int blackNodeCount(BinaryTree tree){
        if(tree == null){
            return 0;
        }
        int leftHeight = blackNodeCount(tree.getLeft());
        int rightHeight = blackNodeCount(tree.getRight());

        int add = tree.isBlack() ? 1 : 0;

        if(leftHeight == -1){
            return -1;
        }
        if(rightHeight == -1){
            return -1;
        }

        if (leftHeight != rightHeight){
            return -1;
        }

        return leftHeight + add;


    }

    public static boolean isTreeBST(BinaryTree tree) {
        if(tree == null){
            return true;
        }
        if(!tree.hasRight() && !tree.hasLeft()){
            return true;
        }

        if(tree.hasLeft() && !tree.hasRight()){
            return withinLimits(Integer.MIN_VALUE, tree.getValue(), tree.getLeft());
        }
        else if(!tree.hasLeft() && tree.hasRight()){
            return withinLimits(tree.getValue(),Integer.MAX_VALUE, tree.getRight());
        }
        else{
            return withinLimits(Integer.MIN_VALUE, tree.getValue(), tree.getLeft()) && withinLimits(tree.getValue(),Integer.MAX_VALUE, tree.getRight());
        }


    }

    public static boolean withinLimits(Integer boundB,Integer boundA,BinaryTree tree){

        if(tree.getValue() >= boundA){
            return false;
        }
        if(tree.getValue() <= boundB){
            return false;
        }


        if(!tree.hasRight() && !tree.hasLeft()){
            return true;
        }

        else if(tree.hasLeft() && !tree.hasRight()){
            return withinLimits(boundB, tree.getValue(), tree.getLeft());
        }
        else if(!tree.hasLeft() && tree.hasRight()){
            return withinLimits(tree.getValue(),boundA, tree.getRight());
        }
        else{
            return withinLimits(boundB, tree.getValue(), tree.getLeft()) && withinLimits(tree.getValue(),boundA, tree.getRight());
        }
    }
}

class BinaryTree {

    private int value;

    private BinaryTree left, right;

    private boolean isRed;

    /**
     * Simple constructor.
     *
     * @param value
     *     Value for this tree set as value.
     * @param isRed
     *     True if this node is red, false otherwise.
     */
    public BinaryTree(int value, boolean isRed) {
        this.value = value;
        this.isRed = isRed;
    }

    /**
     * Extended constructor.
     *
     * @param value
     *     to set as value.
     * @param left
     *     to set as left child.
     * @param right
     *     to set as right child.
     */
    public BinaryTree(int value, boolean isRed, BinaryTree left, BinaryTree right) {
        this(value, isRed);
        setLeft(left);
        setRight(right);
    }

    /**
     * @return the value of this tree.
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value
     *     the new value of this tree.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the left child.
     */
    public BinaryTree getLeft() {
        return left;
    }

    /**
     * @return the right child.
     */
    public BinaryTree getRight() {
        return right;
    }

    /**
     * @return true if this node is red, false otherwise.
     */
    public boolean isRed() {
        return isRed;
    }

    /**
     * @return true if this node is black, false otherwise.
     */
    public boolean isBlack() {
        return !isRed;
    }

    /**
     * @return True if the tree has a left child, false otherwise.
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * @return True if the tree has a right child, false otherwise.
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * @param left
     *     Left subtree to set.
     */
    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    /**
     * @param right
     *     Right subtree to set.
     */
    public void setRight(BinaryTree right) {
        this.right = right;
    }

    /**
     * @param red
     *     True if the new color is red, false otherwise.
     */
    public void setRed(boolean red) {
        isRed = red;
    }
}
