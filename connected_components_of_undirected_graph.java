package weblab;

import java.util.*;

class Solution {

    public static int numberOfConnectedComponents(Graph g) {

        if(g == null){
            return 0;
        }
        if(g.getAllVertices().isEmpty()){
            return 0;
        }

        int counter = 0;
        Collection<Vertex> unexplored = g.getAllVertices();

        while(!unexplored.isEmpty()){

            GraphIterator iter = new GraphIterator(g,unexplored.iterator().next());

            while(iter.hasNext()){
                unexplored.remove(iter.next());
            }
            counter++;

        }
        return counter;



    }
}

