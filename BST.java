import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;
 
public class BST<T extends Comparable<? super T>> {//implements Iterable<T> {
    /*
    public class BSTIterator<U extends T> implements Iterator<U> {
        BSTNode<U> curr;
        Stack<BSTNode<U>> stack;
        public BSTIterator(BSTNode<U> current){
            stack = new Stack<BSTNode<U>>();
            this.curr = current;
            stack.push(curr);
        }

        public boolean hasNext(){
            if(stack.empty() == true) return true;
            else return false; 
        }
 
        public U next(){
            BSTNode<U> returnable = stack.pop();
            if(returnable == null) return null;
            else {
                if(returnable.right != null)
                    stack.push(returnable.right);
                if(returnable.left != null)
                    stack.push(returnable.left);
            }
            return returnable.value;
        }
    }
    */
    private class BSTNode<T extends Comparable<? super T>> {
        T value;
        BSTNode<T> left, right;
 
        public BSTNode(T value){
            this.value = value;
        }
 
        // Adapted from Todd Davies answer to printing a BST on Stack Overflow.
        // https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
        private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
            if(right!=null) {
                right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(value).append("\n");
            if(left!=null) {
                left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
            }
            return sb;
        }
 
        @Override
        public String toString() {
            return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
            //return "" + this.value;
        }
        public String toValue() {
            return "" + this.value;
        }
    }
 
    private BSTNode<T> root;
    private int count;
    
    public BST() {
        root = null;
        count = 0;
    }

    public BST(T data) {
        root = new BSTNode<T>(data);
        count = 1;
    }
    private BSTNode<T> insert(BSTNode<T> curr, T value){
        if (curr == null)
            return new BSTNode<T>(value);
        //if (value > curr.value)     
        if(value.compareTo(curr.value) > 0)
            curr.right = insert(curr.right, value);
        else if(value.compareTo(curr.value) < 0)   //if (value < curr.value)    
            curr.left = insert(curr.left, value); 
        count++;
        return curr;
    }
 
    public void insert(T value){
        root = insert(root, value);
    }
 
    private T search(BSTNode<T> curr, T value){
        //System.out.println("Visiting: " + (curr == null ? "null :(" : curr.value));
        if (curr == null) return null;
        //if (curr.value == value) return true;   
        if(curr.value.compareTo(value) == 0) return curr.value;
        //if (value > curr.value)     
        if(value.compareTo(curr.value) > 0)
            return search(curr.right, value);
        return search(curr.left, value);
    }
 
    public T search(T value){
        return search(root, value);
    }
 
    private void printInOrder(BSTNode<T> curr){
        if (curr != null) {
            // Print everything in left subtree
            printInOrder(curr.left);
            // Print curr.value
            if(curr.value != null) System.out.println(curr.value);
            // Print everything in right subtree
            printInOrder(curr.right);
        }
    }
 
    public void printInOrder(){
        printInOrder(root);
    }
 
    public void printTree(){
        System.out.println(root.toString());
    }
    
    private BSTNode<T> remove(BSTNode<T> curr, T value) {
        //System.out.println(curr.value.compareTo(value) + " " + curr.toValue());
        if(curr == null)
            return null;
        else if(curr.value.compareTo(value) < 0)
            curr.right = remove(curr.right, value);
        else if(curr.value.compareTo(value) > 0)
            curr.left = remove(curr.left, value);
        else {
            BSTNode<T> replacement;
            if(curr.right == null && curr.left == null)
                return null;
            else if(curr.right == null && curr.left != null)
                return curr.left;
            else if(curr.left == null && curr.right != null)
                return curr.right;
            else {
                replacement = successor(curr.right);
                curr.value = replacement.value;
                curr.right = remove(curr.right, curr.value);
            }
        }
        return curr;
    }

    public void remove(T value) {
        root = remove(root, value);
    }

    public BSTNode<T> successor(BSTNode<T> curr) {
        if(curr.left != null)
            return successor(curr.left);
        return curr;
    }

    public int getCount() {
        return count;
    }
    /*
    public BSTIterator<T> iterator() {
        return new BSTIterator<T>(root);
    }
    
        //Testing:
    public static void main(String[] args){
        BST bst = new BST();
        bst.insert(8);
        bst.insert(3);
        bst.insert(1);
        bst.insert(6);
        bst.insert(4);
        bst.insert(7);
        bst.insert(10);
        bst.insert(14);
        bst.insert(13);
        bst.printTree();
        bst.remove(13);
        bst.remove(8);
        bst.remove(3);
        bst.printTree();
        //System.out.println("9? " + bst.search(9));
        //System.out.println("7? " + bst.search(7));
        bst.printInOrder();
    }
    */
}