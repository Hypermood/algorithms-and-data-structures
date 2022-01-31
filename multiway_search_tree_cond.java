package weblab;

import java.util.*;

class Solution {

    /**
     * Checks whether the given MultiwaySearchTree satisfies all to conditions.
     * Our reference solution does not change this function in any way.
     *
     * Condition 1: every node has at most 7 children.
     * Condition 2: every non-leaf node (except the root) has at least 4 children.
     * Condition 3: the root has at least two children if it is not a leaf node.
     * Condition 4: the tree is a multiway search tree with distinct keys.
     *
     * @param tree
     *     MultiwaySearchTree to check.
     * @return True iff the given tree satisfies all conditions.
     */
    public static boolean isSpecialTree(MultiwaySearchTree tree) {
        return satisfiesCondition1(tree)
                && satisfiesCondition2(tree)
                && satisfiesCondition3(tree)
                && satisfiesCondition4(tree);
    }

    /**
     * Refer to the exercise description what condition you should check here.
     * Note that you should NOT change the method signature (name/parameters/return type).
     */
    public static boolean satisfiesCondition1(MultiwaySearchTree tree) {
        if(tree == null){
            return true;
        }
        if(tree.getChildren().length > 7){
            return false;
        }
        MultiwaySearchTree[] children = tree.getChildren();
        boolean flag = true;

        for (MultiwaySearchTree child : children) {
            flag = flag && satisfiesCondition1(child);

        }

        return flag;
    }

    /**
     * Refer to the exercise description what condition you should check here.
     * Note that you should NOT change the method signature (name/parameters/return type).
     */
    public static boolean satisfiesCondition2(MultiwaySearchTree tree) {
        return cond2Helper(tree,tree);
    }

    /**
     * Refer to the exercise description what condition you should check here.
     * Note that you should NOT change the method signature (name/parameters/return type).
     */
    public static boolean satisfiesCondition3(MultiwaySearchTree tree) {
        if(isLeaf(tree)){
            return true;
        }
        else{
            int counter = 0;
            for (int i = 0; i < tree.getChildren().length; i++) {

                if(tree.getChildren()[i] != null){
                    counter++;
                }
            }
            return counter >= 2;
        }
    }

    /**
     * Refer to the exercise description what condition you should check here.
     * Note that you should NOT change the method signature (name/parameters/return type).
     */
    public static boolean satisfiesCondition4(MultiwaySearchTree tree) {
        if(tree == null){
            return true;
        }

        if(isLeaf(tree)){
            return true;
        }

        if(tree.keys.length != tree.getChildren().length -1){
            return false;
        }

        MultiwaySearchTree[] children = tree.getChildren();

        for (int i = 0; i < children.length; i++) {
            if(i == 0){
                for (int j = 0; j < children[i].keys.length; j++) {

                    if(children[i].keys[j] >= tree.keys[i]){
                        return false;
                    }
                    if(j>0){
                        if(children[i].keys[j] <= children[i].keys[j-1]){
                            return false;
                        }
                    }

                }
            }
            else if(i == children.length - 1){
                for (int j = 0; j < children[i].keys.length; j++) {

                    if(children[i].keys[j] <= tree.keys[tree.keys.length-1]){
                        return false;
                    }
                    if(j>0){
                        if(children[i].keys[j] <= children[i].keys[j-1]){
                            return false;
                        }
                    }

                }
            }
            else{
                for (int j = 0; j < children[i].keys.length; j++) {

                    if(children[i].keys[j] >= tree.keys[i] || children[i].keys[j] <= tree.keys[i-1]){
                        return false;
                    }
                    if(j>0){
                        if(children[i].keys[j] <= children[i].keys[j-1]){
                            return false;
                        }
                    }

                }
            }
        }

        boolean flag = true;

        for (MultiwaySearchTree child : children) {
            flag = flag && satisfiesCondition4(child);

        }

        return flag;



    }

    public static boolean cond2Helper(MultiwaySearchTree tree,MultiwaySearchTree genTree){
        if(tree == null){
            return true;
        }


        if(tree.getChildren().length < 4 && !tree.equals(genTree)){
            if(isLeaf(tree)){
                return true;
            }
            return false;
        }

        MultiwaySearchTree[] children = tree.getChildren();
        boolean flag = true;

        for (MultiwaySearchTree child : children) {
            flag = flag && cond2Helper(child,genTree);

        }

        return flag;

    }

    public static boolean isLeaf(MultiwaySearchTree tree){
        return Arrays.stream(tree.getChildren()).allMatch(Objects::isNull);
    }
}

class MultiwaySearchTree {

    int[] keys;

    MultiwaySearchTree[] children;

    public MultiwaySearchTree(int[] keys, MultiwaySearchTree[] children) {
        this.keys = keys;
        this.children = children;
    }

    public int[] getKeys() {
        return keys;
    }

    public MultiwaySearchTree[] getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "MultiwaySearchTree{" + "keys=" + Arrays.toString(keys) + '}';
    }
}
