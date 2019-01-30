package de.tu_darmstadt.gdi1.towerdefense.tower;

public class Shortrange extends Tower {
    /**
     * @param TowerName is the Type of Tower
     * @param counter   is the Number of createt Tower
     */
    public Shortrange(char TowerName, int counter) {
        super(TowerName, counter);

        damage = 1;
        cost = 30;
        frequency = 100;
    }

    @Override
    public int upgrade(int Gold) {
        int d = 0, r = 0, f = 0, c = 0;

        switch (rank)//Tower Normal Nahkampf
        {
            case 1:
                d = 1;
                f = -10;
                c = 15;  //1. Upgrade
                break;
            case 2:
                d = 2;
                f = -20;
                c = 60; //2. Upgrade
                break;
            case 3:
                d = 2;
                r = 1;
                f = -40;
                c = 120; //3. Upgrade
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
