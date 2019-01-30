package de.tu_darmstadt.gdi1.towerdefense.classes;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import de.tu_darmstadt.gdi1.towerdefense.exceptions.SyntaxNotCorrectException;
import de.tu_darmstadt.gdi1.towerdefense.monster.Monster;
import de.tu_darmstadt.gdi1.towerdefense.tower.Tower;
import de.tu_darmstadt.gdi1.towerdefense.ui.GameState;
import de.tu_darmstadt.gdi1.towerdefense.ui.MainMenuState;

/**
 * @author ErrOR
 */
public class GameEngine {
    //>>>>>Constructor>>>
    private int Level;
    private int LifePoints;
    public int Gold;
    public char[][] GameMap;

    //>>>>>Game>>>>>>>>>>
    public int StartPoints = 0;
    public int[][] Start; //= new int [2][1]
    private char[][] Way; // einmal weg in array, Monster steps zählen und an die position im way weiter
    public int NumbMonster = 0; //Counter
    public int Wave = 0; //Starts with wave 1


    //>>>>>Monster&Tower>
    public static Map<String, Monster> monsterMap = new HashMap<>();
    public static Map<String, Tower> towerMap = new HashMap<>();
    private int counterDelay = 0;
    public int counterT = 0;

    /**
     * Constructor
     *
     * @param Level      is the difficult grade
     * @param LifePoints Lifepoints of player
     * @param Gold       Money of player
     * @param map        map to be loded
     */
    public GameEngine(int Level, int LifePoints, int Gold, String map) //Constructor
    {
        ParsMap parser = new ParsMap();

        this.Level = Level;
        this.LifePoints = LifePoints;
        this.Gold = Gold;
        this.GameMap = parser.reading(map);

        try {
            parser.arrayToGuiObject();
        } catch (SyntaxNotCorrectException e) {
            e.printStackTrace();
        }
        StartPoints = parser.startPointsCounter;

    }

    /**
     * @param LifePoints life points of player
     * @param Gold       money of player
     */
    public GameEngine(int LifePoints, int Gold) //Constructor
    {
        this.LifePoints = LifePoints;
        this.Gold = Gold;
    }

    //Getter>>>>>>>>>>>>>

    /**
     * @return difficult grade
     */
    public double get_Level() //returns Level
    {
        return Level;
    }

    /**
     * @return Lifepoints of player
     */
    public int get_LifePoints()  //returns LifePoints
    {
        return LifePoints;
    }

    /**
     * @return money of player
     */
    public int get_Gold() //returns Gold
    {
        return Gold;
    }

    /**
     * @return Map of all visible elements on map
     */
    public Map<String, Point> getGuiMap() {
        return GuiObject.getGuiMap();
    }
    //<<<<<<<<<<<<<Getter

    //Setter>>>>>>>>>>>>>

    /**
     * @param lp Lifepoints
     */
    public void set_LifePoints(int lp) //returns Level
    {
        LifePoints = lp;
    }
    //<<<<<<<<<<<<<Setter

    /**
     * Checks start and creats Start Points for certain map
     */
    private void checkStart() //Finds Start Points in an array and saves start Points
    {
        Start = new int[2][StartPoints];
        int StartCount = StartPoints - 1;


        for (int y = 0; y < GameMap.length; y++) {
            for (int x = 0; x < GameMap[0].length; x++) {
                if (GameMap[y][x] == 'S') {
                    Start[0][StartCount] = y;
                    Start[1][StartCount] = x; //Start[][], saves the position of Start Points!
                    StartCount--;
                    for (int i = 0; i <= StartPoints; i++) {
                        //Changes the Start 'S' into the right road symbol, so the move method can move monsters
                        if ((GameState.Map.containsKey("start_east" + i))) {
                            GameMap[y][x] = '>';
                        } else if (GameState.Map.containsKey("start_south" + i)) {
                            GameMap[y][x] = 'v';
                        } else if (GameState.Map.containsKey("start_west" + i)) {
                            GameMap[y][x] = '<';
                        } else if (GameState.Map.containsKey("start_north" + i)) {
                            GameMap[y][x] = '^';
                        }
                    }
                } else if (GameMap[y][x] == 't') {
                    setTower(y, x, 'H');
                    Gold += 30;
                }
            }
        }
    }

