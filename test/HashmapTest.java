/**
 *  Tests for Hashmap.java. Made for Project07
 *
 * @author cbitting
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashmapTest {

	Hashmap<String, Double> fullMap, map;
	
	@BeforeEach
	void init() {
		fullMap = new Hashmap<String, Double>();
		map = new Hashmap<String, Double>(2);
		map.put("Apple", 1.5);
		map.put("Banana", .9);
		map.put("Cranberry", .3);
	}
	
	@Test
	void containsTest() {
		assertTrue(map.containsKey("Cranberry"), "Key that is present but stacked does not register");
		assertTrue(map.containsKey("Apple"), "Key that is present does not register");
		assertFalse(map.containsKey("Grape"), "Key that is not present registers");
	}
	
	@Test
	void getTest() {
		assertEquals(.3, map.get("Cranberry"), "Unable to find stacked key");
		assertEquals(1.5, map.get("Apple"), "Unable to find key");
		assertEquals(null, map.get("Grape"), "Key that isn't present does not return null");
		map.put("Apple", 5.);
		assertEquals(5, map.get("Apple"), "Put function does not properly update values");
	}
	
	@Test
	void keyTest() {
		assertEquals("[Apple, Cranberry, Banana]", map.keySet().toString());
	}
	
	@Test
	void valueTest() {
		assertEquals("[1.5, 0.3, 0.9]", map.values().toString());
	}
	
	@Test
	void entryTest() {
		assertEquals("[<Apple, 1.5>, <Cranberry, 0.3>, <Banana, 0.9>]", map.entrySet().toString());
	}
	
	@Test
	void accessorTests() {
		assertEquals(1, map.getCollisions());
	}
	
	@Test
	void sizeTest() {
		// test put
		assertEquals(3, map.size(), "Your put function does not correctly update the size metric");
		// test updating key pairs
		map.put("Apple", 8694.);
		assertEquals(3, map.size(), "Your put function should not change the size metric when updating a value");
		// test expansion
		map.put("Brocolli", 2.8);
		map.put("Date", 0.0);
		map.put("Eggplant", 5.);
		map.put("Fig", 3.);
		assertEquals(7, map.size(), "Your put function loses items when expanding the Object array");
		assertEquals(6, map.getContentsSize(), "Your put funciton does not expand your Object array");
		// test clear
		map.clear();
		assertEquals(0, map.size(), "You improperly clear your Object array");
	}
	
	

}
