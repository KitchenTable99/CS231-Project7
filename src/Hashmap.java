/**
 *  A user-implemented HashMap class. Made for Project07
 *
 * @author cbitting
 */

import java.util.ArrayList;

public class Hashmap<K extends Comparable<K>, V> implements MapSet<K, V> {

	private class HashNode {
		KeyValuePair<K, V> data;
		HashNode next;
		
		public HashNode(K key, V value) {
			this.data = new KeyValuePair<K, V>(key, value);
			this.next = null;
		}
		
		/**
		 * @return the key
		 */
		public K getKey() {
			return data.getKey();
		}
		
		/**
		 * @return the value
		 */
		public V getValue() {
			return data.getValue();
		}
		
		/**
		 * @param value to set the data value to
		 */
		public void setValue(V value) {
			this.data.setValue(value);
		}
		
		/**
		 * @return the data KeyValuePair
		 */
		public KeyValuePair<K, V> getData() {
			return this.data;
		}
		
		public String toString() {
			String toReturn = "<";
			toReturn += this.getKey();
			toReturn += ", ";
			toReturn += this.getValue();
			toReturn += ">";
			return toReturn;
		}

	}
	
	private int collisions = 0, size = 0, filledBuckets = 0;
	private Object[] contents;
	
	public Hashmap() {
		contents = new Object[32];
	}
	
	public Hashmap(int capacity) {
		contents = new Object[capacity];
	}
	
	/**
	 * @return the total number of collisions to set up the current hashmap (changes upon expansion)
	 */
	public int getCollisions() {
		return collisions;
	}
	
	/**
	 * @return the number of items in the hashmap
	 */
	public int getContentsSize() {
		return contents.length;
	}
	
	/**
	 * @return the average bucket size across the entire hashmap
	 */
	@SuppressWarnings("unchecked")
	private double averageBucketSize() {
		double total = 0;
		for (int i = 0; i < contents.length; i++) {
			HashNode foo = (HashNode) contents[i];
			// don't do anything if null
			if (foo == null) {
				continue;
			}
			while (foo.next != null) {
				total++;
				foo = foo.next;
			}
			total++; // the while loop doesn't include the original node. this increment accounts for that
		}
		return total/contents.length;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V put(K newKey, V newValue) {
		// if table full
		if (this.averageBucketSize() >= 3) {
			// get data
			ArrayList<KeyValuePair<K, V>> nodes = this.entrySet();
			// reset vars
			filledBuckets = 0;
			collisions = 0;
			size = 0;
			contents = new Object[contents.length * 3];
			// add data to new table
			for (KeyValuePair<K, V> pair : nodes) {
				this.put(pair.getKey(), pair.getValue());
			}
		}
		
		// find index
		int index = Math.abs(newKey.hashCode()) % this.contents.length;
		// if no collision
		HashNode foo = (HashNode) contents[index];
		if (foo == null) {
			contents[index] = new HashNode(newKey, newValue);
			filledBuckets++;
			size++;
			return newValue;
		} else {
			// until you find either the end of the linked list or the same key,
			while (foo.next != null && !foo.getKey().equals(newKey)) {
				foo = foo.next;
			}
			// if updating value
			if (foo.getKey().equals(newKey)) {
				foo.setValue(newValue);
				return newValue;
			}
			// adding to list
			foo.next = new HashNode(newKey, newValue);
			collisions++;
			size++;
			return newValue;			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean containsKey(K key) {
		boolean toReturn = false;
		found:
		for (int i = 0; i < contents.length; i++) {
			HashNode foo = (HashNode) contents[i];
			// skip empty bits
			if (foo == null) {
				continue;
			}
			// if first node is the desired one
			if (foo.getKey().equals(key)) {
				toReturn = true;
				break found;
			} else {
				// iterate over linked nodes
				while (foo.next != null) {
					foo = foo.next;
					if (foo.getKey().equals(key)) {
						toReturn = true;
						break found;
					}
				}
			}
		}
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		int index = Math.abs(key.hashCode()) % contents.length;
		HashNode foo = (HashNode) contents[index];
		// if empty space
		if (foo == null) {
			return null;
		}
		// check for first node
		if (foo.getKey().equals(key)) {
			return foo.getValue();
		} else {
			while (foo.next != null) {
				foo = foo.next;
				if (foo.getKey().equals(key)) {
					return foo.getValue();
				}
			}
			System.out.println(this.containsKey(key) ? "contains key: " + key : "doesn't contain key: " + key);
			System.out.println("not present: " + key);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<K> keySet() {
		ArrayList<K> toReturn = new ArrayList<K>();
		// for each bit in the contents array
		for (int i = 0; i < contents.length; i++) {
			HashNode foo = (HashNode) contents[i];
			// skip empty bits
			if (foo == null) {
				continue;
			}
			// add node
			toReturn.add(foo.getKey());
			// add all next nodes
			while (foo.next != null) {
				foo = foo.next;
				toReturn.add(foo.getKey());
			}
		}
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<V> values() {
		ArrayList<V> toReturn = new ArrayList<V>();
		// for each bit in the contents array
		for (int i = 0; i < contents.length; i++) {
			HashNode foo = (HashNode) contents[i];
			// skip empty bits
			if (foo == null) {
				continue;
			}
			// add node
			toReturn.add(foo.getValue());
			// add all next nodes
			while (foo.next != null) {
				foo = foo.next;
				toReturn.add(foo.getValue());
			}
		}
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<KeyValuePair<K, V>> entrySet() {
		ArrayList<KeyValuePair<K, V>> toReturn = new ArrayList<KeyValuePair<K, V>>();
		// for each bit in the contents array
		for (int i = 0; i < contents.length; i++) {
			HashNode foo = (HashNode) contents[i];
			// skip empty bits
			if (foo == null) {
				continue;
			}
			// add node
			toReturn.add(foo.getData());
			// add all next nodes
			while (foo.next != null) {
				foo = foo.next;
				toReturn.add(foo.getData());
			}
		}
		return toReturn;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		contents = new Object[contents.length];
		size = 0;
		filledBuckets = 0;
		collisions = 0;
	}
	
	@SuppressWarnings("unchecked")
	public String toString() {
		String toReturn = "";
		for (int i = 0; i < contents.length; i++) {
			HashNode foo = (HashNode) contents[i];
			// skip the blanks
			if (foo == null) {
				continue;
			}
			toReturn += foo.toString();
			while (foo.next != null) {
				toReturn += "\t";
				foo = foo.next;
				toReturn += foo.toString();
			}
			toReturn += "\n";
		}
		return toReturn;
	}
	
}
