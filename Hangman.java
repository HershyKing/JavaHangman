import java.io.*;
import java.util.Scanner;

class Hangman {
	private String[] words;
	private Scanner in;

	public Hangman(String filename) {
		in = new Scanner(new File(filename));
		try {
			in = new Scanner(new File(filename));
		} 
		catch (FileNotFoundException fne) {
			System.err.println("Unable to open" + filename);
			System.exit(1);
		}

		

		int i = 0;
		while (in.hasNext) {

		}
	}
}