	package de.tu_darmstadt.gdi1.towerdefense.classes;

	import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import de.tu_darmstadt.gdi1.towerdefense.exceptions.SyntaxNotCorrectException;
import de.tu_darmstadt.gdi1.towerdefense.ui.GameState;

	public class ParsMap {
		public char parsedMap[][];
		char ch;
		public int startPointsCounter = 0;
		public int destinationPointsCounter = 0;
		int counter = 0;
		public static int startcounter = 0;

		/*
		 * (non-javadoc)
		 */
		private GuiObject guiObject;

		/**
		 * Makes a String from a textfile
		 * 
		 * @return char[][] with all symbols of the textfile
		 * @return String with the chars from the textfile
		 * @throws IOException
		 */
		public char[][] reading(String a) {
			int i = 0;
			int j = 0;
			int z = 0;
			try {
				BufferedReader in = new BufferedReader(new FileReader(a));
				String zeile = null;
				StringBuilder sb = new StringBuilder();
				while ((zeile = in.readLine()) != null) {
					sb.append(zeile).append("\n");
					if (z == 0) {
						for (int k = 0; k < zeile.length(); k++) {
							if (zeile.charAt(k) != '\n') {
								j++;
							}
							z++;
						}
					}
					i++;
				}
				in.close();
				parsedMap = new char[i][j];
				if (i > 16 || j > 12) {
					GameState.setScale(0.7f);
				} else
					GameState.setScale(1);

				stringToArray(sb.toString());
				sb = null;

			} catch (IOException e) {
				System.out.println("Cannot open " + e);
			}
			return parsedMap;
		}

		/**
		 * creates a new char "parsedMap" and fill it with the characters from
		 * the String
		 * 
		 * @param map
		 */

		public void stringToArray(String map) {
			int ch = 0;
			int j = 0;
			startPointsCounter = 0;
			for (int i = 0; i < map.length(); i++) {
				if (map.substring(i, i + 1).equals("\n")) {
					for (int k = 0; k < ch; ++k) {
						parsedMap[j][k] = map.charAt((i + 1) - (ch + 1) + k);

						// zählen der Startpunkte
						if (map.charAt((i + 1) - (ch + 1) + k) == 'S') {
							startPointsCounter++;
						}
						// zählen der Zielpunkte
						else if (map.charAt((i + 1) - (ch + 1) + k) == 'X') {
							destinationPointsCounter++;
						}

					}
					ch = 0;
					j++;
				} else if (i + 1 == map.length()) {
					for (int k = 0; k <= ch; ++k) {
						parsedMap[j][k] = map.charAt((i + 1) - (ch + 1) + k);
					}
				} else
					ch = ch + 1;
			}
		}

		/**
		 * String with the value of the array (named parsedMap)
		 * 
		 * @return String with values of 2-dim-array (parsedMap)
		 */
		public String toString() {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < parsedMap.length; i++) {
				if(i+1< parsedMap.length){
					for (int j = 0; j < parsedMap[i].length; j++) {
						sb.append(parsedMap[i][j]);
					}
					sb.append("\n");
				}
				else {
					for (int j = 0; j < parsedMap[i].length; j++) {
						sb.append(parsedMap[i][j]);
					}			
				}
			}
			return sb.toString();
		}

		/**
		 * Checks: is the syntax of the textfile correct? Checks: is there min
		 * one starting Point Checks: is there exactly one destination Point
		 * 
		 * @return false -> Syntax wrong
		 * @return true -> Syntax right
		 * 
		 * @param startPointCounter : Count of Startpoints in Map
		 * @param destinationPointsCounter : Count of Destinationpoints in Map
		 * 
		 * @throws SyntaxNotCorrectException
		 */
		public boolean isSyntaxCorrect() throws SyntaxNotCorrectException {
			boolean a = true;
			if (startPointsCounter < 1 || destinationPointsCounter < 1
					|| startPointsCounter < destinationPointsCounter) {
				a = false;
				throw new SyntaxNotCorrectException();

			} else {

				// prüfen ob es einen Pfad vom Start zum Ziel gibt
				for (int i = 0; i < parsedMap.length; i++) {
					for (int j = 0; j < parsedMap[0].length; j++) {
						
						// prüfen oder der Startpunkt angebunden ist. 0
						if ((parsedMap[i][j] == 'S' && ((parsedMap[i + 1][j] == 'v'	|| parsedMap[i + 1][j] == '<' || parsedMap[i + 1][j] == '>')
								|| (parsedMap[i - 1][j] == '^' || parsedMap[i - 1][j] == '<' || parsedMap[i - 1][j] == '>')
								|| (parsedMap[i][j + 1] == '>' || parsedMap[i][j + 1] == '^' || parsedMap[i][j + 1] == 'v')
								|| (parsedMap[i][j - 1] == '<' || parsedMap[i][j - 1] == '^' || parsedMap[i][j - 1] == 'v')))
								// prüfen ob mindestens ein Weg ins Ziel führt
								|| (parsedMap[i][j] == 'X' && 
								(parsedMap[i + 1][j] == '^'	|| parsedMap[i - 1][j] == 'v' || parsedMap[i][j - 1] == '>' || parsedMap[i][j + 1] == '<'))
								// prüfen ob ein '>' immer destiniert.
								|| (parsedMap[i][j] == '>' && (parsedMap[i][j + 1] == '>'
										|| parsedMap[i][j + 1] == 'v'|| parsedMap[i][j + 1] == '^' || parsedMap[i][j + 1] == 'X'))
								// prüfen ob ein '<' immer destiniert.
								|| (parsedMap[i][j] == '<' && (parsedMap[i][j - 1] == '<'
										|| parsedMap[i][j - 1] == 'v' || parsedMap[i][j - 1] == '^' || parsedMap[i][j - 1] == 'X'))
								// prüfen ob ein 'v' immer destiniert.
								|| (parsedMap[i][j] == 'v' && (parsedMap[i + 1][j] == 'v'
										|| parsedMap[i + 1][j] == '<' || parsedMap[i + 1][j] == '>' || parsedMap[i + 1][j] == 'X'))
								// prüfen ob ein '^' immer destiniert.
								|| (parsedMap[i][j] == '^' && (parsedMap[i - 1][j] == '^'
										|| parsedMap[i - 1][j] == '<' || parsedMap[i - 1][j] == '>' || parsedMap[i - 1][j] == 'X'))
								// überspringen von # und _
								|| (parsedMap[i][j] == '#' || parsedMap[i][j] == '_' || parsedMap[i][j] == 't')) {
							a = true;
						} else {
							a = false;
							throw new SyntaxNotCorrectException();
						}
					} // end of innner-loop (j)
				} // end of outer-loop (i)
			} // end of else
			return a;
		} // end of class

		/**
		 * Creates from the array (parsedMap) a new 1-dim-array (GuiObject)
		 * 
		 * @return :GuiObject with String and the coords for the picture and
		 *          midpoint
		 * 
		 * @param :getStartDrection: direction of Start
		 * 
		 * @throws SyntaxNotCorrectException
		 */
		public void arrayToGuiObject() throws SyntaxNotCorrectException {
			Random rand = new Random();
			
			if (isSyntaxCorrect() == true) {

				for (int i = 0; i < parsedMap.length; i++) {
					for (int j = 0; j < parsedMap[0].length; j++) {

						/*--------------------------------------------------------------------------------*/
						// north-west border
						if (parsedMap[i][j] == '#' && i == 0  && j == 0)
						{
							new GuiObject("border_nw" + counter, j, i);
							counter++;
						}
						// north-east border
						else if (parsedMap[i][j] == '#' &&  i == 0 && j == parsedMap[0].length-1)
						{
							new GuiObject("border_ne" + counter, j, i);
							counter++;
						}
						// south-east border
						else if (parsedMap[i][j] == '#' &&  i == parsedMap.length-1 && j == parsedMap[0].length-1)
						{
							new GuiObject("border_se" + counter, j, i);
							counter++;
						}
						// south-west border
						else if (parsedMap[i][j] == '#' && i == parsedMap.length-1 && j == 0)
						{
							new GuiObject("border_sw" + counter, j, i);
							counter++;
						}									
						// border east-side
						else if(parsedMap[i][j] == '#' && j == parsedMap[0].length-1  && (i > 0 && i < parsedMap.length-1))
						{
							new GuiObject("border_e" + counter, j, i);
							counter++;
						}
						// border west-side
						else if(parsedMap[i][j] == '#' && j == 0  && (i > 0 && i < parsedMap.length-1))
						{
							new GuiObject("border_w" + counter, j, i);
							counter++;
						}
						// border north-side
						else if (parsedMap[i][j] == '#' && i == 0  && (j > 0 && j < parsedMap[0].length-1))
						{
							new GuiObject("border_n" + counter, j, i);
							counter++;
						}
						// border south-side
						else if (parsedMap[i][j] == '#' && i == parsedMap.length-1  && (j > 0 && j < parsedMap[0].length-1))
						{
							new GuiObject("border_s" + counter, j, i);
							counter++;
						}

						/*--------------------------------------------------------------------------------*/
						// This is a tower defined in the Map (picture ...png)
						else if ((parsedMap[i][j] == 't')) {
							// Set value of GuiObject Array
							new GuiObject("tower" + counter, j, i);
							counter++;
						}
						/*--------------------------------------------------------------------------------*/
						// This is a empty field in the Map (picture back.png)
						else if ((parsedMap[i][j] == '_')) {
							// Set value of GuiObject Array
							if(rand.nextBoolean()){
								new GuiObject("flower" + counter, j, i);
								counter++;
							}else if(rand.nextBoolean()){
								new GuiObject("tree" + counter, j, i);
								counter++;
							}else {
								new GuiObject("field" + counter, j, i);
								counter++;
							}
						}
						/*--------------------------------------------------------------------------------*/
						// This is a release-point
						else if (parsedMap[i][j] == 'S') {
							// picture (su.png)
							if ((parsedMap[i][j] == 'S'
									&& parsedMap[i + 1][j] == 'v'
									&& parsedMap[i + 1][j + 1] != '<' && parsedMap[i + 1][j - 1] != '>')
									|| (parsedMap[i][j] == 'S'
											&& parsedMap[i + 1][j] == '>'
											&& parsedMap[i + 1][j - 1] != '>' && parsedMap[i + 2][j] != '^')
									|| (parsedMap[i][j] == 'S'
											&& parsedMap[i + 1][j] == '<'
											&& parsedMap[i + 1][j + 1] != '<' && parsedMap[i + 2][j] != '^')) {
								new GuiObject("start_south" + startcounter, j,
										i);
								startcounter++;
							}
							// picture (so.png)
							else if ((parsedMap[i][j] == 'S'
									&& parsedMap[i - 1][j] == '^'
									&& parsedMap[i - 1][j - 1] != '>' && parsedMap[i - 1][j + 1] != '<')
									|| (parsedMap[i][j] == 'S'
											&& parsedMap[i - 1][j] == '<'
											&& parsedMap[i - 2][j] != 'v' && parsedMap[i - 1][j + 1] != '<')
									|| (parsedMap[i][j] == 'S'
											&& parsedMap[i - 1][j] == '>'
											&& parsedMap[i - 2][j] != 'v' && parsedMap[i - 1][j - 1] != '>')) {
								new GuiObject("start_north" + startcounter, j,
										i);
								startcounter++;
							}
							// picture (sr.png)
							else if ((parsedMap[i][j] == 'S'
									&& parsedMap[i][j + 1] == '>'
									&& parsedMap[i + 1][j + 1] != '^' && parsedMap[i - 1][j + 1] != 'v')
									|| (parsedMap[i][j] == 'S'
											&& parsedMap[i][j + 1] == 'v'
											&& parsedMap[i - 1][j + 1] != 'v' && parsedMap[i][j + 2] != '<')
									|| (parsedMap[i][j] == 'S'
											&& parsedMap[i][j + 1] == '^'
											&& parsedMap[i + 1][j + 1] != '^' && parsedMap[i][j + 2] != '<')) {
								new GuiObject("start_east" + startcounter, j, i);
								startcounter++;
							}
							// picture (sl.png)
							else if ((parsedMap[i][j] == 'S'
									&& parsedMap[i][j - 1] == '<'
									&& parsedMap[i - 1][j + 1] != 'v' && parsedMap[i + 1][j - 1] != '^')
									|| (parsedMap[i][j] == 'S'
											&& parsedMap[i][j - 1] == 'v'
											&& parsedMap[i - 1][j - 1] != 'v' && parsedMap[i][j - 2] != '>')
									|| (parsedMap[i][j] == 'S'
											&& parsedMap[i][j - 1] == '^'
											&& parsedMap[i + 1][j - 1] != '^' && parsedMap[i][j - 2] != '>')) {
								new GuiObject("start_west" + startcounter, j, i);
								startcounter++;
							}
						}

						/*--------------------------------------------------------------------------------*/
						// This is the destination-point
						// Checks whether there is only one opportunity for the
						// monsters to walk into finish

						// Finish from north (picture zo.png)
						else if ((parsedMap[i][j] == 'X' && parsedMap[i - 1][j] == 'v')
								&& (parsedMap[i][j - 1] != '>')
								&& (parsedMap[i][j + 1] != '<')
								&& (parsedMap[i + 1][j] != '^')) {
							new GuiObject("finish_north" + counter, j, i);
							counter++;
						}

						// Finish from south (picture zu.png)
						else if ((parsedMap[i][j] == 'X' && parsedMap[i + 1][j] == '^')
								&& (parsedMap[i][j - 1] != '>')
								&& (parsedMap[i][j + 1] != '<')
								&& (parsedMap[i - 1][j] != 'v')) {
							new GuiObject("finish_south" + counter, j, i);
							counter++;
						}

						// Finish from west (picture zl.png)
						else if ((parsedMap[i][j] == 'X' && parsedMap[i][j - 1] == '>')
								&& (parsedMap[i][j + 1] != '<')
								&& (parsedMap[i + 1][j] != '^')
								&& (parsedMap[i - 1][j] != 'v')) {
							new GuiObject("finish_west" + counter, j, i);
							counter++;
						}

						// Finish from west (picture zr.png)
						else if ((parsedMap[i][j] == 'X' && parsedMap[i][j + 1] == '<')
								&& (parsedMap[i][j - 1] != '>')
								&& (parsedMap[i + 1][j] != '^')
								&& (parsedMap[i - 1][j] != 'v')) {
							new GuiObject("finish_east" + counter, j, i);
							counter++;
						}

						/*--------------------------------------------------------------------------------*/
						// This is the t-cross to south (picture: tku.png)
						else if ((parsedMap[i][j] == '>'
								&& (parsedMap[i - 1][j] != 'v' || parsedMap[i - 1][j] != 'S')
								&& (parsedMap[i + 1][j] == '^')
								&& (parsedMap[i][j + 1] == '>'
										|| parsedMap[i][j + 1] == '^'
										|| parsedMap[i][j + 1] == 'v' || parsedMap[i][j + 1] == 'X') && (parsedMap[i][j - 1] == '>'))
								|| (parsedMap[i][j] == '<'
										&& (parsedMap[i - 1][j] != 'v' || parsedMap[i - 1][j] != 'S')
										&& (parsedMap[i + 1][j] == '^')
										&& (parsedMap[i][j - 1] == '<'
												|| parsedMap[i][j - 1] == '^'
												|| parsedMap[i][j - 1] == 'v' || parsedMap[i][j - 1] == 'X') && (parsedMap[i][j + 1] == '<'))
								|| (parsedMap[i][j] == 'v'
										&& (parsedMap[i - 1][j] != 'v' || parsedMap[i - 1][j] != 'S')
										&& (parsedMap[i + 1][j] == 'v'
												|| parsedMap[i + 1][j] == '<'
												|| parsedMap[i + 1][j] == '>' || parsedMap[i + 1][j] == 'X')
										&& (parsedMap[i][j - 1] == '>') && (parsedMap[i][j + 1] == '<'))) {
							new GuiObject("tcs" + counter, j, i);
							counter++;
						}

						/*--------------------------------------------------------------------------------*/
						// This is the t-cross to east (picture: tkr.png)
						else if ((parsedMap[i][j] == '^'
								&& (parsedMap[i - 1][j] == '^'
										|| parsedMap[i - 1][j] == 'X'
										|| parsedMap[i - 1][j] == '<' || parsedMap[i - 1][j] == '>')
								&& (parsedMap[i + 1][j] == '^')
								&& (parsedMap[i][j + 1] == '<') && (parsedMap[i][j - 1] != '>' || parsedMap[i][j - 1] != 'S'))
								|| (parsedMap[i][j] == 'v'
										&& (parsedMap[i - 1][j] == 'v')
										&& (parsedMap[i + 1][j] == 'v'
												|| parsedMap[i + 1][j] == '<'
												|| parsedMap[i + 1][j] == '>' || parsedMap[i + 1][j] == 'X')
										&& (parsedMap[i][j + 1] == '<') && (parsedMap[i][j - 1] != '>' || parsedMap[i][j - 1] != 'S'))
								|| (parsedMap[i][j] == '>'
										&& (parsedMap[i - 1][j] == 'v')
										&& (parsedMap[i + 1][j] == '^')
										&& (parsedMap[i][j - 1] != '>' || parsedMap[i][j - 1] != 'S') && (parsedMap[i][j + 1] == '>'
										|| parsedMap[i][j + 1] == 'X'
										|| parsedMap[i][j + 1] == '^' || parsedMap[i][j + 1] == 'v'))) {
							new GuiObject("tce" + counter, j, i);
							counter++;
						}

						/*--------------------------------------------------------------------------------*/
						// This is the t-cross to west (picture: tkl.png)
						else if ((parsedMap[i][j] == '^'
								&& (parsedMap[i - 1][j] == '^'
										|| parsedMap[i - 1][j] == 'X'
										|| parsedMap[i - 1][j] == '<' || parsedMap[i - 1][j] == '>')
								&& (parsedMap[i + 1][j] == '^')
								&& (parsedMap[i][j + 1] != '<' || parsedMap[i][j + 1] != 'S') && (parsedMap[i][j - 1] == '>'))
								|| (parsedMap[i][j] == 'v'
										&& (parsedMap[i - 1][j] == 'v')
										&& (parsedMap[i + 1][j] == 'v'
												|| parsedMap[i + 1][j] == '<'
												|| parsedMap[i + 1][j] == '>' || parsedMap[i + 1][j] == 'X')
										&& (parsedMap[i][j + 1] != '<') && (parsedMap[i][j - 1] == '>' || parsedMap[i][j - 1] == 'S'))
								|| (parsedMap[i][j] == '<'
										&& (parsedMap[i - 1][j] == 'v')
										&& (parsedMap[i + 1][j] == '^')
										&& (parsedMap[i][j + 1] != '<' || parsedMap[i][j + 1] != 'S') && (parsedMap[i][j - 1] == '<'
										|| parsedMap[i][j - 1] == 'X'
										|| parsedMap[i][j - 1] == '^' || parsedMap[i][j - 1] == 'v'))) {
							new GuiObject("tcw" + counter, j, i);
							counter++;
						}
						/*--------------------------------------------------------------------------------*/
						// This is the t-cross to north (picture: tkn.png)
						else if ((parsedMap[i][j] == '^'
								&& (parsedMap[i - 1][j] == '^'
										|| parsedMap[i - 1][j] == 'X'
										|| parsedMap[i - 1][j] == '<' || parsedMap[i - 1][j] == '>')
								&& (parsedMap[i + 1][j] != '^' || parsedMap[i + 1][j] != 'S')
								&& (parsedMap[i][j + 1] == '<') && (parsedMap[i][j - 1] == '>'))
								|| (parsedMap[i][j] == '>'
										&& (parsedMap[i - 1][j] == 'v')
										&& (parsedMap[i + 1][j] != '^' || parsedMap[i + 1][j] != 'S')
										&& (parsedMap[i][j - 1] == '>') && (parsedMap[i][j + 1] == '>'
										|| parsedMap[i][j + 1] == 'X'
										|| parsedMap[i][j + 1] == '^' || parsedMap[i][j + 1] == 'v'))
								|| (parsedMap[i][j] == '<'
										&& (parsedMap[i - 1][j] == 'v')
										&& (parsedMap[i + 1][j] != '^' || parsedMap[i + 1][j] != 'S')
										&& (parsedMap[i][j + 1] == '<') && (parsedMap[i][j - 1] == '<'
										|| parsedMap[i][j - 1] == 'X'
										|| parsedMap[i][j - 1] == '^' || parsedMap[i][j - 1] == 'v'))) {
							new GuiObject("tcn" + counter, j, i);
							counter++;
						}
						
						/*--------------------------------------------------------------------------------*/
						// This is the west-east-straight (picture: lr.png)
						else if ((parsedMap[i][j] == '<'
								&& (parsedMap[i - 1][j] != 'v')
								&& (parsedMap[i + 1][j] != '^')
								&& (parsedMap[i][j - 1] == '<'|| parsedMap[i][j - 1] == 'X'	|| parsedMap[i][j - 1] == '^' || parsedMap[i][j - 1] == 'v') 
								&& (parsedMap[i][j + 1] == '<' || parsedMap[i][j + 1] == 'S'))
								|| (parsedMap[i][j] == '>'
									&& (parsedMap[i - 1][j] != 'v')
									&& (parsedMap[i + 1][j] != '^')
									&& (parsedMap[i][j + 1] == '>'	|| parsedMap[i][j + 1] == 'X'|| parsedMap[i][j + 1] == '^' || parsedMap[i][j + 1] == 'v') 
									&& (parsedMap[i][j - 1] == '>' || parsedMap[i][j - 1] == 'S'))) {
							new GuiObject("swe" + counter, j, i);
							counter++;
						}

						/*--------------------------------------------------------------------------------*/
						// This is the north-south-straight (picture: ou.png)
						else if ((parsedMap[i][j] == '^'
								&& (parsedMap[i - 1][j] == '<'|| parsedMap[i - 1][j] == '>'	|| parsedMap[i - 1][j] == '^' || parsedMap[i - 1][j] == 'X')
								&& (parsedMap[i + 1][j] == 'S' || parsedMap[i + 1][j] == '^')
								&& (parsedMap[i][j + 1] != '<') 
								&& (parsedMap[i][j - 1] != '>'))
								|| (parsedMap[i][j] == 'v'
									&& (parsedMap[i - 1][j] == 'S' || parsedMap[i - 1][j] == 'v')
									&& (parsedMap[i + 1][j] == '<' || parsedMap[i + 1][j] == '>' || parsedMap[i + 1][j] == 'v' || parsedMap[i + 1][j] == 'X')
									&& (parsedMap[i][j + 1] != '<') 
									&& (parsedMap[i][j - 1] != '>'))) {
							new GuiObject("sns" + counter, j, i);
							counter++;
						}
						/*--------------------------------------------------------------------------------*/
						// This is a curve from west-to-south (picture: lu.png)
						else if ((parsedMap[i][j] == 'v'
								&& (/* parsedMap[i - 1][j] != 'S' && */parsedMap[i - 1][j] != 'v')
								&& (parsedMap[i + 1][j] == 'v'
										|| parsedMap[i + 1][j] == '>'
										|| parsedMap[i + 1][j] == '<' || parsedMap[i + 1][j] == 'X')
								&& (parsedMap[i][j + 1] != '<') && (parsedMap[i][j - 1] == '>' || parsedMap[i][j - 1] == 'S'))
								|| (parsedMap[i][j] == '<'
										&& (parsedMap[i - 1][j] != 'v')
										&& (parsedMap[i + 1][j] == '^' || parsedMap[i + 1][j] == 'S')
										&& (/* parsedMap[i][j + 1] != 'S' && */parsedMap[i][j + 1] != '<') && (parsedMap[i][j - 1] == '<'
										|| parsedMap[i][j - 1] == '^'
										|| parsedMap[i][j - 1] == 'v' || parsedMap[i][j - 1] == 'X'))) {
							new GuiObject("cws" + counter, j, i);
							counter++;
						}

						/*--------------------------------------------------------------------------------*/
						// This is a curve from west-to-north (picture: lo.png)
						else if ((parsedMap[i][j] == '^'
									&& (parsedMap[i - 1][j] == '<' || parsedMap[i - 1][j] == '>' || parsedMap[i - 1][j] == '^' || parsedMap[i - 1][j] == 'X')
									&& (parsedMap[i + 1][j] != '^' || parsedMap[i + 1][j] != 'S')
									&& (parsedMap[i][j + 1] != '<') 
									&& (parsedMap[i][j - 1] == '>' || (parsedMap[i][j - 1] == 'S' && parsedMap[i + 1][j] != '^')))
								|| (parsedMap[i][j] == '<'
									&& (parsedMap[i - 1][j] == 'v' || parsedMap[i - 1][j] == 'S')
									&& (parsedMap[i + 1][j] != '^')
									&& (parsedMap[i][j + 1] != '<' || parsedMap[i][j + 1] != 'S') 
									&& (parsedMap[i][j - 1] == '<' || parsedMap[i][j - 1] == '^'|| parsedMap[i][j - 1] == 'v' || parsedMap[i][j - 1] == 'X'))) {
							new GuiObject("cwn" + counter, j, i);
							counter++;
						}

						/*--------------------------------------------------------------------------------*/
						// This is a curve from east-to-north (picture: ro.png)
						else if ((parsedMap[i][j] == '^'
								&& (parsedMap[i - 1][j] == '^'|| parsedMap[i - 1][j] == '<'	|| parsedMap[i - 1][j] == '>' || parsedMap[i - 1][j] == 'X')
								&& (parsedMap[i + 1][j] != '^')
								&& (parsedMap[i][j + 1] == '<' || parsedMap[i][j + 1] == 'S') 
								&& (parsedMap[i][j - 1] != '>'))
								|| (parsedMap[i][j] == '>'
								&& (parsedMap[i - 1][j] == 'v' || parsedMap[i - 1][j] == 'S')
								&& (parsedMap[i + 1][j] != '^')
								&& (parsedMap[i][j + 1] == '>'|| parsedMap[i][j + 1] == '^'	|| parsedMap[i][j + 1] == 'v' || parsedMap[i][j + 1] == 'X') 
								&& (parsedMap[i][j - 1] != '>'))) {
							new GuiObject("cen" + counter, j, i);
							counter++;
						}

						/*--------------------------------------------------------------------------------*/
						// This is a curve from east-to-south (picture: ru.png)
						else if ((parsedMap[i][j] == '>'
								&& (parsedMap[i - 1][j] != 'v')
								&& (parsedMap[i + 1][j] == '^' || parsedMap[i + 1][j] == 'S')
								&& (parsedMap[i][j + 1] == '^'
										|| parsedMap[i][j + 1] == 'v'
										|| parsedMap[i][j + 1] == '>' || parsedMap[i][j + 1] == 'X') && (parsedMap[i][j - 1] != '>'))
								|| (parsedMap[i][j] == 'v'
										&& (parsedMap[i - 1][j] != 'v')
										&& (parsedMap[i + 1][j] == 'v'
												|| parsedMap[i + 1][j] == '<'
												|| parsedMap[i + 1][j] == '>' || parsedMap[i + 1][j] == 'X')
										&& (parsedMap[i][j + 1] == '<' || parsedMap[i][j + 1] == 'S') && (parsedMap[i][j - 1] != '>'))) {
							new GuiObject("ces" + counter, j, i);
							counter++;
						}
					} // end for-loop (j)
				} // end for-loop (i)
			} // end of if
		} // end of arrayToGuiObject
		
		/**
		 * To get the char at x and y
		 * @param x the x Position in the Array
		 * @param y the y Position in the Array
		 * @return the char at the position
		 */
		public char getCharAt(int x, int y) {
			return parsedMap[x][y];
		}

		/**
		 * 	resets the char-array
		 */
		public void reset() {
			parsedMap = new char[0][0];
		}

		/**
		 * Getter of the property <tt>guiObject</tt>
		 * 
		 * @return Returns the guiObject.
		 * 
		 */

		public GuiObject getGuiObject() {
			return guiObject;
		}

		/**
		 * Setter of the property <tt>guiObject</tt>
		 * 
		 * @param guiObject
		 *            The guiObject to set.
		 * 
		 */
		public void setGuiObject(GuiObject guiObject) {
			this.guiObject = guiObject;
		}
	} // end of class
