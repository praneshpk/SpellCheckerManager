package edu.ncsu.csc316.spell_checker.manager;

import static org.junit.Assert.*;

import org.junit.Test;

public class SpellCheckerManagerTest {

	@Test
	public void testSpellCheckerManager() {
		try {
			SpellCheckerManager s = new SpellCheckerManager("input/nonexistant.txt");
			fail("Error: Should have caught an error! Double check there is no lurking file.");
		} catch(IllegalArgumentException e) {
			// This is supposed to happen
		}
		try {
			SpellCheckerManager s = new SpellCheckerManager("input/empty.txt");
			fail("Error: Should have caught an error!");
		} catch(IllegalArgumentException e) {
			// This is supposed to happen
		}
		try {
			SpellCheckerManager s = new SpellCheckerManager("input/dictionary.txt");
			
		} catch(Exception e) {
			fail("Error: Test file deleted!");
		}
		
	}
	
	@Test
	public void testSpellCheck() {
		SpellCheckerManager s = new SpellCheckerManager("input/dictionary.txt");
		System.out.println(s.spellCheck("input/small_sample.txt"));
		System.out.println(s.spellCheck("input/complicatedWordTextFile.txt"));
		System.out.println(s.spellCheck("input/invalidWordTextFile.txt"));
		try {
			System.out.println(s.spellCheck("input/illegalTextFile.txt"));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
