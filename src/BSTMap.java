// Written by Caleb Bitting
// Project06
// Binary Search Tree map

import java.util.ArrayList;


public class BSTMap<K extends Comparable<K>, V> implements MapSet<K, V> {
	
	// helper Node
	private class TNode {
		TNode left, right;
		KeyValuePair<K, V> data;
		
		public TNode(K k, V v) {
			data = new KeyValuePair<K, V>(k, v);
			left = null;
			right = null;
		}
		
		/**
		 * @return the data value
		 */
		public V getVal() {
			return data.getValue();
		}
		
		/**
		 * @return the key value
		 */
		public K getKey() {
			return data.getKey();
		}
		
		/**
		 * @return the data KeyValue pair
		 */
		public KeyValuePair<K, V> getData() {
			return this.data;
		}
		
		public String toString() {
			return data.getValue().toString();
		}
		
	}
	
	private TNode root;
	private int size;
	
	/**
	 * @param node the starting node
	 * @param key the key to insert
	 * @param val the value to insert
	 * @return the node to insert
	 */
	private TNode recursivePut(TNode node, K key, V val) {
		// insert at root if needed
		if (node == null) {
			return new TNode(key, val);
		}
		// call function recursively
		if (key.compareTo(node.getKey()) < 0) {
			node.left = recursivePut(node.left, key, val);
		} else if (key.compareTo(node.getKey()) > 0) {
			node.right = recursivePut(node.right, key, val);
		// node already exists in tree
		} else {
			node.getData().setValue(val);
		}
		
		return node;
	}

	@Override
	public V put(K newKey, V newValue) {
		root = recursivePut(root, newKey, newValue);
		size++;
		return newValue;
	}

	/**
	 * @param node to start it
	 * @param key the key to find
	 * @return whether or not it was found
	 */
	private boolean recursiveContains(TNode node, K key) {
		// if no value in tree, no key present
		if (node == null) {
			return false;
		}
		// else go down left branch
		if (key.compareTo(node.getKey()) < 0) {
			return this.recursiveContains(node.left, key);
		// go down right branch
		} else if (key.compareTo(node.getKey()) > 0) {
			return this.recursiveContains(node.right, key);
		// node present
		} else {
			return true;
		}
	}
	
	@Override
	public boolean containsKey(K key) {
		return this.recursiveContains(root, key);
	}

	/**
	 * @param node to start at
	 * @param list an ArrayList that will contain all the keys
	 */
	private void recursiveKey(TNode node, ArrayList<K> list) {
		// add this node
		list.add(node.getKey());
		// add the node on the left
		if (node.left != null) {
			this.recursiveKey(node.left, list);
		}
		// add node on right
		if (node.right != null) {
			this.recursiveKey(node.right, list);
		}
		return;
	}
	
	@Override
	public ArrayList<K> keySet() {
		ArrayList<K> keys = new ArrayList<K>();  
		this.recursiveKey(root, keys);
		return keys;
	}

	/**
	 * @param node to start at
	 * @param list an ArrayList that will contain all the values
	 */
	private void recursiveValues(TNode node, ArrayList<V> list) {
		// add this node
		list.add(node.getVal());
		// add the node on the left
		if (node.left != null) {
			this.recursiveValues(node.left, list);
		}
		// add node on right
		if (node.right != null) {
			this.recursiveValues(node.right, list);
		}
		return;
	}
	
	@Override
	public ArrayList<V> values() {
		ArrayList<V> list = new ArrayList<V>();
		this.recursiveValues(root, list);
		return list;
	}

	/**
	 * @param node to start at
	 * @param list an ArrayList that will contain all the KeyValue pairs
	 */
	private void recursiveEntry(TNode node, ArrayList<KeyValuePair<K, V>> list) {
		// add this node
		list.add(node.getData());
		// add the node on the left
		if (node.left != null) {
			this.recursiveEntry(node.left, list);
		}
		// add node on right
		if (node.right != null) {
			this.recursiveEntry(node.right, list);
		}
		return;
	}
	
	@Override
	public ArrayList<KeyValuePair<K, V>> entrySet() {
		ArrayList<KeyValuePair<K, V>> list = new ArrayList<KeyValuePair<K, V>>();
		this.recursiveEntry(root, list);
		return list;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * @param node to start at
	 * @param key to look for
	 * @return the value linked with the key or null if not found
	 */
	private V recursiveGet(TNode node, K key) {
		// if no value in tree, no key present
		if (node == null) {
			return null;
		}
		// else go down left branch
		if (key.compareTo(node.getKey()) < 0) {
			return this.recursiveGet(node.left, key);
		// go down right branch
		} else if (key.compareTo(node.getKey()) > 0) {
			return this.recursiveGet(node.right, key);
		// node present
		} else {
			return node.getVal();
		}
	}
	
	/**
	 * @param node to start at
	 * @return the max depth for the node
	 */
	private int recursiveDepth(TNode node) {
		
		if (node == null) {
			return 0;
		}
		
		int leftDepth = this.recursiveDepth(node.left);
		int rightDepth = this.recursiveDepth(node.right);
		
		if (leftDepth > rightDepth) {
			return (leftDepth + 1);
		} else {
			return (rightDepth + 1);
		}
	}
	
	/**
	 * @return the max depth for the tree
	 */
	public int getMaxDepth() {
		return this.recursiveDepth(root);
	}
	
	@Override
	public V get(K key) {
		return this.recursiveGet(root, key);
	}

}
