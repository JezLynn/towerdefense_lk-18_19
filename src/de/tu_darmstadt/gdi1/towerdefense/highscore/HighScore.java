package de.tu_darmstadt.gdi1.towerdefense.highscore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Michael Schlittenbauer
 * @version 1.1
 * This class writes a new highscore and reads the allready reached highscores 
 * from/to the file highscroe.txt
 * 
 */

public class HighScore {

	private String path = "src/de/tu_darmstadt/gdi1/resources/highscore/highscore.txt";
	public ArrayList<HighScoreEntry> listHighscore = new ArrayList<HighScoreEntry>();
	
	/**
	 * Save the reached score
	 * @param name the name of the Player
	 * @param points the reached Points
	 */
	public void save(String name, int points) {
		
		/*
		 *  writes the reached highscore into the highscore.txt
		 *  cipher encodes the highscore, so you can't cheat.
		 */
		
		BufferedWriter out;
		try {
			out = new BufferedWriter(new PrintWriter(new FileWriter(path, /* append */ true)));

			out.append(name + " " + points);
			out.newLine();
			out.close();
		} catch (IOException e) {
			System.out.println("Datei konnte nicht gefunden werden");
			e.printStackTrace();
		}
	}

		/**
		 * 	reads the highscore.txt and list the result in the GameWindow.
		 * 	sort with Collection.sort() the entry's
		 */	
	public void read() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				if (zeile.length() >= 1){
					int index = zeile.indexOf(" ");
					String dName = new String(zeile.substring(0, index));
					Integer dPoints = new Integer(zeile.substring(index + 1));
				HighScoreEntry entry = new HighScoreEntry(dName, dPoints);
				listHighscore.add(entry);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Datei konnte nicht gefunden werden");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Datei konnte nicht gelesen werden");
			e.printStackTrace();
		}
		Collections.sort(listHighscore);
	}
}
