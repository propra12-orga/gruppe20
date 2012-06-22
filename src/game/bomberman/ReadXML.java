package game.bomberman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * liest ein Level aus einer XML Datei ein
 * 
 * @author timozjx, KingManuel
 * 
 */
public class ReadXML {

	// bgList speichert die Pfad der background,die von XML gelesen hat
	private List<String> bgList = new ArrayList<String>();
	// obList speichert die Pfad der obstruction,die von XML gelesen hat
	private List<String> obList = new ArrayList<String>();
	// ppList speichert die Pfad der player,die von XML gelesen hat
	private List<String> ppList = new ArrayList<String>();
	// stoneX speichert die X-Koordinaten der Steine
	private List<Integer> stoneX = new ArrayList<Integer>();
	// stoneY speichert die Y-Koordinaten der Steine
	private List<Integer> stoneY = new ArrayList<Integer>();
	// boxX speichert die X-Koordinaten der Boxen
	private List<Integer> boxX = new ArrayList<Integer>();
	// boxY speichert die Y-Koordinaten derBoxen
	private List<Integer> boxY = new ArrayList<Integer>();
	// Obstruction speicher alle obstructions
	private List<Obstruction> obs = new ArrayList<Obstruction>();

	public List<Obstruction> getObs() {
		return obs;
	}

	public void setObs(List<Obstruction> obs) {
		this.obs = obs;
	}

	public List<Integer> getStoneX() {
		return stoneX;
	}

	public void setStoneX(List<Integer> stoneX) {
		this.stoneX = stoneX;
	}

	public List<Integer> getStoneY() {
		return stoneY;
	}

	public void setStoneY(List<Integer> stoneY) {
		this.stoneY = stoneY;
	}

	public List<Integer> getBoxX() {
		return boxX;
	}

	public void setBoxX(List<Integer> boxX) {
		this.boxX = boxX;
	}

	public List<Integer> getBoxY() {
		return boxY;
	}

	public void setBoxY(List<Integer> boxY) {
		this.boxY = boxY;
	}

	/**
	 * the constructor to initial the member variables,the member variables put
	 * the url of the backgound,obstructions
	 * 
	 */

	public ReadXML() {
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		try {
			doc = sb.build(ReadXML.class.getClassLoader().getResourceAsStream(
					"background.xml"));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Root config
		Element root = doc.getRootElement();
		// Children config
		List list = root.getChildren("background");
		for (int i = 0; i < list.size(); i++) {
			// erhalte jede Background,die mit Parameter id,url

			Element element = (Element) list.get(i);
			String id = element.getAttributeValue("id");
			String url = element.getAttributeValue("url");
			String path = System.getProperty("user.dir") + url;

			// wenn background bg ist,dann legen wir in BackgroundList
			if (id.equals("bg"))
				this.bgList.add(path);
			else if (id.equals("ob"))
				this.obList.add(path);
			else if (id.equals("pp"))
				this.ppList.add(path);

		}

	}

	/**
	 * initialisire die Koordinaten der Obstruction
	 */
	public void initObLocation() {

		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		try {
			doc = sb.build(ReadXML.class.getClassLoader().getResourceAsStream(
					"obstruction.xml"));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// root config
		Element root = doc.getRootElement();
		// children config
		List list = root.getChildren("ob");
		for (int i = 0; i < list.size(); i++) {

			Element element = (Element) list.get(i);
			String id = element.getAttributeValue("id");
			String x = element.getAttributeValue("x");
			String y = element.getAttributeValue("y");
			Integer x1 = new Integer(x);
			Integer y1 = new Integer(y);

			if (id.equals("stone")) {

				// this.stoneX.add(x1);
				// this.stoneY.add(y1);
				Obstruction ob = new Obstruction(x1, y1, 0);
				ob.setType(0);
				obs.add(ob);
			}

			else if (id.equals("box")) {

				// this.boxX.add(x1);
				// this.boxY.add(y1);
				Obstruction ob = new Obstruction(x1, y1, 1);
				ob.setType(1);
				obs.add(ob);

			}

		}

	}

	/**
	 * return a list of player image url string
	 * 
	 */
	public List<String> getPpList() {
		return ppList;
	}

	/**
	 * setter
	 * 
	 */
	public void setPpList(List<String> ppList) {
		this.ppList = ppList;
	}

	/**
	 * return a list of obstruction image url string
	 * 
	 */
	public List<String> getObList() {
		return obList;
	}

	/**
	 * setter
	 * 
	 */
	public void setObList(List<String> obList) {
		this.obList = obList;
	}

	/**
	 * return a list of background image url string
	 * 
	 */
	public List<String> getBgList() {
		return bgList;
	}

	/**
	 * setter
	 * 
	 */
	public void setBgList(List<String> bgList) {
		this.bgList = bgList;
	}

	/*
	 * Test public static void main(String[] args) { try { ReadXML obj = new
	 * ReadXML(); String id = obj.getBgList().get(1); System.out.println(id); }
	 * catch (Exception e) { e.printStackTrace(); } }
	 */
}