    /**
     * creates Road, which the monsters will move
     */
    public void makeRoad() {
        char[][] tempWay = new char[StartPoints][100]; //Because array needs certain size


        for (int z = 0; z < StartPoints; z++) //Creates Road for -ALL- Starts
        {
            int y = Start[0][z];
            int x = Start[1][z];
            int step = 0;
            char sign;
            boolean stop = false;


            do //Build tempWay array with road
            {
                sign = GameMap[y][x];
                tempWay[z][step] = sign;

                switch (sign) {
                    case '>':
                        x++;
                        break;
                    case 'v':
                        y++;
                        break;
                    case '<':
                        x--;
                        break;
                    case '^':
                        y--;
                        break;
                    case 'X':
                        stop = true;
                        break;    //If 'X' found stop road
                    default:
                }
                step++;
            } while (!stop);

            //Builds road with tempWay for certain Resolution
            int picSize = GuiObject.pictureSize;
            if (z == 0) Way = new char[StartPoints][step * picSize];

            for (int p = 0; p < step; p++) {
                sign = tempWay[z][p];

                if (sign != 'X') {
                    for (int c = 0; c < picSize; c++) {
                        Way[z][(p * picSize) + c] = sign;
                    }
                } else Way[z][(p * picSize)] = 'X';
            }

        }
    }

    /**
     * @param life   Creats monster with lifepoints
     * @param speed  Creats monster with speed
     * @param profit Creats monster with money to be earned after kill
     */
    public void StartMonster(int life, int speed, int profit) //Creates 1 "NumbMonster" monster & GuiObject @ random start
    {
        NumbMonster++; //Starts a new Monster, so NumbMonster+1
        String monsterX = "monster" + NumbMonster;
        Random rand = new Random();
        int randStart = 0;
        int randMove = rand.nextInt(10); //TODO
        if (StartPoints > 1) //If there is more than 1 start point = random Start
        {
            randStart = rand.nextInt(StartPoints);
            new GuiObject(monsterX, Start[1][randStart], Start[0][randStart]);
            monsterMap.put(monsterX, new Monster(monsterX, life, speed, profit, randStart));
            GuiObject.GuiObjectMap.get(monsterX).translate(0, -2 * randMove); //TODO

        } else
            new GuiObject(monsterX, Start[1][randStart], Start[0][randStart]);
        monsterMap.put(monsterX, new Monster(monsterX, life, speed, profit, randStart));

    }

    /**
     * Starts new Wave with static number of monster
     */
    public void NewWave() {
        Random rand;
        rand = new Random();
        int life;
        int speed;
        int profit;
        Wave++;

        if (Wave < 11) {
            switch (Wave) {
                case 1:
                    life = 1;
                    speed = 8;
                    profit = 10;
                    break;
                case 2:
                    life = 2;
                    speed = 8;
                    profit = 14;
                    break;
                case 3:
                    life = 4;
                    speed = 6;
                    profit = 16;
                    break;
                case 4:
                    life = 6;
                    speed = 6;
                    profit = 18;
                    break;
                case 5:
                    life = 12;
                    speed = 6;
                    profit = 24;
                    break;
                case 6:
                    life = 10;
                    speed = 4;
                    profit = 22;
                    break;
                case 7:
                    life = 12;
                    speed = 4;
                    profit = 24;
                    break;
                case 8:
                    life = 14;
                    speed = 2;
                    profit = 26;
                    break;
                case 9:
                    life = 20;
                    speed = 2;
                    profit = 100;
                    break;
                case 10:
                    life = 100;
                    speed = 2;
                    profit = 100;
                    break;
                default:
                    life = 1;
                    speed = 9;
                    profit = 1;
                    break;
            }
            //Level Anpassung
            life = life * Level;
            if (Level == 2) speed = speed / Level;

            for (int numb = 0; numb < 3 * Wave; numb++) //Starts number of Monsters for certain waves
            {
                int speedX = rand.nextInt(speed);
                StartMonster(life, speedX + 1, profit);
            }
        }
    }

