package weblab;

class Solution {

    public static void stableSort(String[][] table, int column) {

        for (int i = 1; i < table.length; i++) {

            for (int j = i; j > 0; j--) {
                if(table[j][column].compareTo(table[j-1][column]) < 0){
                    swap(table,j,j-1);
                }
                else{
                    break;
                }
            }


        }

    }

    private static void swap(String[][] table, int i, int j) {
        String[] tt = table[i];
        table[i] = table[j];
        table[j] = tt;
    }
}

