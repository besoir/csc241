public class ExpandedArray<T> {
	private T[] arr;
	private int count;

	public ExpandedArray(int oLength) {
		arr = (T[])new Object[oLength];
		count = 0;
	}

	public void expandable() {
		T[] newArr = (T[])new Object[arr.length * 2];
		for(int i=count-1; i >= 0; i--)
			newArr[i] = arr[i];
		arr = newArr;
	}

	public void add(T value) {
		if(count == arr.length)
			expandable();
		arr[count++] = value;
	}

	public void printArray() {
		System.out.print("Array with " + count + " nodes: ");
		for (int j=0; j < count; j++)
			System.out.print(arr[j] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		ExpandedArray<String> ea = new ExpandedArray<>(6);
		ea.add("a");
		ea.printArray();
		ea.add("b");
		ea.add("c");
		ea.add("d");
		ea.add("e");
		ea.add("f");
		ea.add("g");
		ea.add("h");
		ea.printArray();
	}
}