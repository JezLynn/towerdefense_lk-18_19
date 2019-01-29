package de.tu_darmstadt.gdi1.towerdefense.test.adapter;

/**
 * Interface to be implemented for the minimal stage.
 * 
 * @author Felix G&uuml;ndling
 */
public interface TowerDefenseTestAdapterMinimal {
	
	/**
	 * Reset the complete system to a blank state.<br>
	 * This means: No level loaded and a clean game state.
	 */
	public void reset();

	/**
	 * Load a level from a string.
	 * 
	 * @param level the level string to be loaded
	 */
	public void loadLevelFromString(String level);
		
	/**
	 * @return the string representation of the currently loaded level
	 */
	public String levelToString();

	/**
	 * @return <code>true</code> if the loaded level is valid
	 */
	public boolean levelIsValid();

	/**
	 * @param x the x coordinate to check
	 * @param y the y coordinate to check
	 * @return the character chars of the level element at given coordinates
	 */
	public char levelElementAt(int x, int y);

	/**
	 * @return players health points in current game state
	 */
	public int getPlayerHealth();
	
	/**
	 * Places a simple tower at the given coordinates.
	 * The tower has to shoot maximum 1x per tick.
	 * The tower will only hit one monster per tick.
	 * The tower can only damage monsters in neighboring fields. 
	 * 
	 * @param x the x coordinate of the tower to place
	 * @param y the y coordinate of the tower to place
	 */
	public void placeTower(int x, int y);

	/**
	 * @param x the x coordinate to check
	 * @param y the y coordinate to check
	 * @return <code>true</code> when there is a tower at the given position
	 */
	public boolean isTowerAt(int x, int y);

	/**
	 * Spawn a simple monster at the given coordinates.
	 * This monster must move at exactly 1 field per tick.
	 * This monster will down after one tower hit.
	 * 
	 * @param x the x coordinate of the monster to place
	 * @param y the y coordinate of the monster to place
	 */
	public void spawnMonster(int x, int y);

	/**
	 * @param x the x coordinate to check
	 * @param y the y coordinate to check
	 * @return <code>true</code> when there is a monster at the given position
	 */
	public boolean isMonsterAt(int x, int y);
	
	/**
	 * @param x the x coordinate to check
	 * @param y the y coordinate to check
	 * @return The number of monsters on the given position (<code>Minimum: 0</code>)
	 */
	public int monsterCount(int x, int y);
	
	/**
	 * Prepare to start the game (if necessary).
	 */
	public void startGame();
	
	/**
	 * This function has to be called when the game has reached the next state.
	 */
	public void tick();
}