    /**
     * Moves all monster along the Way[][]
     */
    public void moveAllMonster() //Moves all GuiObjects Monsters from the current position
    {
        for (int n = NumbMonster; n > 0; n--) {
            String monsterX = "monster" + n;
            if (GuiObject.GuiObjectMap.containsKey(monsterX)) {

                if (counterDelay % monsterMap.get(monsterX).getSpeed() == 0) {
                    char p = Way[monsterMap.get(monsterX).getStart()][monsterMap.get(monsterX).getSteps()];//Sets char from map of current GuiObject
                    monsterMap.get(monsterX).steps++;

                    switch (p) {
                        case 'v':
                            GuiObject.GuiObjectMap.get(monsterX).translate(0, 1);
                            break;

                        case '>':
                            GuiObject.GuiObjectMap.get(monsterX).translate(1, 0);
                            break;

                        case '<':
                            GuiObject.GuiObjectMap.get(monsterX).translate(-1, 0);
                            break;

                        case '^':
                            GuiObject.GuiObjectMap.get(monsterX).translate(0, -1);
                            break;

                        case 'X':
                            MonsterFinish(monsterX);
                            break; //Monster finished

                        default:
                            break;  //TODOHier könnte man einbauen, dass monster auf dem gras nicht laufen können
                    }
                }
            }
        }
        counterDelay++;
    }

    /**
     * @param name Removes monster if it reached finish
     */
    private void MonsterFinish(String name) {
        LifePoints--;    //LifePoints -1
        GuiObject.GuiObjectMap.remove(name);
        GameEngine.monsterMap.remove(name);
    }

    /**
     * Creates a tower visible on map and object
     *
     * @param y    y coordinate for tower
     * @param x    x coordinate for tower
     * @param name name of tower
     */
    public void setTower(int y, int x, char name) //Sets a Tower in GuiMap and creates object tower in towerMap
    {
        new GuiObject("tower" + counterT, x, y);
        towerMap.put("tower" + counterT, new Tower(name, counterT));
        Gold = Gold - towerMap.get("tower" + counterT).getCost();
        counterT++;
    }

    /**
     * Sells a tower
     *
     * @param name Name of tower to be selled
     */
    public void SellTower(String name) {
        Gold = Gold + (towerMap.get(name).getCost() / 2); //half of the cost will be returned
        GuiObject.GuiObjectMap.remove(name);
        towerMap.remove(name);
    }

    /**
     * Upgrade Tower Stats
     *
     * @param name name of tower
     */
    public void UpgradeTower(String name) //Upgrade for 50Gold
    {
        Gold = Gold - towerMap.get(name).upgrade(Gold);


    }

    /**
     * Shoot all towers
     *
     * @return if tower has shot
     */
    public boolean shootTower()//Checks if a tower shoots a monster
    {
        boolean shoot = false;
        for (int t = 0; t <= counterT; t++) {
            if (towerMap.containsKey("tower" + t)) {

                if (counterDelay % towerMap.get("tower" + t).getFrequency() == 0) //ticker tower shoot
                {
                    Tower OtowerX = towerMap.get("tower" + t);
                    OtowerX.setStatus(true); //can tower shoot??
                    if (OtowerX.getStatus()) {
                        GuiObject.GuiObjectMap.remove("hit" + t);

                        for (int m = 1; m <= NumbMonster; m++) //Tower is able to shoot--is there a monster?
                        {
                            String monsterX = "monster" + m;
                            if (monsterMap.containsKey(monsterX)) {
                                Monster OmonsterX = monsterMap.get(monsterX);
                                double rangeX = OtowerX.getrange() * GuiObject.pictureSize;
                                char type = OtowerX.type;
                                double distance = Math.abs(Point.distance(GuiObject.getmidX(monsterX), GuiObject.getmidY(monsterX), GuiObject.getmidX("tower" + t), GuiObject.getmidY("tower" + t)));

                                if (rangeX + 15 > distance) //Shoot Monster>>>>>>>>>>>>>>>>>
                                {
                                    shoot = true;
                                    OtowerX.setStatus(false);

                                    //GuiObject.GuiObjectMap.put("bullet"+m, new Point(towerx, towery)); TODO
                                    GuiObject.GuiObjectMap.put("hit" + t, new Point((int) GuiObject.GuiObjectMap.get(monsterX).getX(), (int) GuiObject.GuiObjectMap.get(monsterX).getY()));//HITPIC
                                    m = NumbMonster + 1;//Nur eins wird angegriffen

                                    switch (type) {
                                        case 'i':
                                            if (OmonsterX.slowSpeed()) shoot = false;
                                            break;

                                        case 'p':
                                            OmonsterX.durable = true;

                                        case 't':
                                    }
                                    OmonsterX.updateLifepoints(-OtowerX.getDamage());
                                    if (OmonsterX.getLifepoints() < 1) {
                                        killmonster(monsterX);
                                    }
                                } else {
                                    if (type == 'p' && OmonsterX.durable) {
                                        OmonsterX.updateLifepoints(-1);
                                        if (OmonsterX.getLifepoints() <= 0) killmonster(monsterX);
                                    }

                                }
                            }//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Shoot Monster

                        }
                    }
                }
            }
        }
        return shoot;
    }

