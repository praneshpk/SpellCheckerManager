package edu.ncsu.csc316.spell_checker.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class maintains all the functions for running test
 * cases on SpellCheckerManger
 * @author Pranesh Kamalakanthan
 *
 */
public class SpellCheckerManagerTest {

	/**
	 * Tests the SpellCheckerManager method
	 */
	@Test
	public void testSpellCheckerManager() {
		try {
			new SpellCheckerManager("input/nonexistant.txt");
			fail("Error: Should have caught an error! Double check there is no lurking file.");
		} catch(IllegalArgumentException e) {
			// This is supposed to happen
		}
		try {
			new SpellCheckerManager("input/empty.txt");
			fail("Error: Should have caught an error!");
		} catch(IllegalArgumentException e) {
			// This is supposed to happen
		}
		try {
			new SpellCheckerManager("input/dictionary.txt");
			
		} catch(Exception e) {
			fail("Error: Test file deleted!");
		}
		
	}
	
	/**
	 * Tests the SpellCheck method
	 */
	@Test
	public void testSpellCheck() {
		SpellCheckerManager s = new SpellCheckerManager("input/dictionary.txt");
		assertEquals(s.spellCheck("input/small_sample.txt"), "ArrayBasedList[]");
		assertEquals(s.spellCheck("input/complicatedWordTextFile.txt"), "ArrayBasedList[]");
		assertEquals(s.spellCheck("input/invalidWordTextFile.txt"), "ArrayBasedList[49ers, broski, brotendo, dogger, gotta, Llamajeans, me&'s, timeyo]");
		/*try {
			System.out.println(s.spellCheck("input/illegalTextFile.txt"));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}*/
	}

}
