import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;
public class Quicksort {
	private static int partition(int[] arr, int first, int last) {
		int pivot = arr[first];
		int up = first;
		int down = last;
		do {
			// Need to increase/decrease pointers until you reach two points where neither are on the correct
			// side of pivot
			while(arr[up] <= pivot && up != last)
				up++;
			while(arr[down] > pivot && down != first)
				down--;
			// Once we have a point we need to change the values of the pointers to correct the array
			if(up < down) {
				int larger = arr[up];
				arr[up] = arr[down];
				arr[down] = larger;
				//Increase/Decrease because both are now on the correct side
				up++;
				down--;
			} 
			System.out.println("" + up + " " + down + " " + arr[up] + " " + arr[down]);
			printArr(arr);
		} while(up < down);
		//Once the array in ordered with respect to the pivot we need to switch the pivot with arr[down]
		arr[first] = arr[down];
		arr[down] = pivot;
		printArr(arr);
		// Now we return down... the position of the pivot
		return down;
	}

	private static void quicksort(int arr[], int first, int last) {
		if(first < last) {
			// Set the pivot so that we split up the array based on the pivot
			int pivot = partition(arr, first, last);
			// Call quicksort twice because you need both sides of the pivot
			quicksort(arr, first, pivot-1);
			quicksort(arr, pivot+1, last);
		}
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
        //int[] randoms = getRandoms(1000000);
        int[] randoms = {4,3,8,64,2,5};
        long startTime = System.currentTimeMillis();
        sort(randoms);
        long endTime = System.currentTimeMillis();
        System.out.println("Total sort of random data execution time: " + (endTime - startTime));
        printArr(randoms);
        //printArr(randoms);
        /*
		//Sorted Data
        startTime = System.currentTimeMillis();
        sort(randoms);
        endTime = System.currentTimeMillis();
        System.out.println("Total sort of random data execution time: " + (endTime - startTime));
        //printArr(randoms);
        
        //Reverse Sort the Data
        //I couldn't get the Collections way to do it to work so I used a for loop like a total noob
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


// At 40,000 Randoms: Total sort of random data execution time: 7
// At 80,000 Randoms: Total sort of random data execution time: 15
// At 160,000 Randoms: Total sort of random data execution time: 21
// At 320,000 Randoms: Total sort of random data execution time: 41
// At 740,000 Randoms: Total sort of random data execution time: 939
// At 1,480,000 Randoms: Total sort of random data execution time: 1799
// At 2,960,000 Randoms: Total sort of random data execution time: 325
// At 5,920,000 Randoms: Total sort of random data execution time: 697
// At 11,840,000 Randoms: Total sort of random data execution time: 1439
// At 23,680,000 Randoms: Total sort of random data execution time: 2992

// For Sorted Data and Reverse Sorted Data I kept getting StackOverflow Exceptions everytime, but the sort worked when I 
// had less elements in the array did work, so the problem has something to do with calling the method too many times 
// recursively

// Also if you didn't notice, I borrowed the printArr, getRandoms methods from you along with how to check the time
// I used the code from previous classes