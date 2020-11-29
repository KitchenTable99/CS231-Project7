import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordCounter2Test {

	WordCounter2 BSTcounter, Hcounter;
	ArrayList<String> testWords;
	
	@BeforeEach
	void init() {
		BSTcounter = new WordCounter2("bst");
		Hcounter = new WordCounter2("hashmap");
		testWords = WordCounter2.readWords("counttest.txt");
		BSTcounter.buildMap(testWords);
		Hcounter.buildMap(testWords);
	}
	
	@Test
	void constructorTest() {
		assertTrue(BSTcounter.getMap() instanceof BSTMap, "bst map not correct class");
		assertTrue(Hcounter.getMap() instanceof Hashmap, "hashmap map not correct class");
	}
	
	@Test
	void readTest() {
		assertEquals("[was, best, times, was, worst, times, was, age, wisdom, was, age, foolishness]", testWords.toString());
	}
	
	@Test
	void buildBSTTest() {
		assertEquals("[<was, 4>, <best, 1>, <age, 2>, <times, 2>, <foolishness, 1>, <worst, 1>, <wisdom, 1>]", BSTcounter.getMap().entrySet().toString());
	}
	
	@Test
	void buildHashTest() {
		assertEquals("<best, 1>\n" + 
				"<times, 2>\n" + 
				"<was, 4>\n" + 
				"<foolishness, 1>\n" + 
				"<worst, 1>\n" + 
				"<age, 2>\t<wisdom, 1>\n", Hcounter.getMap().toString());
	}
	
	@Test
	void clearTest() {
		BSTcounter.clearMap();
		Hcounter.clearMap();
		assertTrue(BSTcounter.getMap() instanceof BSTMap, "bst map not correct class after clearing");
		assertTrue(Hcounter.getMap() instanceof Hashmap, "hashmap map not correct class after clearing");
	}
	
	@Test
	void accesssorTest() {
		// BST counter tests
		assertEquals(12, BSTcounter.totalWordCount(), "BSTMap total words incorrect");
		assertEquals(7, BSTcounter.uniqueWordCount(), "BSTMap unique words incorrect");
		assertEquals(4, BSTcounter.getCount("was"), "BSTMap count incorrect");
		assertEquals(1./3., BSTcounter.getFrequency("was"), "BSTMap frequency incorrect");
		// Hashmap tests
		assertEquals(12, Hcounter.totalWordCount(), "Hashmap total words not counting correctly");
		assertEquals(7, Hcounter.uniqueWordCount(), "Hashmap unique words not counting correctly");
		assertEquals(4, Hcounter.getCount("was"), "Hashmap count incorrect");
		assertEquals(1./3., Hcounter.getFrequency("was"), "Hashman frequency incorrect");
	}
	
	@Test
	void writeTest() {
		assertTrue(BSTcounter.writeWordCount("BSTtest.txt"));
		assertTrue(Hcounter.writeWordCount("Hashmaptest.txt"));
	}
	
	@Test
	void readWrittenTest() {
		assertTrue(BSTcounter.readWordCount("BSTtest.txt"));
		assertTrue(Hcounter.readWordCount("Hashmaptest.txt"));
	}

}