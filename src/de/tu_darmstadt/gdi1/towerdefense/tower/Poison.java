package de.tu_darmstadt.gdi1.towerdefense.tower;

public class Poison extends Tower {
    /**
     * @param TowerName is the Type of Tower
     * @param counter   is the Number of createt Tower
     */
    public Poison(char TowerName, int counter) {
        super(TowerName, counter);

        damage = 1;
        cost = 45;
        frequency = 200;
    }

    @Override
    public int upgrade(int Gold) {
        int d = 0, r = 0, f = 0, c = 0;

        switch (rank)//Poison Fernkampf
        {
            case 1:
                r = 1;
                f = -10;
                c = 15;
                break;
            case 2:
                r = 2;
                f = -40;
                c = 60;
                break;
            case 3:
                d = 1;
                r = 2;
                f = -60;
                c = 240;
                break;
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
