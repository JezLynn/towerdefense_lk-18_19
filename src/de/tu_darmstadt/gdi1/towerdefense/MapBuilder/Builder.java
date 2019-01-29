package de.tu_darmstadt.gdi1.towerdefense.MapBuilder;

import java.io.File;
import java.io.FileWriter;

import de.tu_darmstadt.gdi1.towerdefense.exceptions.SyntaxNotCorrectException;

public class Builder {


	private int height = fieldHeight();
	private int width = fieldWidth();
	private int length = wayLength((((height-2)*(width-2))/2),(int) (((height-2)*(width-2))/1.5));

	private char[][] newMap = new char[height][width];

	private int startX = setStartX(height - 2);
	private int startY = setStartY(width - 2);

	public Builder(){
		
	}

	/**
	 * generates a number for the width (cols) of the field
	 * @usage :fieldWidth(2, 25) --> creats a number between 2 and 25
	 */
	private int fieldHeight(){
	    return (int) (Math.random() * (21 - 7) + 7);
	}
	
	/**
	 * generates a number for the heigth (rows) of the field
	 * @usage :fieldWidth(2, 10) --> creates number between 2 and 10
	 */
	private int fieldWidth(){
	    return (int) (Math.random() * (26 - 7) + 7);
	}

	/**
	 * set the value of the width and height Points of the releasepoint
	 */
	private int setStartX(int max){
	    return (int) (Math.random() * (max - 1) + 1);
	}		
	private int setStartY(int max){
	    return (int) (Math.random() * (max - 1) + 1);
	}	

	/**
	 * the length of the way
	 */
	private int wayLength(int min, int max){
	    return (int) (Math.random() * (max - min) + min);    	
	}	
	
	/**
	 * Fills the rest of the empty field after the lead to finish
	 */
	private void fillRest(){
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				if (newMap[i][j] == '\u0000'){
					newMap[i][j] = '_';
				}
			}
		}
	}
	
	/**
	 * creates the border of the gamepanel
	 */
	private void makeBorder(){
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				if (i == 0 || j == 0 || j == width -1 || i == height -1){
					newMap[i][j] = '#';
				}
			}
		}
	}
		
	/**
	 * generates a number for the char
	 * @usage :charSet(1, 4) --> creates number between 1 and 4
	 */
	private int charSet(){
	    return (int) (Math.random() * (5 - 1) + 1);
	}
	
	/**
 	* creates a random Map for playing
 	*/
	public void createField(){
			
	/* creates Border of the Map */
		makeBorder();
		
	/* writes randomly the Starts in the Array */ 	
		newMap[startX][startY] = 'S';
		
	/* creates the way from start to finish */
		int x = startX;
		int y = startY;
		int i = 0;
				 do {
					 int c = charSet();
					 	if (c == 1 && (y + 1) < (width -2) && newMap[x][y + 1] == '\u0000' && newMap[x][y + 2] != '#'){	
					 		y = y + 1;
					 		newMap[x][y] = '>';
					 		if (newMap[x][y - 1] != 'S'){
					 			newMap[x][y - 1] = '>';
					 		}
					 	}
					 	else if (c == 2 && (y - 1) > 0 && newMap[x][y - 1] == '\u0000' && newMap[x][y - 2] != '#'){		
					 		y = y - 1;
							newMap[x][y] = '<';
							if (newMap[x][y + 1] != 'S'){
								newMap[x][y + 1] = '<';
							}
					 	}
					 	else if (c == 3 && (x + 1) < (height -2) && newMap[x + 1][y] == '\u0000' && newMap[x + 2][y] != '#'){
					 		x = x + 1;
							newMap[x][y] = 'v';
							if (newMap[x - 1][y] != 'S'){
								newMap[x - 1][y] = 'v';
							}
					 	}
					 	else if (c == 4 && (x - 1) > 0 && newMap[x - 1][y] == '\u0000' && newMap[x - 2][y] != '#'){
					 		x = x - 1;
							newMap[x][y] = '^';	
							if (newMap[x + 1][y] != 'S'){
							newMap[x + 1][y] = '^';
							}
					 	}
					 
					 i++;
				 }
				 while (i < length );
				 
				 /* set finish at the end of the Map */
				 newMap[x][y] = 'X';
							
	
	/* fills the rest of the field with '_' */
		fillRest();
	}
	
	/**
	 *	new Level to String just for public Tests ! 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (char[] chars : newMap) {
			for (int j = 0; j < newMap[0].length; j++) {
				sb.append(chars[j]);
			}
			sb.append("\n");
		}

		return sb.toString();
	}
	
	
	/**
	 * writes the Map into file
	 */
	public void makeFile(){
		  try {
				File f = new File("src/de/tu_darmstadt/gdi1/resources/levels/OwnLevel.txt");
				f.createNewFile();
				FileWriter fw = new FileWriter("src/de/tu_darmstadt/gdi1/resources/levels/OwnLevel.txt");
				
				for (int i = 0; i < height; i++){
					for (int j = 0; j < width; j++){
						if (j == width - 1){
							fw.write(newMap[i][j]);
							fw.write(System.getProperty("line.separator"));
						}
						else {
							fw.write(newMap[i][j]);
						}										
					}
				}
				fw.close();
		    }
			catch (Exception e) {
				System.out.println(e);
			}
	}
}

	
