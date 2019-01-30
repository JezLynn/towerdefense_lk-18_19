package de.tu_darmstadt.gdi1.towerdefense.tower;

/**
 * Creates the Class Tower
 *
 * @author Saed
 */
public abstract class Tower {
    private String name;
    int damage;
    int rank;
    int range;
    int cost;
    int frequency;
    public char type;
    private boolean status;

    /**
     * @param TowerName is the Type of Tower
     * @param counter   is the Number of createt Tower
     */

    public Tower(char TowerName, int counter) {
        this.name = "tower" + counter;
        this.type = TowerName;
        this.range = 1;
        this.rank = 1;

        switch (TowerName) //TODO
        {
            case 't': {
            }
            break; //Nah

            case 'p': {
            }
            break; //Fern

            case 'i': {
            }
            break; //Slow

            case 'H': {
            }
            break; //Testtower
        }
    }

    /**
     * @return returns Towername
     */
    public String getName() {
        return name;
    }

    /**
     * @return returns Towerrange
     */
    public double getrange() {
        return range;
    }

    /**
     * @return returns Towerdamage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @return returns TowerRank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return returns TowerCost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return returns TowerFrequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @return returns TowerType
     */
    public char getType() {
        return type;
    }

    /**
     * @return returns if tower is able to shoot
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * @param shoot shows if the monster got shootet
     */
    public void setStatus(boolean shoot) {
        status = shoot;
    }

    /**
     * @param Gold Cost of Tower
     * @return returns 0 if tower cant be upgraded anymore
     */
    public abstract int upgrade(int Gold);
    /*
    public int upgrade(int Gold) {


        if (rank <= 3) {
            switch (type) {
                case 't': {

                }
                break;

                case 'p':

                    break;

                case 'i':

                    break;
            }

            if (Gold >= c) //wenn genug gold für turm update
            {
//			System.out.println("Tower SSTATS: d r f r"+damage+" "+range+" "+frequency+" "+rank+" ");
                damage = damage + d;
                range = range + r;
                frequency = frequency + f;
                rank++;
//			System.out.println("Tower SSTATS: d r f r"+damage+" "+range+" "+frequency+" "+rank+" ");
                return c; //return cost
            }
        }
        return 0;
    }*/
}
