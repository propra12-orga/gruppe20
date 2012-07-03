package game.bomberman;

import game.bomberman.thing.BombPoint;
import game.bomberman.thing.Life;

import java.util.List;
import java.util.Vector;

/**
 * Speichert Variabeln
 * 
 * @author timo
 * 
 */
public class Config {

	/**
	 * high score
	 * 
	 */
	public static int maxGrade = 0;

	/**
	 * select default ist 0 fuer a new game select 1 fuer load game
	 * 
	 */
	public static int select = 0;

	/**
	 * net game
	 * 
	 */
	public static boolean netGame = false;
	public static boolean startGame = false;
	/**
	 * 0 fuer start new game1 fuer load gamex1,y1 fuer player1x2,y2 fuer player2
	 * transpotieren die Koordinaten
	 */
	public static int x1 = -100;
	public static int y1 = -100;
	public static int x2 = -100;
	public static int y2 = -100;
	/**
	 * StartPosition Spieler 2
	 */
	public static int point2[] = { 384, 452 };
	/**
	 * StartPosition Spieler 1
	 */
	public static int point1[] = { 0, 68 };

	// tuer
	public static boolean AusgangShow = false;
	/**
	 * Koordinaten des Ausgangs
	 */
	public static int dx = -100;
	public static int dy = -100;

	public static boolean hasSave = false;

	public static boolean computerFight = false;

	/**
	 * maximal monster Anzahl
	 * 
	 */
	public static int maxMonster = 10000;

	public static int allowexist = 5;

	/**
	 * maxmal gleichzeitig monster Anzahl
	 * 
	 */
	public static int existMonster = 0;

	/**
	 * count wie viel Monster exist
	 * 
	 */
	public static int monsterCounter = 0;

	/**
	 * maximal Items gleichzeitig
	 * 
	 */
	public static int max = 3;

	/**
	 * actually Items
	 * 
	 */
	public static int exist = 0;

	/**
	 * bomb Items point
	 * 
	 */
	public static BombPoint bomb = null;

	public static Life life = null;

	public static TimeCounter bugifx = null;

	public static int mapEditor = 0;

	public static List<BombPoint> bombs = new Vector<BombPoint>();

	public static List<Life> lifes = new Vector<Life>();

}
