package de.tu_darmstadt.gdi1.towerdefense.monster;

public class Monster {

	private String name;
	private int lifepoints;
	private int speed;
	private int profit;
	public int steps=0;
	public int Start=0;
	public boolean durable;
	public static int killcounter = 0;
	
	/**
	 * 
	 * @param MonsterName is the Type/Name of Monster
	 * @param MonsterLifepoints is the Lifepoints of Monster
	 * @param MonsterSpeed is the Speed of Monster
	 * @param MonsterProfit is the money what the player get if the Monster is dead
	 * @param Start set the Start where the Monster beginnst
	 */
	
	public Monster(String MonsterName, int MonsterLifepoints, int MonsterSpeed, int MonsterProfit, int Start){
		
		name = MonsterName;
		lifepoints = MonsterLifepoints;
		speed = MonsterSpeed;
		profit = MonsterProfit;
		this.Start = Start;
	}
	/**
	 * 
	 * @return returns name of Monster
	 */
	
	public String getName(){
		return name;
	}
	/**
	 * 
	 * @return returns Startplace of Monster
	 */
	public int getStart()
	{
		return Start;
	}
	/**
	 * 
	 * @return couns how number if Movements 
	 */
	
	public int getSteps()
	{
		return steps;
	}
	/**
	 * 
	 * @return returns Lifepoints of Monster
	 */
	public int getLifepoints(){
		return lifepoints;
	}
	/**
	 * 
	 * @return returns Speed of Monster
	 */
	public int getSpeed(){
		return speed;
	}
	/**
	 * 
	 * @return returns profit that Monster gives
	 */
	public int getProfit(){
		return profit;
	}
	/**
	 *  slows down the Monster 
	 */
	public boolean slowSpeed()
	{
		if(speed<20)
		{
			speed=speed+4;
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @param upLifepoints updates lifepoints of Monster
	 */
	public void updateLifepoints(int upLifepoints){
		lifepoints = lifepoints+ upLifepoints;
	}
	/**
	 * 
	 * @return returns that the Monster is Dead
	 */
	public boolean isDead(){
		if(lifepoints == 0){
			killcounter = killcounter + 1;
			return true;		
		}
		else {
			return false;
		}
	}
	
//	public void setStart(int start)
//	{
//		this.Start = start;
//	}

		
}


