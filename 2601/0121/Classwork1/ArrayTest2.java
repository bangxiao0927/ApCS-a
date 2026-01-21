public class ArrayTest2 {
    public void printArray(int[] array) {
        for (int each: array) {
            System.out.print(each + " ");
        }
        System.out.println();
    }
    
    //+ search(int, int[]) : int - Takes in a search number and an array.  Return the location of the first occurrence of that number in the array.  Return -1 if not found.
    public int search(int number, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (number == array[i]) {
                return i;
            }
        }
        return -1;
    }

    //+ scramble(int[]) : void - Takes in a number array and scramble it
    public void scramble(int[] array) {
        for(int i=0; i < array.length; i++ ){
			int j = (int)(Math.random()*array.length);

            int temp1 = array[i];
			int temp2 = array[j];
        	array[i] = temp2;
            array[j] = temp1;
        }
    }

    public void sort(int[] arr) {
        for (int i=0; i < arr.length-1; i++){
            int minIndex = i;
            
            for (int j=i+1; j < arr.length; j++){
                if (arr[j] <  arr[minIndex]){
                    minIndex = j;
                }
            }

            if( minIndex != i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}