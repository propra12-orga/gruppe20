package game.bomberman;

/**
 * Items werden zufällig erzeugt wenn eine Box zerstoert wird. Tritt ein Spieler
 * auf ein offen liegendes Item verschwindet es und veraendert den Spieler auf
 * verschiedene Arten. Items haben verschiedene Typen: 1:=erhoet
 * BombenKapazitaet um 1 2:=erhoet BombenRadius um 1
 * 
 * @author HC
 * 
 */
public class Item {
	// Koordinaten
	private int x;
	private int y;
	/**
	 * Item wird gezeichnet wenn ItemShow true ist
	 */
	private boolean ItemShow;

	/**
	 * Um welche Art von Item handelt es sich? 1:=BombCapacity Up 2:=BombRadius
	 * Up
	 * 
	 */
	private int type;

	public Item(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.ItemShow = false;
	}

}