    /**
     * Kills monster and removes objects
     *
     * @param monsterX name of monster to be killed
     */

    private void killmonster(String monsterX) {
        String n = monsterX.substring(monsterX.indexOf("r") + 1);
        GuiObject.GuiObjectMap.remove("hit" + n);
        Gold = Gold + monsterMap.get(monsterX).getProfit();
        monsterMap.remove(monsterX);
        GuiObject.GuiObjectMap.remove(monsterX);
    }

    /**
     * Tells if game is lost
     *
     * @return lost true,
     */
    public boolean GameLost() {
        if (LifePoints <= 0) {
            GuiObject.GuiObjectMap.clear();
            monsterMap.clear();
            return true;
        } else return false;
    }

    /**
     * Tells if game is won
     *
     * @return true if won
     */
    public boolean GameWon() {
        //--> wenn alle 10 wellen vorbei sind
        return Wave == 11;
    }

    /**
     * Tells if wave is over
     *
     * @return wave over true
     */
    public boolean WaveOver() {
        if (monsterMap.isEmpty()) {
            Gold = Gold + (10 * Wave); //Wave over Bonus
            NumbMonster = 0; //Monster Number reset
            return true;
        } else return false;
    }

    /**
     * Resets the whole game
     */
    public void resetAll() {
        MainMenuState.game = null;
        GameEngine.monsterMap.clear();
        GameEngine.towerMap.clear();
        NumbMonster = 0;
        GuiObject.GuiObjectMap.clear();
        ParsMap.startcounter = 0;
        GameState.Map.clear();
        GameState.Objects.clear();
        GameState.gameLost = false;
        GameState.gameWon = false;
        GameState.sound.stop();
    }

    /**
     * Starts the game and check Starts and make road for monsters
     */
    public void StartGame() {
        GuiObject.GuiObjectMap.clear();
        checkStart();
        makeRoad();
    }

    /**
     * Checks start and creat's Start Points for certain map only for Test reason
     */
    public void checkStartTest() //Finds Start Points in an array and saves start Points
    {
        Start = new int[2][StartPoints];
        int StartCount = StartPoints - 1;

        for (int y = 0; y < GameMap.length; y++) {
            for (int x = 0; x < GameMap[0].length; x++) {
                if (GameMap[y][x] == 'S') {
                    Start[0][StartCount] = y;
                    Start[1][StartCount] = x; //Start[][], saves the position of Start Points!
                    for (int i = 0; i <= ParsMap.startcounter; i++) {
                        //Changes the Start 'S' into the right road symbol, so the move method can move monsters
                        if ((GuiObject.GuiObjectMap.containsKey("start_east" + i))) {
                            GameMap[y][x] = '>';
                        } else if (GuiObject.GuiObjectMap.containsKey("start_south" + i)) {
                            GameMap[y][x] = 'v';
                        } else if (GuiObject.GuiObjectMap.containsKey("start_west" + i)) {
                            GameMap[y][x] = '<';
                        } else if (GuiObject.GuiObjectMap.containsKey("start_north" + i)) {
                            GameMap[y][x] = '^';
                        }
                    }
                } else if (GameMap[y][x] == 't') {
                    setTower(y, x, 'H');
                    Gold += 30;
                }
            }
        }
    }

}

