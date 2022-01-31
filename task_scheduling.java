package weblab;

import java.util.*;
import java.util.Arrays;

class Solution {

    /**
     * Computes how fast the given tasks can be finished by the given number of TAs.
     * @param durations Array containing the duration for each tasks.
     * @param n Number of TAs to complete the tasks.
     * @return The shortest time in which all tasks can be completed.
     */
    public static int completeTasks(int[] durations, int n) {

        if(n == 0){
            return 0;
        }
        if(durations.length == 0){
            return 0;
        }

        if(n==1){
            int sum = 0;
            for(int i=0;i<durations.length;i++){
                sum = sum + durations[i];
            }
            return sum;
        }

        if(n>=durations.length){
            Arrays.sort(durations);
            return durations[durations.length-1];
        }

        PriorityQueue<Integer> queue = new PriorityQueue();
        
        for(int i=0;i<n;i++){
            queue.offer(durations[i]);
        }

        for(int i=n;i<durations.length;i++){
            int duration = queue.poll();
            queue.offer(duration+durations[i]);
        }

        while(queue.size() > 1){
            queue.poll();
        }
        return queue.poll();


    }
}

