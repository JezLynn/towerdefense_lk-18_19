package de.tu_darmstadt.gdi1.towerdefense.tower;

/**
 * Creates the Class Tower
 * @author Saed
 *
 */
public class Tower {
	private String name;
	private int damage;
	private int range;
	private int rank;
	private int cost;
	private int frequency;
	public char type;
	private boolean status;
	
	/**
	 * 
	 * @param TowerName is the Type of Tower
	 * @param counter is the Number of createt Tower
	 */
	
	public Tower(char TowerName, int counter)
	{
		this.name = "tower"+counter;
		this.type = TowerName;
		
		switch(TowerName) //TODO
		{
		case 't': {damage=1;  range=1; rank=1; frequency=100; cost=30;} break; //Nah
		
		case 'p': {damage=1;  range=1; rank=1; frequency=200; cost=45;} break; //Fern
		
		case 'i': {damage=0;  range=1; rank=1; frequency=200; cost=60;} break; //Slow

		case 'H': {damage=1;  range=1; rank=1; frequency=1; cost=30;} break; //Testtower
		}
	}
/**
 * 	
 * @return returns Towername
 */
	public String getName(){
		return name;
	}
	/**
	 * 
	 * @return returns Towerrange
	 */
	public double getrange(){
		return range;
	}
	/**
	 * 
	 * @return returns Towerdamage
	 */
	public int getDamage(){
		return damage;
	}
	/** 
	 * 
	 * @return returns TowerRank
	 */
	public int getRank(){
		return rank;
	}
	/**
	 * 
	 * @return returns TowerCost
	 */
	public int getCost(){
		return cost;
	}
	/** 
	 * 
	 * @return returns TowerFrequency
	 */
	public int getFrequency(){
		return frequency;
	}
	/**
	 * 
	 * @return returns TowerType
	 */
	public char getType()
	{
		return type;
	}
	/**
	 * 
	 * @return returns if tower is able to shoot
	 */
	public boolean getStatus()
	{
		return status;
	}
	/** 
	 * 
	 * @param shoot shows if the monster got shootet
	 */
	public void setStatus(boolean shoot)
	{
		status=shoot;
	}
	/**
	 * 
	 * @param Gold Cost of Tower
	 * @return returns 0 if tower cant be upgraded anymore
	 */
	public int upgrade(int Gold)
	{ 
		int d=0; int r=0; int f=0; int c=0;

		if(rank<=3)
		{
		switch(type)
		{
		case 't':
		{
		  switch(rank)//Tower Normal Nahkampf
		  {
		  case 1: d=1; r=0; f=-10; c=15;  //1. Upgrade
			  break;
		  case 2: d=2; r=0; f=-20; c=60; //2. Upgrade
			  break;
		  case 3: d=2; r=1; f=-40; c=120; //3. Upgrade
			  break;		
		  }
		} break;
		
		case 'p':
			  switch(rank)//Poison Fernkampf
			  {
			  case 1: d=0; r=1; f=-10; c=15;
				  break;
			  case 2: d=0; r=2; f=-40; c=60;
				  break;
			  case 3: d=1; r=2; f=-60; c=240; 
				  break;		
			  }break;
			  
		case 'i':
			  switch(rank)//Ice slow
			  {
			  case 1: d=0; r=0; f=-30; c=15;
				  break;
			  case 2: d=0; r=1; f=-30; c=60;
				  break;
			  case 3: d=0; r=2; f=-60; c=240; 
				  break;
			  default: return 0;			  
			  }break;		
		}
		
		if(Gold >=c) //wenn genug gold für turm update
     	{
//			System.out.println("Tower SSTATS: d r f r"+damage+" "+range+" "+frequency+" "+rank+" ");
			damage=damage+d; range=range+r; frequency=frequency+f; rank++;
//			System.out.println("Tower SSTATS: d r f r"+damage+" "+range+" "+frequency+" "+rank+" ");
			return c; //return cost
     	}
		}
		return 0;
	}
}
