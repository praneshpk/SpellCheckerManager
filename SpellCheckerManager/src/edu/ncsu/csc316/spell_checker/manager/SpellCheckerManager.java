package edu.ncsu.csc316.spell_checker.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellCheckerManager {
	/** Hash table where all words in dictionary are stored */
	private HashTable dict;
	
	/** List where all words to be spell-checked are stored */
	private DoubleList<String> words;
	
	
	/**
	 * Constructs a new SpellChecker with the given dictionary
	 * @param path path to the dictionary
	 */
	public SpellCheckerManager(String path) {
		dict = new HashTable();
		words = new DoubleList<>();
		try( Scanner in = new Scanner( new FileInputStream( path ), "UTF8") )
		{
			if(!in.hasNext()) {
				System.out.println("Error: File is empty!");
				throw new IllegalArgumentException();
			}
			String word;
			while( in.hasNext()) {
				word = in.next();
				dict.add(word, genHash(word));
			}

		} catch(FileNotFoundException e) {
			System.out.println("Error: File " + path + " not found");
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * This method generates a hashcode from the given string
	 * @return hash code
	 */
	public int genHash(String e) {
		int hash = 29;
		for(int i = 0; i < e.length(); i++ )
			hash = ((hash << 3) + hash) + e.charAt(i);
		return hash;
	}
	
	/**
	 * Returns a string representation of the list of misspelled
	 * words (in alphabetical order, case insensitive) in the input file.
	 * The string representation should be in the format:
	 * ArrayBasedList[a, b, c]
	 * 
	 * @param path the path to the file to be spell-checked
	 * @return a string representation of the list of misspelled words
	 */
	public String spellCheck(String path) {
		try( Scanner in = new Scanner( new FileInputStream( path ), "UTF8") )
		{
			dict.resetProbes();
			int wordcount = 0;
			int lookup = 0;
			if(!in.hasNext())
				throw new IllegalArgumentException("Error: File is empty!");
			while( in.hasNext()) {
				String w = in.next();
				wordcount++;
				for(int i = 0; i < w.length(); i++ )
					if(!Character.isAlphabetic(w.charAt(i)) && 
							!Character.isDigit(w.charAt(i)) && 
							w.charAt(i) != '\'' &&
							w.charAt(i) != '&')
						throw new IllegalArgumentException("Error: File has a word with illegal characters");
				
				String word = w;
				//System.out.println(word);
				boolean r1 = true;
				boolean r2 = true;
				boolean r3 = true;
				boolean r4 = true;
				boolean r5 = true;
				boolean r6 = true;
				boolean r7 = true;
				// Checks if whole word is in dictionary
				for(lookup++; !dict.contains(word, genHash(word));) {
					System.out.println(word);
					// General rule
					if( word.length() < 4 ) {
						r5 = false;
						if(word.length() < 3) {
							r2 = false;
							r6 = false;
						}
						if(word.length() < 2) {
							r3 = false;
							r4 = false;
							r7 = false;
							
						}
					}
					// Capitalization rule
					if(r1 && Character.isUpperCase(word.charAt(0))) {
						if(word.length() > 1)
							word = (word.charAt(0) + "").toLowerCase() + word.substring(1);
						else
							word = (word.charAt(0) + "").toLowerCase();
						r1 = false;
					}
					// Possession rule
					else if(r2 && word.substring(word.length() - 2, word.length()).equals("'s")) {
						word = word.substring(0, word.length() - 2);
						r2 = false;
					}
					// Plurality rule
					else if(r3 && word.charAt(word.length() - 1) == 's') {
						word = word.substring(0, word.length() - 1);
						lookup++;
						if(dict.contains(word, genHash(word)))
							break;
						if(word.length() > 1 && word.charAt(word.length() - 1) == 'e')
							word = word.substring(0, word.length() - 1);
						r3 = false;
					}
					// Occupation rule
					else if(r4 && word.charAt(word.length() - 1) == 'r') {
						word = word.substring(0, word.length() - 1);
						lookup++;
						if(dict.contains(word, genHash(word)))
							break;
						if(word.length() > 1 && word.charAt(word.length() - 1) == 'e')
							word = word.substring(0, word.length() - 1);
						r4 = false;
					}
					// Gerund rule
					else if(r5 && word.substring(word.length() - 3, word.length()).equals("ing")) {
						word = word.substring(0, word.length() - 3) + "e";
						lookup++;
						if(dict.contains(word, genHash(word)))
							break;
						word = word.substring(0, word.length() - 1);
						r5 = false;
					}
					// Adverb rule
					else if(r6 && word.substring(word.length() - 2, word.length()).equals("ly")) {
						word = word.substring(0, word.length() - 2);
						r6 = false;
					}
					// Past tense rule
					else if(r7 && word.charAt(word.length() - 1) == 'd') {
						word = word.substring(0, word.length() - 1);
						lookup++;
						if(dict.contains(word, genHash(word)))
							break;
						if(word.length() > 1 && word.charAt(word.length() - 1) == 'e')
							word = word.substring(0, word.length() - 1);
						r7 = false;
					}
					// Word is misspelled
					else {
						if(words.size() == 0)
							words.add(w);
						else {
							boolean add = true;
							Node<String> curr = words.getFirst();
							for( int i = 0; i < words.size(); i++) {
								if(curr.getData().compareToIgnoreCase(w) > 0 ) {
									words.add(w, curr.getPrev(), curr);
									add = false;
									break;
								}
								curr = curr.getNext();
							}
							if(add)
								words.add(w);
						}
						break;
					}
				}
			}
			System.out.println("Number of words in dictionary: " + dict.size());
			System.out.println("Number of words to be spell-checked: " + wordcount);
			System.out.println("Number of misspelled words: " + words.size());
			System.out.println("Total Probes: " + dict.probes());
			System.out.println("Total lookUps: " + lookup);
			System.out.println("Average number of probes per word: " + ((double)dict.probes() / wordcount));
			System.out.println("Average number of probes per lookUp: " + ((double)dict.probes() / lookup));
			System.out.println();
			
			//words = ArrayList.alphabetize(words);
			return words.toString();

		} catch(FileNotFoundException e) {
			System.out.println("Error: File " + path + " not found");
			throw new IllegalArgumentException();
		}
		
	}
	
}
