/**
 * The KeyValue pair aspect of the Java version of the Python dictionary.
 *
 * @author cbitting
 */

public class KeyValuePair<K, V> {
	
	private K key;
	private V value;
	
	// constructors
	/**
	 * @param key (the key)
	 * @param value (the value)
	 */
	public KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	// accessors and mutator
	/**
	 * @param newVal what to set the new value to
	 */
	public void setValue(V newVal) {
		value = newVal;
	}
	
	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}
	
	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}
	
	// usurper methods
	public String toString() {
		String toReturn = "<";
		toReturn += key;
		toReturn += ", ";
		toReturn += value;
		toReturn += ">";
		return toReturn;
		
	}
	
	/**
	 * @param other is another KeyValue pair to compare to
	 * @return boolean representing the sameness of the two KeyValue pairs
	 */
	public boolean equals(KeyValuePair<K, V> other) {
		boolean toReturn = true;
		// check the key
		if (this.getKey().equals(other.getKey()) == false) {
			toReturn = false;
		// check the value
		} else if (this.getValue().equals(other.getValue()) == false) {
			toReturn = false;
		}
		
		return toReturn;
	}

}
