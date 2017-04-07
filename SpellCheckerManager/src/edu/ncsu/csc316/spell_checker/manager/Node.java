package edu.ncsu.csc316.spell_checker.manager;

/**
 * The Node class acts as the data structure contained with left and 
 * right Nodes to maintain the DoubleList structure
 * @author Pranesh Kamalakanthan
 *
 * @param <E> the type of data stored in the Node
 */
public class Node<E> {
	/** Holds Node data */
	private E data;
	
	/** References previous node */
	private Node<E> prev;
	
	/** References next node */
	private Node<E> next;
	
	/**
	 * Creates new Node object
	 * @param d Node data
	 * @param p previous Node
	 * @param n next Node
	 */
	public Node( E d, Node<E> p, Node<E> n ) {
		data = d;
		prev = p;
		next = n;
	}
	
	/**
	 * Get the previous Node.
	 * @return previous Node
	 */
	public Node<E> getPrev() { return prev; }
	
	/**
	 * Get the next Node.
	 * @return next Node
	 */
	public Node<E> getNext() { return next; }
	
	/**
	 * Get the data
	 * @return data
	 */
	public E getData() { return data; }
	
	/**
	 * Sets the previous Node.
	 * @param p previous Node
	 */
	public void setPrev( Node<E> p ) { prev = p; }
	
	/**
	 * Sets the next Node
	 * @param n next Node
	 */
	public void setNext( Node<E> n ) { next = n; }
}