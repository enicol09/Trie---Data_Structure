package cy.ac.ucy.cs.epl231.ID1012334.homework2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the entering point of the program . Here the user decides what
 * he/she wants to do , so it has the interaction with the user. Based on the
 * user choices , it calls functions for satisfying user's choice.
 *
 * @author Elia Nicolaou 1012334
 * @version 2.0
 * @since 10/11/2019
 *
 */
public class Hw2_main_1012334 {
	private static Scanner scan;
	private static Scanner input;
	private static int in;
	static ArrayList<String> words;
	private static ArrayList<String>  used_words;
	
	/**
	 * This function is being used for checking if the given file, from the command line is valid or not
	 * if it is not then the program stops.
	 *
	 * @param filename the name of the given input
	 */
	private static void Check(String filename) {
		File file_name = new File(filename);
		try {
			input = new Scanner(file_name);

		} catch (FileNotFoundException ex) {

			System.out.println("\n EROOR: File not Found! . \n" + " Please run the program with different file name \n");
			System.exit(0);
		}
	}

	/** Main !
	 *  it is the interactive part of the program with the user.
	 * @param args the inputs from the command line
	 */
	public static void main(String[] args) {
		
		//long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		scan = new Scanner(System.in);
		
		//put the words of the given file in an arraylist
		if (args.length > 0) {
			String filename = args[0];
			Check(filename);

			Trie_1012334 dictionary = new Trie_1012334();

			while (input.hasNext()) {

				String word = input.next();
				word = word.toLowerCase();
				String temp = "";

				for (int i = 0; i < word.length(); i++) {
					boolean letter = false;
					for (char c = 'a'; c <= 'z'; c++) {
						if (word.charAt(i) == c) {
							letter = true;
						}
					}

					if (letter) {
						temp += word.charAt(i);
					}
					
					if (i != word.length() - 1) {
						if (!letter ) {
							temp = " ";
							break;

						}
					}
				}
       
				if(temp!=" ") {
				word = temp;
				dictionary.insert(word); }

			}

			boolean stop = false;

			//shows the menu , until the end choice
			while (!stop) {
				System.out.println("\n---- Iparxoun 4 epiloges: ----\n");
				System.out.print(" 1  = Elegxos Kimenou gia Lathoi "
						+ "\n 2  = Elegxos kimenou kai protasi gia enallaktiki lisi idiou "
						+ "\n 3  = Elegxos Kimenou gia lathoi kai protasi gia enalaktiki lisi diaforetikou megethous"
						+ "\n 4  = Exodos" + "\n \n---Eisage ena apo tous pio panw arithmous---"
						+ "\n Dwse epilogi =  ");

				boolean stop2 = true;
				while (stop2) {
					try {
						String str = scan.next();
						in = Integer.parseInt(str);
						boolean stop3 = false;
						while (in != 1 && in != 2 && in != 3 && in != 4) {
							stop3 = true;
							System.out.print(
									" Autos o arithmos den einai epilogi !\n Dose xana ena apo tous pio panw :) : ");
							break;
						}
						if (!stop3) {
							stop2 = false;
						}

					} catch (Exception e) {
						System.out.print("I eisodos prepei na einai arithmos. Dose xana :) : ");
					}
				}

				System.out.println("\n -->Epilogi = " + in);

				
				//choice 4 = end
				if (in == 4) {
					System.out.println("\n -- Telos programmatos! Euxaristoume :) --");
					stop = true;
				}

				//choice 1 = legxos Kimenou gia Lathoi
				if (in == 1) {
					get_file_words();

					dictionary.setStart_Time(System.currentTimeMillis());
					System.out.println("\n --> Mi egkires lexeis : \n");
					for (String out : words) {
						if (!dictionary.search(out)) {
							System.out.println("   "+ out);
							// System.out.println(out);
						}
					}
					//dictionary.setEnd_Time(System.currentTimeMillis());
					//System.out.println("\n Execution Time : " + dictionary.collapsed() + " milliseconds");
				}

				//choice 2 = Elegxos kimenou kai protasi gia enallaktiki lisi idiou
				else if (in == 2) {
					get_file_words();
					for (String out : words) {
						if (!dictionary.search(out)) {
							System.out.println("\n ------------------------------------------------------- ");
							System.out.println("\n -- > Mi egkiri lexi = " + out);
							System.out.println("\n Enallaktikes lexeis tou idiou mikous : \n");							
							dictionary.alternatives(out);
							//System.out.println("\n ------------------------------------------------------- ");
						}
						
					}
					System.out.println("\n ------------------------------------------------------- \n");
				}

				//choice 3 = Elegxos Kimenou gia lathoi kai protasi gia enalaktiki lisi diaforetikou megethous
				else if (in == 3) {
					get_file_words();

					for (String out : words) {
						if (!dictionary.search(out)) {
							System.out.println("\n ------------------------------------------------------- ");
							System.out.println(" \n --> Mi egkiri lexi = " + out );
							System.out.println("\n Enallaktikes lexeis mikroterou mikous :  \n");
							dictionary.alternatives_all(out);
						}
					}
				}

				//long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				//long actualMemUsed = afterUsedMem - beforeUsedMem;

				//System.out.print("Memory usage = " + actualMemUsed);
			}

		}

	}

	/**
	 * This function is being used for getting the words from the user's given file.
	 * 
	 */
	private static void get_file_words() {
		System.out.print("\n -- Dwse to onoma tou arxeiou pou thelis na elexoume -- = ");
		boolean stop2;
		stop2 = true;
		String filename;
		boolean Notexist = false;

		while (stop2) {
			filename = scan.next();
			File file_name = new File(filename);
			try {
				input = new Scanner(file_name);
				stop2 = false;

			} catch (FileNotFoundException ex) {

				System.out.println(" EROOR: File not Found! . \n" + " Ksanadwse onoma arxeiou : ");
			}
		}
        used_words = new ArrayList<String>();
		words = new ArrayList<String>();
      
		while (input.hasNext()) {

			String word = input.next();

			word = word.toLowerCase();
			String temp = "";
			//System.out.println("Added` t9o word = " + word);
			for (int i = 0; i < word.length(); i++) {
				boolean letter = false;
				for (char c = 'a'; c <= 'z'; c++) {
					if (word.charAt(i) == c) {
						letter = true;
					}
				}
				if (letter) {
					temp += word.charAt(i);
				}

				if (i != word.length() - 1) {
					if (!letter && (word.charAt(i+1)>='a'&& word.charAt(i+1)<='z')) {
						temp = " ";
						break;

					}
				}
			}

			if (temp != " ") {
				word = temp;
				
				if(used_words.isEmpty()) {
					used_words.add(word);	
				words.add(word);}
				else {
					Notexist=false;
					for (String out : used_words) {
						if(out.equals(word)) {
							Notexist = true;
						}
					}
						if(!Notexist) {
							used_words.add(word);
							words.add(word);
						}
					}
					
				}
		
			    //System.out.println("Added word = " + word);
			}
	

	}

}
