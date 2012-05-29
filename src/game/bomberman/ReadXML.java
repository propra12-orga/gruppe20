package game.bomberman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ReadXML {

	// bgList speichert die Pfad der background,die von XML gelesen hat
	private List<String> bgList = new ArrayList<String>();
	// obList speichert die Pfad der obstruction,die von XML gelesen hat
	private List<String> obList = new ArrayList<String>();
	// ppList speichert die Pfad der player,die von XML gelesen hat
	private List<String> ppList = new ArrayList<String>();

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

			// wenn background bg ist,dann tun wir hreinin BackgroundList
			if (id.equals("bg"))
				this.bgList.add(path);
			else if (id.equals("ob"))
				this.obList.add(path);
			else if (id.equals("pp"))
				this.ppList.add(path);

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
