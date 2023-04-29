import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS group 2
 * 
 * @version 03/2023
 * @param <K> Key
 * @param <V> Value
 */
public class SkipList<K extends Comparable<? super K>, V> implements Iterable<KVPair<K, V>> {
	// First element of the top level
	private SkipNode head;
	// number of entries in the Skip List
	private int size;

	/**
	 * Initializes the fields head, size and level
	 */
	public SkipList() {
		head = new SkipNode(null, 0);
		size = 0;
	}

	/**
	 * Returns a random level number which is used as the depth of the SkipNode
	 * 
	 * @return a random level number
	 */
	int randomLevel() {
		int lev;
		Random value = new Random();
		for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
			// Do nothing
		}
		// returns a random level
		return lev;
	}

	/**
	 * Searches for the KVPair using the key which is a Comparable object.
	 * 
	 * @param key key to be searched for
	 * @return returns a list of KVPairs with the same key and null if there aren't
	 *         KVPairs with the same key exist
	 */
	public ArrayList<KVPair<K, V>> search(K key) {
		// start node (head)
		SkipNode curr = head;
		for (int i = head.level; i >= 0; i--) {
			while (curr.forward[i] != null && curr.forward[i].element().getKey().compareTo(key) < 0) {
				// go forward
				curr = curr.forward[i];
			}
		}
		// Move to actual record, if it exists
		curr = curr.forward[0];
		if (curr != null && curr.element().getKey().compareTo(key) == 0) {
			// Array to store nodes that have been found
			ArrayList<KVPair<K, V>> nodes = new ArrayList<KVPair<K, V>>();
			while (curr != null && curr.element().getKey().compareTo(key) == 0) {
				nodes.add(curr.element());
				curr = curr.forward[0];
			}
			return nodes;
		}
		// The key isn't exist
		return null;
	}

	/**
	 * @return the size of the SkipList
	 */
	public int size() {
		return size;
	}

	/**
	 * Inserts the KVPair in the SkipList at its appropriate spot as designated by
	 * its lexicographical order.
	 * 
	 * @param it the KVPair to be inserted
	 */
	@SuppressWarnings("unchecked")
	public void insert(KVPair<K, V> it) {
		int newLevel = randomLevel();
		// Increasing the head levels if the new level is higher
		if (newLevel > head.level) {
			adjustHead(newLevel);
		}
		// An array to store the end node for every level before the required position
		SkipNode[] temp;
		temp = (SkipNode[]) Array.newInstance(SkipList.SkipNode.class, head.level + 1);
		// Searching for the position to insert the New Node
		SkipNode curr = head;
		for (int i = head.level; i >= 0; i--) {
			while (curr.forward[i] != null && curr.forward[i].element().compareTo(it) < 0) {
				curr = curr.forward[i];
			}
			temp[i] = curr;
		}
		SkipNode newNode = new SkipNode(it, newLevel);
		// Updating the forwards arrays of the New Node and for the nodes pointing to
		// the New Node
		for (int i = 0; i <= newLevel; i++) {
			newNode.forward[i] = temp[i].forward[i];
			temp[i].forward[i] = newNode;
		}
		// increasing the size of the Skip List
		size++;
	}

	/**
	 * Increases the number of levels in head so that no element has more indices
	 * than the head.
	 * 
	 * @param newLevel the number of levels to be added to head
	 */
	private void adjustHead(int newLevel) {
		SkipNode temp = head;
		int prevLevel = head.level;
		head = new SkipNode(null, newLevel);
		// restoring the previous forward array to the new head
		for (int i = 0; i <= prevLevel; i++) {
			head.forward[i] = temp.forward[i];
		}
	}

	/**
	 * Removes any KVPair with the specified key
	 * 
	 * @param key of the KVPair to be removed
	 * @return returns the removed pair if the pair was valid and null if not
	 */
	@SuppressWarnings("unchecked")
	public KVPair<K, V> remove(K key) {
		// Array to store nodes
		SkipNode[] rmv = (SkipNode[]) Array.newInstance(SkipList.SkipNode.class, head.level + 1);
		// Dummy header node
		SkipNode temp = head;
		// loop to iterate on nodes in levels
		for (int s1 = head.level; s1 >= 0; s1--) {
			// to iterate on nodes that is smaller than the required
			while (temp.forward[s1] != null && temp.forward[s1].element().getKey().compareTo(key) < 0) {
				// continuing to search
				temp = temp.forward[s1];
			}
			// store nodes
			rmv[s1] = temp;
		}
		// Move to actual record, if it exists
		temp = temp.forward[0];
		// if this is the required node
		if (temp != null && temp.element().getKey().compareTo(key) == 0) {
			// to iterate on levels that may have the same node
			for (int i = temp.level; i >= 0; i--) {
				// to link with the next node
				rmv[i].forward[i] = temp.forward[i];
			}
			// removing the node
			size--;
			return temp.element();
		}
		// if the node is bigger than required or it is null
		return null;
	}

	/**
	 * Removes any KVPair with the specified value.
	 * 
	 * @param val the value of the KVPair to be removed
	 * @return returns the removed pair if the removal was successful and null if
	 *         not
	 */
	@SuppressWarnings("unchecked")
	public KVPair<K, V> removeByValue(V val) {
		// Points to the current node (head)
		SkipNode crt = head;
		// The previous nodes of the node to be removed
		SkipNode[] prvNodes = (SkipNode[]) Array.newInstance(SkipList.SkipNode.class, head.level + 1);
		while (crt.forward[0] != null && crt.forward[0].element().getValue().equals(val) != true) {
			// Go to the next node
			crt = crt.forward[0];
		}
		// Move to actual record, if it exists
		crt = crt.forward[0];
		// Check if this is the required node
		if (crt != null) {
			// To iterate on the list
			SkipNode iterNode = head;
			for (int i = crt.level; i >= 0; i--) {
				while (iterNode.forward[i] != null && iterNode.forward[i].equals(crt) != true) {
					// Go to the next node
					iterNode = iterNode.forward[i];
				}
				prvNodes[i] = iterNode;
			}
			for (int j = crt.level; j >= 0; j--) {
				prvNodes[j].forward[j] = crt.forward[j];
			}
			size--;
			return crt.element();
		}
		return null;
	}

	/**
	 * Prints out the SkipList in a human readable format to the console.
	 */
	public void dump() {
		System.out.println("SkipList dump:");
		SkipNode x = head;
		while (x != null) {
			System.out.print("Node has depth " + Integer.toString(x.level + 1) + ", Value ");
			if (x.element() == null)
				System.out.println("(null)");
			else
				System.out.println(x.element().toString());
			x = x.forward[0];
		}
		System.out.println("SkipList size is: " + Integer.toString(size));
	}

	/**
	 * This class implements a SkipNode for the SkipList data structure.
	 * 
	 * @author CS Staff
	 * 
	 * @version 2016-01-30
	 */
	private class SkipNode {
		// the KVPair to hold
		private KVPair<K, V> pair;
		// what is this
		private SkipNode[] forward;
		// the number of levels
		private int level;

		/**
		 * Initializes the fields with the required KVPair and the number of levels from
		 * the random level method in the SkipList.
		 * 
		 * @param tempPair the KVPair to be inserted
		 * @param level    the number of levels that the SkipNode should have
		 */
		@SuppressWarnings("unchecked")
		public SkipNode(KVPair<K, V> tempPair, int level) {
			pair = tempPair;
			forward = (SkipNode[]) Array.newInstance(SkipList.SkipNode.class, level + 1);
			this.level = level;
		}

		/**
		 * Returns the KVPair stored in the SkipList.
		 * 
		 * @return the KVPair
		 */
		public KVPair<K, V> element() {
			return pair;
		}

	}

	private class SkipListIterator implements Iterator<KVPair<K, V>> {
		private SkipNode current;

		public SkipListIterator() {
			current = head;
		}

		@Override
		public boolean hasNext() {
			if (current.forward[0] != null) {
				return true;
			}
			return false;
		}

		@Override
		public KVPair<K, V> next() {
			if (current.forward[0] != null) {
				current = current.forward[0];
				return current.element();
			}
			return null;
		}

	}

	@Override
	public Iterator<KVPair<K, V>> iterator() {
		return new SkipListIterator();
	}

}
