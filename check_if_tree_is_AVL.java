package weblab;
import java.util.*;

class Solution {

    /**
     * Computes whether the BinaryTree is an AVL tree.
     *
     * @param tree
     *     the BinaryTree to check.
     * @return true iff the BinaryTree is an AVL tree, else false.
     */
    public static boolean isTreeAVL(BinaryTree tree){
        if(tree == null){
            return true;
        }

        if(!tree.hasRight() && !tree.hasLeft()){
            return true;
        }

        int leftCh = heightCalculator(tree.getLeft());
        int rigthCh = heightCalculator(tree.getRight());

        boolean flagL = true;
        boolean flagR = true;

        if(tree.hasLeft()){
            flagL = isTreeAVL(tree.getLeft());
        }
        if(tree.hasRight()){
            flagR = isTreeAVL(tree.getRight());
        }

        return noDuplicates(tree) && (flagR && flagL) && Math.abs(leftCh - rigthCh) <= 1
                && correctValues(tree) && correctValuesG(tree,Integer.MIN_VALUE,Integer.MAX_VALUE);

    }


    public static int heightCalculator(BinaryTree tree){

        int h = 1;

        if(tree == null){
            return 0;
        }

        else if(!tree.hasRight() && !tree.hasLeft()){
            return 1;
        }
        else if(tree.hasRight() && !tree.hasLeft()){
            return 1 + heightCalculator(tree.getRight());
        }
        else if(!tree.hasRight() && tree.hasLeft()){
            return 1 + heightCalculator(tree.getLeft());
        }
        else{
            return 1 + Math.max(heightCalculator(tree.getRight()), heightCalculator(tree.getLeft()));
        }
    }

    public static boolean correctValues(BinaryTree tree){

        if(tree == null){
            return true;
        }

        if(tree.hasRight() && tree.hasLeft()){
            boolean flag = tree.getLeft().getKey() < tree.getKey() && tree.getRight().getKey() > tree.getKey();
            return flag && correctValues(tree.getRight()) && correctValues(tree.getLeft());
        }

        else if(!tree.hasRight() && tree.hasLeft()){
            boolean flag = tree.getLeft().getKey() < tree.getKey();
            return flag && correctValues(tree.getLeft());
        }
        else if(tree.hasRight() && !tree.hasLeft()){
            boolean flag = tree.getRight().getKey() > tree.getKey();
            return flag && correctValues(tree.getRight());
        }
        else{
            return true;
        }

    }


    public static boolean correctValuesG(BinaryTree tree,int boundL,int boundH){
        if(tree == null){
            return true;
        }
        if(tree.getKey() > boundH){
            return false;
        }
        if(tree.getKey() < boundL){
            return false;
        }

        return correctValuesG(tree.getLeft(),boundL,tree.getKey()) && correctValuesG(tree.getRight(),tree.getKey(),boundH);

    }


    public static boolean noDuplicates(BinaryTree tree){

        if(tree == null){
            return true;
        }

        List<Integer> jj = new ArrayList<>();


        ArrayDeque<BinaryTree> deque = new ArrayDeque<>();

        deque.offer(tree);

        while(!deque.isEmpty()){

            BinaryTree curr = deque.poll();
            jj.add(curr.getKey());

            if(curr.hasLeft()){
                deque.offer(curr.getLeft());
            }
            if(curr.hasRight()){
                deque.offer(curr.getRight());
            }
        }

        Set<Integer> collect = new HashSet<>(jj);

        return collect.size() == jj.size();

    }
}

class BinaryTree {

    private int key;

    private BinaryTree left, right;

    /**
     * Simple constructor.
     *
     * @param key
     *     to set as key.
     */
    public BinaryTree(int key) {
        this.key = key;
    }

    /**
     * Extended constructor.
     *
     * @param key
     *     to set as key.
     * @param left
     *     to set as left child.
     * @param right
     *     to set as right child.
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

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    /**
     * @param left
     *     to set
     */
    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    /**
     * @param right
     *     to set
     */
    public void setRight(BinaryTree right) {
        this.right = right;
    }
}
