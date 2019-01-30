package de.tu_darmstadt.gdi1.towerdefense.tower;

public class Ice extends Tower {
    /**
     * @param TowerName is the Type of Tower
     * @param counter   is the Number of createt Tower
     */
    public Ice(char TowerName, int counter) {
        super(TowerName, counter);

        damage = 0;
        cost = 60;
        frequency = 200;
    }

    @Override
    public int upgrade(int Gold) {
        int d = 0, r = 0, f, c;

        switch (rank)//Ice slow
        {
            case 1:
                f = -30;
                c = 15;
                break;
            case 2:
                r = 1;
                f = -30;
                c = 60;
                break;
            case 3:
                r = 2;
                f = -60;
                c = 240;
                break;
            default:
                return 0;
        }

        if (Gold >= c) {
            damage = damage + d;
            range = range + r;
            frequency = frequency + f;
            rank++;
            return c; //return cost
        } else
            return 0;
    }
}
