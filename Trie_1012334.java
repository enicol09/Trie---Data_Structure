package cy.ac.ucy.cs.epl231.ID1012334.homework2;

/**
 * This class implements the Trie data structure. Trie is a data structure which
 * stores data in such a way that it can be retrieved faster and improve the
 * performance. This trie implementation use the class TrieNode in order to
 * present it's "children"
 *
 * @author Elia Nicolaou 1012334
 * @version 2.0
 * @see TrieNode_1012334
 * @since 10/11/2019
 *
 */
public class Trie_1012334 {
	private TrieNode_1012334 root; // we have one root / the first list
	private long Start_Time;
	private long End_Time;

	/**
	 * This is the only constructor of the clas Trie_1012334, and it creates the
	 * root of the Trie , ( using TrieNode_1012334 )
	 * 
	 * @see TrieNode_1012334
	 * @since 10/11/2019
	 *
	 */
	public Trie_1012334() {
		root = new TrieNode_1012334(' '); // the root will have the space as symbol to no any letters
	}

	/**
	 * This function is being used for inserting a new word in the Trie .
	 * 
	 * How the algorithm of insert works , if the word already exist in the Trie
	 * then , it doesn't do anything. But if it doesn't it takes a new Trie-node and
	 * makes it as the root . Then it takes every character of the word, and gets
	 * all the trienodes ( the kids ) , if a kid node with the same character exists
	 * then it makes it into the current - index node. Else , it creates a new trie
	 * node with the current character and add it to the current(index) node kid
	 * list , and change the index into the new trie node.
	 * 
	 * @param word the word that we want to insert
	 * @see TrieNode_1012334
	 * @since 10/11/2019
	 *
	 */
	public void insert(String word) {
		if (search(word) == true) // case of having the same letter in the trie , so we are not goinf to add it
									// again
			return;
		TrieNode_1012334 index = root;

		for (int c = 0; c < word.length(); c++) {
			char character = word.charAt(c);

			TrieNode_1012334 child = index.getKid(character); // tha pernoume to paidi aftis tis lexis

			if (child != null)
				index = child;
			else {

				// If child not present, adding it to the list
				index.kid.add(new TrieNode_1012334(character));
				index = index.getKid(character);
			}
		}
		index.word = true;
	}

	/** This function is used for the choice 2 , where it finds alternatives
	 * of a given word of the same length.
	 * 
	 * @param out the word that we want to find alternatives
	 */
	public void alternatives(String out) {
		setStart_Time(System.currentTimeMillis());
		String temp = "";
		for (int i = 0; i < out.length(); i++) {
			for (char c = 'a'; c <= 'z'; c++) {
				if (i == 0) {
					temp = c + out.substring(i + 1, out.length());
					//System.out.println(temp);
				} else if (i == out.length() - 1) {
					temp = out.substring(0, out.length() - 1) + c;
					//System.out.println(temp);
				} else {
					temp = out.substring(0, i) + c + out.substring(i + 1, out.length());
					//System.out.println(temp);
				}
				if (search(temp)) {
					System.out.println(temp);
				}
			}
		}
		setEnd_Time(System.currentTimeMillis());
		System.out.println("\n Execution Time : " + collapsed() + " milliseconds");
	}

	/**
	 * @return the time in milliseconds
	 */
	public double collapsed() {
		return getEnd_Time() - getStart_Time();
	}

	/**
	 * This function is being used for search if a word existis inthe Trie .
	 * 
	 * How the algorithm of search works . For every character of the word 
	 * For each character of word , wee see if kid node exists for it.
     * If kid node doesn't exists, return false
     * Else if the character exists, repeat above step
     * If the index.word=true then it is the end and the word exists
	 * 
	 * @param word the word that we want see if exists
	 * @return true if exists , false if it is not
	 * @see TrieNode_1012334
	 * @since 10/11/2019
	 *
	 */
	public boolean search(String word) {
		//System.out.println("shhs");
		TrieNode_1012334 index = root;
		for (int c = 0; c < word.length(); c++) {
			char character = word.charAt(c);
			if (index.getKid(character) == null)
				return false;
			else
				index = index.getKid(character);
		}
		if (index.word == true)
			return true;
		return false;
	}
    
	/** This function is used for the choice 3 , where it finds alternatives
	 * of a given word of the length-1 and length+1.
	 * 
	 * @param out the word that we want to find alternatives
	 */
	public void alternatives_all(String out) {
		setStart_Time(System.currentTimeMillis());
		alternatives_smallest(out);
		alternatives_biggest(out);
		setEnd_Time(System.currentTimeMillis());
		//System.out.println("\n Execution Time : " + collapsed() + " milliseconds");
	}

	/** This function is used for the choice 3 ,and its responsible for finding alternatives of
	 * length+1 
	 * 
	 * @param out the word that we want to find alternatives
	 */
	private void alternatives_biggest(String out) {
		String temp = "";
		int cnt = 0;
		System.out.println("\n Enallaktikes megaliterou mikous = \n ");
		for (int i = 0; i <= out.length(); i++) {
			for (char c = 'a'; c <= 'z'; c++) {
				if (i == 0) {
					temp = c + out.substring(0, out.length());
				} else if( i == out.length()) {
			   	    temp = out.substring(0,out.length())+c;
			   	} else {
					temp = out.substring(0, i) + c + out.substring(i, out.length());
					//System.out.println(temp);
				}
				if (search(temp)) {
				System.out.println("   " + temp);
					cnt++;
				}
				
			}
		}
		if (cnt == 0) {
			System.out.println("\n -- Den iparxoun enallaktikes megaliterou megethous :( -- \n");
			System.out.println("\n -------------------------------------------------------- \n");
		}
	}
	
	/** This function is used for the choice 3 ,and its responsible for finding alternatives of
	 * length-1 
	 * 
	 * @param out the word that we want to find alternatives
	 */
	private void alternatives_smallest(String out) {
		String temp = "";
		int cnt = 0;

		if (out.length() == 1) {
			System.out.println("\n -- Den iparxoun enallaktikes mikroterou megethous :( -- \n");
			System.out.println("\n ------------------------------------------------------- \n");
		} else {
			for (int i = 0; i < out.length(); i++) {
				if (i == 0) {
					temp = out.substring(1, out.length());
				} else if (i == out.length() - 1) {
					temp = out.substring(0, out.length() - 1);
				} else {
					temp = out.substring(0, i) + out.substring(i + 1, out.length());
				}

				if (search(temp)) {
					System.out.println("   " + temp);
					cnt++;
				}

			}
		}

		if (cnt == 0) {
			System.out.println("\n -- Den iparxoun enallaktikes mikroterou megethous :( -- \n");
			System.out.println("\n ------------------------------------------------------- \n");
		}
	}
	
	/** @return the Start_time
	 */
	public long getStart_Time() {
		return Start_Time;
	}

	/** sets the Start_time
	 */
	public void setStart_Time(long start_Time) {
		Start_Time = start_Time;
	}

	/** @return the End_time
	 */
	public long getEnd_Time() {
		return End_Time;
	}
	
	/** sets the End_time
	 */
	public void setEnd_Time(long end_Time) {
		End_Time = end_Time;
	}
}
