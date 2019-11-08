public class ExpandArray<T> {
	private T[] arr;
	private int count;

	public ExpandArray(int originalSize) {
		arr = (T[])new Object[originalSize];
		count = 0;
	}

	public void expander() {
		T[] newArray = (T[])new Object[arr.length*2];
		for(int i=0; i < count; i++)
			newArray[i] = arr[i];
		arr = newArray;
	}

	public void add(T data) {
		if(count == arr.length)
			expander();
		arr[count++] = data;
	}

	public T get(int i) {
		return arr[i];
	}

	public void printArray() {
		System.out.println("Array with " + count + " elements: ");
		for(int i=0; i < count; i++) {
			if(i == count-1)
				System.out.println(arr[i]);
			else System.out.print(arr[i] + ", ");
		}
	}

	public int getLength() {
		return arr.length;
	}
	/*
	public static void main(String[] args) {
		ExpandArray<String> ea = new ExpandArray<>(6);
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
	*/
}