package de.tu_darmstadt.gdi1.towerdefense.tower;

public class Default extends Tower {
    /**
     * @param TowerName is the Type of Tower
     * @param counter   is the Number of createt Tower
     */
    public Default(char TowerName, int counter) {
        super(TowerName, counter);

        damage = 1;
        frequency = 1;
        cost = 30;
    }

    @Override
    public int upgrade(int Gold) {
        return 0;
    }
}
