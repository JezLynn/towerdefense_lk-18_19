package de.tu_darmstadt.gdi1.towerdefense.highscore;

/**
 * @author Michael Schlittenbauer
 * @version 0.1
 * 
 * Defines the type of Entry for the Highscore
 * 
 */
public class HighScoreEntry implements Comparable<HighScoreEntry> {

	private String name;
	private Integer score;

	HighScoreEntry(String name, Integer score) {
		this.name = name;
		this.score = score;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}
	

	@Override
	public int compareTo(HighScoreEntry o) {
		if (o.getScore() == null && this.getScore() == null) {
			return 0;
		}
		if (this.getScore() == null) {
			return 1;
		}
		if (o.getScore() == null) {
			return -1;
		}
		return this.getScore().compareTo(o.getScore());
	}
}
