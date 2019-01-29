package de.tu_darmstadt.gdi1.towerdefense.MapBuilder;

import java.io.File;
import java.io.FileWriter;

import de.tu_darmstadt.gdi1.towerdefense.classes.ParsMap;
import de.tu_darmstadt.gdi1.towerdefense.exceptions.SyntaxNotCorrectException;

public class Builder {
	


	
	/**
	 * defines the values of the used attributes for the level to generate.
	 */	
	ParsMap pm;
	int height = fieldHeight(7, 20);
	int width = fieldWidth(7, 25);
	int length = wayLength((int) (((height-2)*(width-2))/2),(int) (((height-2)*(width-2))/1.5));
	
	public char newMap[][] = new char[height][width];

	int startX = setStartX(1, height - 2);
	int startY = setStartY(1, width - 2);

	public Builder(){
		
	}
	
	/**
	 * For test only
	 */
	
	public Builder (int width, int height){
		newMap = new char [width+1][height+1];
		createField();
	}
	
	/**
	 * generates a number for the width (cols) of the field
	 * @usage :fieldWidth(2, 25) --> creats a number between 2 and 25
	 */
	public int fieldHeight(int min, int max){
		max++;
	    return (int) (Math.random() * (max - min) + min); 
	}
	
	/**
	 * generates a number for the heigth (rows) of the field
	 * @usage :fieldWidth(2, 10) --> creates number between 2 and 10
	 */
	public int fieldWidth(int min, int max){
		max++;
	    return (int) (Math.random() * (max - min) + min); 
	}

	/**
	 * set the value of the width and height Points of the releasepoint
	 */
	public int setStartX(int min, int max){
	    return (int) (Math.random() * (max - min) + min);    	
	}		
	public int setStartY(int min, int max){
	    return (int) (Math.random() * (max - min) + min);    	
	}	

	/**
	 * the length of the way
	 */
	public int wayLength(int min, int max){
	    return (int) (Math.random() * (max - min) + min);    	
	}	
	
	/**
	 * Fills the rest of the empty field after the lead to finish
	 */
	public void fillRest(){
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
	public void makeBorder(){
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
	public int charSet(int min, int max){
		max++;
	    return (int) (Math.random() * (max - min) + min); 
	}
	
	/**
 	* creates a random Map for playing 
	 * @throws SyntaxNotCorrectException 
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
					 int c = charSet(1, 4);
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
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < newMap.length; i++) {
			for (int j = 0; j < newMap[0].length; j++) {
				sb.append(newMap[i][j]);
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
						else if (j < width){
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

	
