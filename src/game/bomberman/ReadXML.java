package game.bomberman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private String r = "";
	private String obPath = "obstruction1.xml";

	public String getObPath() {
		return obPath;
	}

	public void setObPath(String obPath) {
		this.obPath = obPath;
	}

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

		r = ReadXML.class.getClassLoader().getResource("").toString()
				+ "\\game\\bomberman\\ob\\";

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
	 * Liest die Koordinaten der Obstruction aus XML Datei "Level" ein
	 */
	public void initObLocation(String level) {

		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		try {
			doc = sb.build(ReadXML.class.getClassLoader().getResourceAsStream(
					level));
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

			} else if (id.equals("player1") && Config.select == 1) {
				Config.point1[0] = x1;
				Config.point1[1] = y1;
			} else if (id.equals("player2") && Config.select == 1) {

				MyFrame.doublePlayer = true;
				Config.point2[0] = x1;
				Config.point2[1] = y1;

			} else if (id.equals("door")) {
				Config.dx = x1;
				Config.dy = y1;
				String t = element.getAttributeValue("show");
				Integer show = new Integer(t);
				if (show == 1)
					Config.AusgangShow = true;
				else if (show == 0)
					Config.AusgangShow = false;
				Obstruction ob = new Obstruction(x1, y1, 3);
				ob.setType(3);
				obs.add(ob);

			}
		}
	}

	/**
	 * Liest die Koordinaten der Obstruction aus XML Datei ein Je nach Wert von
	 * Int Config.select wird eine andere xml Datei eingelesen
	 */
	public void initObLocation() {
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		try {
			if (Config.select == 2) {
				obPath = "Mapeditor.xml";
			} else if (Config.select == 0) {

				if (Config.netGame) {
					obPath = "obstruction1.xml";
				} else {
					int ran = new Random().nextInt(4);
					switch (ran) {
					case 0:
						obPath = "obstruction1.xml";
						break;
					case 1:
						obPath = "obstruction2.xml";
						break;
					case 2:
						obPath = "obstruction3.xml";
						break;
					case 3:
						obPath = "obstruction4.xml";
						break;
					}

				}

			} else if (Config.select == 1) {
				obPath = "last.xml";
				System.out.println("continue from last time...");
			}
			File f = new File(System.getProperty("user.dir")
					+ "\\bin\\game\\bomberman\\ob\\last.xml");

			if (f.exists())
				Config.hasSave = true;

			doc = sb.build(r + obPath);
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

				Obstruction ob = new Obstruction(x1, y1, 0);
				ob.setType(0);
				obs.add(ob);
			}

			else if (id.equals("box")) {

				Obstruction ob = new Obstruction(x1, y1, 1);
				ob.setType(1);
				obs.add(ob);

			} else if (id.equals("player1") && Config.select == 1) {
				Config.point1[0] = x1;
				Config.point1[1] = y1;
			} else if (id.equals("player2") && Config.select == 1) {

				MyFrame.doublePlayer = true;
				Config.point2[0] = x1;
				Config.point2[1] = y1;

			} else if (id.equals("door")) {
				Config.dx = x1;
				Config.dy = y1;
				String t = element.getAttributeValue("show");
				Integer show = new Integer(t);
				if (show == 1)
					Config.AusgangShow = true;
				else if (show == 0)
					Config.AusgangShow = false;
				Obstruction ob = new Obstruction(x1, y1, 3);
				ob.setType(3);
				obs.add(ob);

			}

		}

		Config.maxGrade = getMaxGrade();

	}

	/**
	 * Lese Highscore vom gespeicherten Spiel ein
	 * 
	 * @return Int Highscore
	 */
	public static int getMaxGrade() {
		int result = 0;
		String path = System.getProperty("user.dir")
				+ "/bin/game/bomberman/ob/max.txt";
		File f = new File(path);
		BufferedReader br = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
				System.out.println("creat file");
			}
			br = new BufferedReader(new FileReader(f));
			String s = br.readLine();
			if (s != null) {
				result = Integer.parseInt(s);
				System.out.println("d:" + s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("result:" + result);
		return result;
	}

	/**
	 * Speichert Highscore in txt bei speichern des Spiels
	 * 
	 * @param grade
	 */
	public static void saveMaxGrade(int grade) {
		if (grade == 0)
			return;
		String path = System.getProperty("user.dir")
				+ "/bin/game/bomberman/ob/max.txt";
		File f = new File(path);
		FileWriter fw = null;
		try {
			if (!f.exists())
				f.createNewFile();
			fw = new FileWriter(f);
			fw.write(grade + "");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
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
}
