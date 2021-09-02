package project;

import java.util.Scanner;
import java.io.*;

/**
 * This program deals with the phone number to word conversion
 * 
 * @author ollie
 *
 */

public class COSC602_P2_PhoneNumberToWord {

	/**
	 * This is the static test method of Project2 class
	 * 
	 * @throws IOException
	 */

	public static void test() throws IOException {

		System.out.println("Please enter phone number, 7 digits in length: only numbers 2~9, no spaces or dashes please"); //ask user for the phone number

		File f = new File("../sevenLetteredWords.txt"); // The file of seven lettered words

		String wordFile = "../COSC602_P2_EnglishWordList.txt";// wordFile will hold new file's contents

		@SuppressWarnings("resource")
		Scanner scnr = new Scanner(System.in);// Scanner object to get phone number to be entered

		int input = scnr.nextInt();// read user input

		String phoneNumber = Integer.toString(input);// Convert into String phone number

		char[] word = new char[7]; // Array to hold respective letter, corresponding to respective number entered  in by the user

		BufferedReader br = null;// to read in the file

		FileWriter out = new FileWriter(f);// The output stream
		
		
		//For loop to go through th ephone number and look for anything that makes the phone number invalid, i.e., a 1,0, or not being the correct length
		for (int i = 0; i < phoneNumber.length(); ++i) {
			if (phoneNumber.charAt(i) == '0' || phoneNumber.charAt(i) == '1' || phoneNumber.length() != 7) {
				System.out.println("Not a valid phone number");
				System.out.println("Please enter phone number, 7 digits in length: only numbers 2~9, no spaces or dashes please");
				input = scnr.nextInt();// read user input

				phoneNumber = Integer.toString(input);// Convert into String phone number
				continue;//rinse and repeat this for loop until a correct number combination has been made
			}
		}

		char[][] Letters = { // two dimensional array to hold letters corresponding to phone number

				{ '0' }, // for zero
				{ '1' }, // for one
				{ 'A', 'B', 'C' }, // for 2
				{ 'D', 'E', 'F' }, // for 3
				{ 'G', 'H', 'I' }, // for 4
				{ 'J', 'K', 'L' }, // for 5
				{ 'M', 'N', 'O' }, // for 6
				{ 'P', 'Q', 'R', 'S' }, // for 7
				{ 'T', 'U', 'V' }, // for 8
				{ 'W', 'X', 'Y', 'Z' }// for 9
		};

		String[] allWordCombo = new String[17000];// to hold all possible word combinations from the 7 lettered words

		int numOfStrings = 0; // the index location of where to place into allWordCombo
		
		//This nested for loop, goes through each digit that that the USER entered, and then from there, inserts that corresponding letter into the respective place in the phone number
		// i.e., a 2, could be a A, B, or a C and would be placed in word[0]
		for (int loop1 = 0; loop1 < Letters[phoneNumber.charAt(0) - '0'].length; loop1++) { 

			word[0] = Letters[phoneNumber.charAt(0) - '0'][loop1];

			for (int loop2 = 0; loop2 < Letters[phoneNumber.charAt(1) - '0'].length; loop2++) {
				word[1] = Letters[phoneNumber.charAt(1) - '0'][loop2];

				for (int loop3 = 0; loop3 < Letters[phoneNumber.charAt(2) - '0'].length; loop3++) {
					word[2] = Letters[phoneNumber.charAt(2) - '0'][loop3];

					for (int loop4 = 0; loop4 < Letters[phoneNumber.charAt(3) - '0'].length; loop4++) {
						word[3] = Letters[phoneNumber.charAt(3) - '0'][loop4];

						for (int loop5 = 0; loop5 < Letters[phoneNumber.charAt(4) - '0'].length; loop5++) {
							word[4] = Letters[phoneNumber.charAt(4) - '0'][loop5];

							for (int loop6 = 0; loop6 < Letters[phoneNumber.charAt(5) - '0'].length; loop6++) {
								word[5] = Letters[phoneNumber.charAt(5) - '0'][loop6];

								for (int loop7 = 0; loop7 < Letters[phoneNumber.charAt(6) - '0'].length; loop7++) {
									word[6] = Letters[phoneNumber.charAt(6) - '0'][loop7];
									allWordCombo[numOfStrings] = String.valueOf(word);// This part of the code that holds all possible word combinations based off of user input

									numOfStrings++;//increment

								}

							}
						}
					}
				}
			}
		}
		
		int matches = 0; //matches will serve as a counter, to let us know how many matches we get
		
		// The below code reads in old document and creates new 7 lettered word document
		try {
			// file's input stream
			br = new BufferedReader(new FileReader(wordFile));
			String tempWord = br.readLine();

			while (tempWord != null) {

				if (tempWord.length() == 7) {
					for (int i = 0; i < allWordCombo.length; i++) { // go through length of allWord combinations and see
																	// if there are any matches

						if (tempWord.equalsIgnoreCase(allWordCombo[i])) {
							System.out.println(tempWord);
							matches++;

						}

					}
				}
				tempWord = br.readLine();// go to next word
			}
			System.out.println("Number of matches: ");
			System.out.println(matches);

		} catch (IOException e) {

			System.out.println("Error with file");
		} finally {

			br.close();
			out.close();
		}

		System.out.println("File Closed");

	}

}
