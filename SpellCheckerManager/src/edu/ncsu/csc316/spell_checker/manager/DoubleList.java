package edu.ncsu.csc316.spell_checker.manager;

import java.util.*;
/**
 * The DoubleList class acts as the main data structure for maintaining Nodes in
 * a similar fashion as a DoublyLinkedList, allowing for traversal of both sides
 * @author Pranesh Kamalakanthan
 *
 * @param <E> the type of data stored in the List
 */
public class DoubleList<E> {
	
	/** Header sentinel Node */
	private Node<E> header;
	
	/** Trailer sentinel Node */
	private Node<E> trailer;
	
	/** Size of DoubleList */
	private int size = 0;
	
	/**
	 * Creates a new DoubleList object
	 */
	public DoubleList() {
		header = new Node<>( null, null, null );
		trailer = new Node<>( null, header, null );
		header.setNext(trailer);
	}
	
	/**
	 * Gets the size of the list
	 * @return size of the list
	 */
	public int size() { return size; }
	
	/**
	 * Gets the first node of the list
	 * @return first node
	 */
	public Node<E> getFirst() { return header.getNext(); }
	
	/**
	 * Creates a new node with given data and adds it to the end
	 * of the list
	 * @param d given data
	 */
	public void add( E d ) {
		add(d, trailer.getPrev(), trailer);
	}
	
	
	/**
	 * Creates a new Node with the given data and
	 * inserts it into the list with the given prev/next nodes
	 * @param d Node data
	 * @param p prev node
	 * @param n next node
	 */
	public void add( E d, Node<E> p, Node<E> n ) {
		Node<E> add = new Node<>(d, p, n);
		p.setNext(add);
		n.setPrev(add);
		size++;
	}
	
	public String toString() {
		String res = "ArrayBasedList[";
		if(size == 0)
			return res + "]";
		Node<E> curr = getFirst();
		for( int i = 0; i < size - 1; i++) {
			res += curr.getData() + ", ";
			curr = curr.getNext();
		}
		return res + curr.getData() + "]";
	}
	

	
}
