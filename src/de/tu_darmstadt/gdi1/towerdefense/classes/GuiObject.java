	package de.tu_darmstadt.gdi1.towerdefense.classes;

	import java.awt.Point;
	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.Map;

	public class GuiObject {

		static public int pictureSize = 48;
		static public Map<String, Point> GuiObjectMap = new HashMap<>();

		/**
		 * Constructor of the Class
		 * get an new an the x and y Coordinates and put them into a HashMap
		 * 
		 * @param name the Key for the HashMap
		 * @param x the x Coordinate
		 * @param y the y Coordinate
		 */
		GuiObject(String name, int x, int y) {
			GuiObjectMap.put(name, new Point(x * pictureSize, y * pictureSize));
		}
		
		/**
		 * Getter for the HashMap
		 * 
		 * @return the GuiObject HashMap
		 */
		public static Map<String, Point> getGuiMap() {
			return GuiObjectMap;
		}

		/**
		 * to update a Value in the HashMap
		 * 
		 * @param name the Key to get the Value
		 * @param x the x to update
		 * @param y the y to update
		 */
		public static void update(String name, int x, int y) {

			GuiObjectMap.get(name).translate((x * pictureSize), (y * pictureSize));
		}

		/**
		 * Calculates the middle of the Picture
		 * @param name the Key to get the Value
		 * @return the mid x Coordinate
		 */
		static double getmidX(String name) // return int x
		{
			return (GuiObjectMap.get(name).getX()+(pictureSize/2f));
		}
		
		/**
		 * Calculates the middle of the Picture
		 * @param name the Key to get the Value
		 * @return the mid y Coordinate
		 */
		static double getmidY(String name) // return int x
		{
			return (GuiObjectMap.get(name).getY()+(pictureSize/2f));
		}

		/**
		 * to get the x Coordinate by a key
		 * @param key the to get the x
		 * @return the x Value
		 */
		public static int getX(String key) // return int x
		{
			return (int)(GuiObjectMap.get(key).getX()/pictureSize);
		}
		
		/**
		 * to get the y Coordinate by a key
		 * @param key the to get the y
		 * @return the y Value
		 */
		public static int getY(String key) // return int y
		{
			return (int)(GuiObjectMap.get(key).getY()/pictureSize);
		}

		/**
		 * to get the Key associated to the given Object description
		 * 
		 * @param map the HashMap to browse
		 * @param value the given value
		 * @param scale the scale of the game
		 * 
		 * @return the associated Key
		 */
		public static String getKey(Map<String, Point> map, Point value, float scale) {
			String s = null;
			for (String mapPiece : map.keySet()) {
				Point guiPoint = map.get(mapPiece);

				if ((guiPoint.getX() * scale <= value.getX() &&
						(guiPoint.getX() + pictureSize) * scale >= value.getX()) &&
						(guiPoint.getY() * scale <= value.getY() &&
								(guiPoint.getY() + pictureSize) * scale >= value.getY())) {
					s = mapPiece;
				}
			}
			return s;
		}
	}
