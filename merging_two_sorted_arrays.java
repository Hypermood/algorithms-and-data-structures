package weblab;

import java.util.*;


class Solution {

  /**
   * Merges two sorted arrays such that the resulting array has all elements from both arrays and is
   * also sorted. Assumes that the elements in the given arrays are sorted in non-decreasing order.
   * If there are duplicate elements in the input arrays, these should also be present in the
   * resulting array. If both arrays are null the result should be null, or a copy of the non-null
   * array if only one is null.
   *
   * <p>Efficiency requirements: merge the two arrays in a single pass.
   *
   * @param arr1 first sorted array to be merged
   * @param arr2 second sorted array to be merged
   * @return sorted array containing all elements from both arrays
   */
  public static int[] merge(int[] arr1, int[] arr2) {

    if(arr1==null && arr2==null){
        return null;
    }

    if(arr1==null && arr2!=null){
        return Arrays.stream(arr2).toArray();
    }
    if(arr1!=null && arr2==null){
        return Arrays.stream(arr1).toArray();
    }


    int[] fina = new int[arr1.length+arr2.length];

   

    int i = 0; 
    int j = 0; 
    int k = 0;
    int n1 = arr1.length;
    int n2 = arr2.length;
     
        
        while (i<n1 && j <n2)
        {
            
            if (arr1[i] < arr2[j]){
                fina[k] = arr1[i];
                k++;
                i++;
            }
            else{
                fina[k] = arr2[j];
                k++;
                j++;
            }
                
        }
    
        while (i < n1){
            fina[k] = arr1[i];
            k++;
            i++;
        }
            
        while (j < n2){
            fina[k] = arr2[j];
            k++;
            j++;
        }
            


    return fina;

  }
}

