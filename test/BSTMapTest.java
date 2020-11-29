// Written by Caleb Bitting

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BSTMapTest {
	
	BSTMap<String, Double> tree;
	
	@BeforeEach
	void init() {
		tree = new BSTMap<String, Double>();
		tree.put("Apple", 1.8);
	}

	@Test
	void constructorTest() {
		assertEquals(tree.size(), 1, "The expected size of the tree does not match the actual size of the tree");
	}
	
	@Test
	void putTest() {
		// check updates
		tree.put("Apple", .9);
		assertEquals(.9, tree.get("Apple"), "Put does not correctly update KeyValue pairs");
	}
	
	@Test
	void clearTest() {
		tree.clear();
		assertEquals(0, tree.size(), "Your clear function does not clear the tree");
	}
	
	@Test
	void getTest() {
		assertEquals(null, tree.get("Orange"));
	}
	
	@Test
	void containsTest() {
		assertTrue(tree.containsKey("Apple"), "Your containsKey function does not return true when a key exists");
		assertFalse(tree.containsKey("Grape"), "Your containsKey function does not return false when a key does not exist");
	}
	
	@Test
	void depthTest() {
		tree.put("Grape", 4.);
		tree.put("Banana", 5.);
		assertEquals(3, tree.getMaxDepth());
	}
	
	@SuppressWarnings("serial")
	@Test
	void keySetTest() {
		// variable set up
		ArrayList<String> keys, expected;
		expected = new ArrayList<String>() {{
			add("Apple");
			add("Aardvark");
			add("Banana");
		}};
		// prep the tree
		tree.put("Banana", 1.4);
		tree.put("Aardvark", .18);
		keys = tree.keySet();
		assertEquals(expected, keys, "Your keys in the wrong order");
	}
	
	@SuppressWarnings("serial")
	@Test
	void valueTest() {
		// variable set up
		ArrayList<Double> values, expected;
		expected = new ArrayList<Double>() {{
			add(1.8);
			add(.18);
			add(1.4);
		}};
		// prep the tree
		tree.put("Banana", 1.4);
		tree.put("Aardvark", .18);
		values = tree.values();
		assertEquals(expected, values, "Your values are in the wrong order");
	}
	
	@SuppressWarnings("serial")
	@Test
	void entrySetTest() {
		// variable set up
		ArrayList<KeyValuePair<String, Double>> pairs, expected;
		expected = new ArrayList<KeyValuePair<String, Double>>() {{
			add(new KeyValuePair<String, Double>("Apple", 1.8));
			add(new KeyValuePair<String, Double>("Aardvark", .18));
			add(new KeyValuePair<String, Double>("Banana", 1.4));
		}};
		// prep the tree
		tree.put("Banana", 1.4);
		tree.put("Aardvark", .18);
		pairs = tree.entrySet();
		boolean same = true;
		for (int i = 0; i < 3; i++) {
			// check that the key value pairs are equal
			if (pairs.get(i).equals(expected.get(i)) == false) {
				same = false;
			}
		}
		assertTrue(same, "Your entry set returns in the wrong order");
	}
	
	

}
