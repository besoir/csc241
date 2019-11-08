public class LinkedStack<T> {
	private class Node<T> {
		T data;
		Node<T> next;

		public Node(T data) {
			this.data = data;
		}

		public Node(T data, Node<T> next) {
			this(data);
			this.next = next;
		}

		@Override
		public String toString() {
			return "" + data;
		}
	}

	private int count;
	private Node<T> head;

	public LinkedStack() {
		head = null;
		count = 0;
	}

	public LinkedStack(T data) {
		head = new Node<T>(data);
	}

	public void push(T data) {
		head = new Node<T>(data, head);
		count++;
	}

	public T pop() {
		T holder = head.data;
		head = head.next;
		count--;
		return holder;
	}

	public int length() {
		return count;
	}

	private boolean isEmpty() {
		return head == null;
	}

	@Override
	public String toString() {
		String returnable = "LinkedStack with " + count + " nodes: ";
		for(Node<T> place = head; place != null; place = place.next)
			returnable = returnable + place.data + " ";
		return returnable;
	}
}