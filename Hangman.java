import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {
	private ArrayList<String> words = new ArrayList<>();
	private String word;
	private Scanner in;
	public Screen gallows;
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

	//Graphics stuff
	public void makeScreen() throws ScreenException, PlotException {
		try {
            this.gallows = new Screen(60, 50,' ');
            
        } catch(PlotException e){
            throw new ScreenException("Cannot create screen of that size.");
        }
	}

	public void drawGallow() {
        for (int x = 0; x < 40; x++){
            this.gallows.setPixel(x,49,'_');
        }
        for (int x = 0; x < 50; x++){
            this.gallows.setPixel(15,(49-x),'|');
        }
        for (int x = 0; x < 25; x++){
            this.gallows.setPixel(15+x,0,'_');
        }
        for (int x = 0; x < 5; x++){
            this.gallows.setPixel(39,0+x,'|');
        }
	}

    public void drawHead() {
    	for (int x = 0; x < 5; x++){
        	this.gallows.setPixel(37+x,5,'_'); 
    	} 
        for (int x = 0; x < 1; x++){
            this.gallows.setPixel(37,6+x,'0');
            this.gallows.setPixel(41,6+x,'0');
        }
        for (int x = 0; x < 4; x++){
            this.gallows.setPixel(35,6+x,'|');
            this.gallows.setPixel(43,6+x,'|');
        }
        for (int x = 0; x < 3; x++){
            this.gallows.setPixel(38+x,8,'_');
        }
        for (int x = 0; x < 5; x++){
            this.gallows.setPixel(37+x,9,'_');
        }
    }

    public void drawBody() {
    	for (int x = 0; x < 21; x++){
            this.gallows.setPixel(29+x,10,'_');
        }
        for (int x = 0; x < 15; x++){
            this.gallows.setPixel(29,10+x,'|');
            this.gallows.setPixel(50,10+x,'|');
        }
        for (int x = 0; x < 20; x++){
            this.gallows.setPixel(30+x,24,'_');
        }
    }

    public void drawLeftArm() {
    	for (int x = 0; x < 16; x++) {
            this.gallows.setPixel(28,10+x,'|');
            this.gallows.setPixel(23,10+x,'|');
    	}
    	for (int x = 0; x < 7; x++){
        	this.gallows.setPixel(23+x,10,'_');
    	}
    }

	public void drawRightArm() {
    	for (int x = 0; x < 16; x++) {
            this.gallows.setPixel(51,10+x,'|');
        	this.gallows.setPixel(55,10+x,'|');
    	}
    	for (int x = 0; x < 7; x++){
        	this.gallows.setPixel(49+x,10,'_');
    	}
    }        

    public void drawLeftLeg() {
    	for (int x = 0; x < 17; x++) {
        	this.gallows.setPixel(30,25+x,'|');
        	this.gallows.setPixel(39,25+x,'|');
    	}
    	for (int x = 0; x < 8; x++) {
        	this.gallows.setPixel(31+x,41,'_');
        }
    }

    public void drawRightLeg() {
    	for (int x = 0; x < 17; x++) {
        	this.gallows.setPixel(40,25+x,'|');
        	this.gallows.setPixel(49,25+x,'|');
    	}
    	for (int x = 0; x < 8; x++) {
        	this.gallows.setPixel(41+x,41,'_');
        }
    }

    public void drawing(int guess) throws ScreenException, PlotException {
    	this.makeScreen();
    	switch (guess) {
    		case 0: this.drawRightLeg();
    		case 1: this.drawLeftLeg();
    		case 2: this.drawRightArm();
    		case 3: this.drawLeftArm();
    		case 4: this.drawBody();
    		case 5: this.drawHead();
    		case 6: this.drawGallow();
    				break;
    		default: break;

    	}
    	System.out.println(this.gallows.toString());
    }

	public static void main(String args[]) throws FileNotFoundException, IOException, ScreenException, PlotException {
		Scanner kb = new Scanner(System.in);
		Scanner inp = new Scanner(System.in);
		Hangman hm = new Hangman("words.txt");
		System.out.println("Welcome to the game HANGMAN!!!");
		System.out.println("I'm thinking of a word that is " + hm.getWord().length() + " letters long.");
		int guess = 6;
		Character uInput;
		while (!hm.isWordGuessed()) {
			hm.drawing(guess);
			System.out.println("You have " + guess + " guess(es) left.");
			System.out.println("Available characters " + hm.getAvailableLetters());
			System.out.print("Please guess a letter: ");
			String s = inp.nextLine();
			uInput = new Character(s.charAt(0));
			uInput = Character.toUpperCase(uInput);
			System.out.println("--------------------");
			if (hm.lettersGuessed.contains(uInput)) {
				System.out.println("Oops you already guessed that character: " + hm.getGuessedWord());
			} else {
				hm.lettersGuessed.add(uInput);
				System.out.println(hm.lettersGuessed);

				if(hm.getWord().indexOf(uInput) > 0) {
					System.out.println("Good guess " + hm.getGuessedWord());
				} else {
					System.out.println("Oops that's not in my word: " + hm.getGuessedWord());

					if (guess > 1) 
						guess = guess - 1;
					else {
						System.out.println("Sorry you ran out of guess.");
						hm.drawing(guess);
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