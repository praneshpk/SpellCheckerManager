package edu.ncsu.csc316.spell_checker.manager;
/**
 * HashTable acts as the main data structure for holding th list of words
 * from a dictionary
 * 
 * @author Pranesh Kamalakanthan
 */
public class HashTable {
	/** The table for holding HashNodes */
	private HashNode[] table;
	/** The size of the table */
	private int size;
	/** Default capacity */
	private static int CAP = 25147 * 2;
	/** Number of probes */
	private int probes; 
	
	/**
	 * The nested HashNode class allows for chaining
	 * in the case of identical hash keys
	 * @author Pranesh Kamalakanthan
	 *
	 */
	protected static class HashNode {
		/** Data contained in node */
		private String data;
		/** Reference to the next node */
		private HashNode next;
		
		/**
		 * Creates a new HashNode object
		 * @param d node data
		 */
		public HashNode(String e) {
			data = e;
			next = null;
		}
		
		/**
		 * Gets the node data
		 */
		public String data() { return data; }
		
		/**
		 * Sets the next node to n
		 * @param n given node
		 */
		public void setNext(HashNode n) { next = n; }
		
		/**
		 * Gets the next node
		 * @return next node
		 */
		public HashNode next() { return next; }
	}
	/** 
	 * Creates a new HashTable object with default size
	 */
	public HashTable() { this(CAP); }
	
	/** 
	 * Creates a new HashTable object of given size
	 * @param s size of table
	 */
	public HashTable(int cap) {
		size = 0;
		probes = 0;
		CAP = cap;
		table = new HashNode[cap];
	}
	
	/**
	 * Adds the given element to the table
	 * @param e given word
	 * @param code hash code of string
	 */
	public void add(String e, int code) {
		int i = compress(code);
		if(table[i] == null)
			table[i] = new HashNode(e);
		else {
			HashNode curr = table[i];
			while(curr.next() != null)
				curr = curr.next();
			curr.setNext(new HashNode(e));
		}
		size++;
	}
	
	/**
	 * Checks if the given word is in the HashTable
	 * @param e given word
	 * @param code hashcode of word
	 * @return true if it contains the word, false otherwise
	 */
	public boolean contains(String e, int code) {
		int i = compress(code);
		HashNode curr = table[i];
		while(curr != null) {
			probes++;
			if(curr.data().equals(e))
				return true;
			curr = curr.next();
		}
		return false;
	}
	
	/**
	 * Returns the size of the table (number of elements)
	 * @return size of table
	 */
	public int size() { return size; }
	
	/**
	 * Returns the number of probes made to the table
	 * @return number of probes
	 */
	public int probes() { return probes; }
	
	/**
	 * Resets the number of probes to zero
	 */
	public void resetProbes() { probes = 0; }
	
	/**
	 * This method compresses the hashcode to an index on the table
	 * @param hash given hashcode
	 * @return index
	 */
	public int compress(int hash) {
		hash = (3 * hash + 11) % CAP;
		if(hash < 0)
			hash *= -1;
		return hash;
	}
}
