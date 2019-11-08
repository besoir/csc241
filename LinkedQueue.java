public class LinkedQueue<T> {
	private class Node<T> {
		private T data;
		private Node<T> next;

		public Node(T data) {
			this.data = data;
		}

		public Node(T data, Node<T> next) {
			this(data);
			this.next = next;
		}

		@Override
		public String toString() {
			return "" + this.data;
		}
	}
	private Node<T> head, tail;
	private int count;

	public LinkedQueue() {
		head = tail = null;
		count = 0;
	}

	public LinkedQueue(T data) {
		head = tail = new Node<T>(data);
	}
	public void enqueue(T data) {
		if(isEmpty())
			head = tail = new Node<T>(data);
		else
			tail = tail.next = new Node<T>(data);
		count++;
	}

	public T dequeue() {
		T returnable = head.data;
		head = head.next;
		count--;
		if(isEmpty())
			tail = null;
		return returnable;
	}

	public T peek() {
		return head.data;
	}
	public int length() {
		return count;
	}
	private boolean isEmpty() {
		return head == null;
	}
	@Override
	public String toString() {
		String returning = "LinkedQueue with " + count + " nodes: ";
		for(Node<T> curr = head; curr != null; curr = curr.next)
			returning = returning + curr.data + " ,";
		return returning;
	}
}