package weblab;

class Solution {

    public static void quickSort(int[] elements) {
        if(elements == null){
            return;
        }
        if(elements.length == 0){
            return;
        }

        partition(elements,0,elements.length-1);
    }

    public static void partition(int[] elements,int startIndex,int endIndex){

        if(startIndex>=endIndex){
            return;
        }

        int pivot = elements[endIndex];

        int pivotIndex = (startIndex - 1);

        for(int j = startIndex; j <= endIndex-1; j++){

            if (elements[j] < pivot){

                pivotIndex++;
                swap(elements, pivotIndex, j);
            }
        }
        swap(elements, pivotIndex + 1, endIndex);

        partition(elements,startIndex,pivotIndex);
        partition(elements,pivotIndex+2,endIndex);

    }
    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

