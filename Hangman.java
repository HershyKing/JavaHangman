import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Hangman {
	private ArrayList<String> words = new ArrayList<>();
	private String word;
	private Scanner in;
	public ArrayList<Character> lettersGuessed = new ArrayList<>();

	//Constructor
	public Hangman(String filename) throws FileNotFoundException {
		//Read from the text file
		in = new Scanner(new File(filename));
		try {
			in = new Scanner(new File(filename));
		} 
		catch (FileNotFoundException fne) {
			System.err.println("Unable to open" + filename);
			System.exit(1);
		}

		while (in.hasNext()) {
			String line = in.nextLine();
			this.words.add(line);
		}

		//Assign the private variable word to the secret word
		this.word = this.pickWord();
	}

	//Return the list of words
	public ArrayList getWords() {
		return this.words;
	}

	//Return the word
	public String getWord() {
		return this.word;
	}

	//Pick a random word from the list and store in the variable 
	private String pickWord() {
		Random random = new Random();
		int index = random.nextInt(this.getWords().size());
		String item = (String)this.getWords().get(index);
		return item;
	}

	//Test if the word is guessed
	public boolean isWordGuessed() {
		//Iterate through the string word
		for(int i = 0; i < this.word.length(); i++) {
			Character c = new Character(this.word.charAt(i));
			if (!this.lettersGuessed.contains(c)) {
				return false;
			}
		}
		return true;
	}

	//Get guess word
	//For example, return '__a__v_'
	public String getGuessedWord() {
		String retval = new String("");
		for(int i = 0; i < this.word.length(); i++) {
			Character c = new Character(this.word.charAt(i));
			if (this.lettersGuessed.contains(c)) {
				retval = retval + c;
			} else {
				retval = retval + "_ ";
			}
		}
		return retval;
	}

	//print out all available letters
	public String getAvailableLetters() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
		String retval = "";

		for(int i = 0; i < alphabet.length(); i++) {
			Character c = new Character(alphabet.charAt(i));
			if (!this.lettersGuessed.contains(c)) {
				retval = retval + c;
			}
		}

		return retval;
	}

	public static void main(String args[]) throws FileNotFoundException, IOException {
		Scanner kb = new Scanner(System.in);
		Scanner inp = new Scanner(System.in);
		Hangman hm = new Hangman("words.txt");
		// System.out.println(hm.getWords());
		// System.out.println(hm.getWord());
		// System.out.println(hm.getGuessedWord());
		// System.out.println(hm.getAvailableLetters());
		System.out.println("Welcome to the game HANGMAN!!!");
		System.out.println("I'm thinking of a word that is " + hm.getWord().length() + " letters long.");
		int guess = 7;
		Character uInput;
		while (!hm.isWordGuessed()) {
			System.out.println("You have " + guess + " guess(es) left.");
			System.out.println("Available characters " + hm.getAvailableLetters());
			System.out.print("Please guess a letter: ");
			String s = inp.nextLine();
			uInput = new Character(s.charAt(0));
			uInput = Character.toUpperCase(uInput);
			if (hm.getWords().contains(uInput)) {
				System.out.println("Oops you already guessed that character: " + hm.getGuessedWord());
			} else {
				hm.lettersGuessed.add(uInput);

				if(hm.getWord().indexOf((char)uInput) > 0) {
					System.out.println("Good guess " + hm.getGuessedWord());
				} else {
					System.out.println("Oops that's not in my word: " + hm.getGuessedWord());

					if (guess > 1) 
						guess = guess - 1;
					else {
						System.out.println("Sorry you ran out of guess.");
						System.out.println("The word is " + hm.getWord());
						break;
					} 
				}
			}

			if (hm.isWordGuessed()) 
				System.out.println("Congrats! You won");
		}
	}
}