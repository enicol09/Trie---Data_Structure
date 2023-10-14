package cy.ac.ucy.cs.epl231.ID1012334.homework2;

import java.util.LinkedList;

/**
 * This class implements the TrieNode for the data structure. It uses linked
 * lists that represents the kids/ childs of it of every character.
 * 
 * @author Elia Nicolaou 1012334
 * @version 2.0
 * @see Trie_1012334
 * @since 10/11/2019
 *
 */
public class TrieNode_1012334 {

	boolean word; // is the ending word of the word?
	char letter; // the character that represents
	LinkedList<TrieNode_1012334> kid;

	// define the constructor

	/**
	 * This is the one and only constructor , that initializes the node.
	 * 
	 *
	 */
	public TrieNode_1012334(char character) {
		// creating a new list for the character

		kid = new LinkedList<TrieNode_1012334>();
		word = false; // this can be changed later
		letter = character; // in which character are we?
	}

	/**
	 * Returns if there is a kid of the node that has the given character.
	 *
	 * @since 10/11/2019
	 *
	 */
	public TrieNode_1012334 getKid(char character) {
		if (kid != null)
			for (TrieNode_1012334 out : kid)
				if (out.letter == character)
					return out;
		return null;
	}

}