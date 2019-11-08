import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;
public class QuicksortEdit {
	private static void quicksort(int arr[], int first, int last) {
		int pivot = arr[first];
		int up = first;
		int down = last;
		do {
			while(arr[up] <= pivot && up != last)
				up++;
			while(arr[down] > pivot && down != first)
				down--;
			if(up < down) {
				int larger = arr[up];
				arr[up] = arr[down];
				arr[down] = larger;
				up++;
				down--;
			} 
		} while(up < down);
		if(up > first) quicksort(arr, first, up);
		if(down < last) quicksort(arr, down, last);
	}

	public static void sort(int arr[]) {
		quicksort(arr, 0, arr.length-1);
	}

	public static void printArr(int[] arr){
        System.out.println(IntStream.of(arr)
                                    .boxed()
                                    .map(Object::toString)
                                    .collect(Collectors.joining(", ")));
    }
 
    public static int[] getRandoms(int count){
        return new Random().ints().limit(count).toArray();
    }

	public static void main(String[] args){
        int[] randoms = {2,3,7,3,4,5,1};//getRandoms(23680000);
        long startTime = System.currentTimeMillis();
        sort(randoms);
        long endTime = System.currentTimeMillis();
        System.out.println("Total sort of random data execution time: " + (endTime - startTime));
        printArr(randoms);
        /*
		//Sorted Data
        startTime = System.currentTimeMillis();
        sort(randoms);
        endTime = System.currentTimeMillis();
        System.out.println("Total sort of random data execution time: " + (endTime - startTime));
        //printArr(randoms);
        
        //Reverse Sort the Data
        int i = 0; int j = randoms.length-1;
        for(; i < j; i++) {
        	int holder = randoms[i];
        	randoms[i] = randoms[j];
        	randoms[j] = holder;
        	j--;
        }
        //printArr(randoms);
        
        //Reverse Sorted Data
        startTime = System.currentTimeMillis();
        sort(randoms);
        endTime = System.currentTimeMillis();
        System.out.println("Total sort of random data execution time: " + (endTime - startTime));
		*/
    }
}