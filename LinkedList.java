public class LinkedList<T> {
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

	private Node<T> head, tail;
	private int count;
	
	public LinkedList() {
		head = tail = null;
		count = 0;
	}
	
	public LinkedList(T data) {
		head = tail = new Node<T>(data);
		count = 1;
	}
	
	public int getCount() {
		return count;
	}
	
	public void append(T data) {
		if(isEmpty())
			head = tail = new Node<T>(data);
		else
			tail = tail.next = new Node<T>(data);
		count++;
	}
	
	public void insert(T data, int index) {
		Node<T> curr = head;
		System.out.println(curr);
		for(int i = 1; i < index - 1; i++){
			curr = curr.next;
			System.out.println(curr);
		}
		Node<T> insertedNode = new Node<T>(data, curr.next);
		curr.next = insertedNode;
		System.out.println(this);
		count++;
	}
	
	//public boolean exists(T request) {
		//Node<T> curr = head;
		
	//}
	
	public boolean isEmpty() {
		return head == null;
	}
	public int length() {
		return count;
	}
	
	public boolean remove(T data) {
		Node<T> curr = head;
		Node<T> previous = null;
		if(curr != null) {
			for(int i = 0; i < count; i++) {
				if((curr.data.equals(data)) && (previous == null)) {
					head = curr.next;
					count--;
					return true;
				}
				if((curr.data.equals(data)) && (curr.next != null)) {
					previous.next = curr.next;
					count--;
					return true;
				}
				if((curr.data.equals(data)) && (curr.next == null)) {
					tail = previous;
					count--;
					return true;
				}
				if(curr.next == null)
					return false;
				previous = curr;
				curr = curr.next;
			}
		}
		return false;
	}
	
	public T get(int index) {
		if(index < 0)
			return null;
		Node<T> curr = head;
		if(curr != null) {
			for(int i = 1; i < index; i++) {
				if(curr.next == null)
					return null;
				curr = curr.next;
			}
		}
		return curr.data;
	}
	
	public void prepend(T data) {
		head = new Node<T>(data, head);
		count++;
	}
	
	public void removeAll() {
		head = tail = null;
		count = 0;
	}
	
	public String toString() {
		String retVal = "Linked List with " + count + " nodes: ";
		for(Node<T> temp = head; temp != null; temp = temp.next) {
			retVal += temp + " ";
		}
		return retVal;
	}
}

	