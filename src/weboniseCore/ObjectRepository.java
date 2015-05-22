package weboniseCore;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ObjectRepository {

	private static Element root = null;
	private static NodeList tcNode = null;

	public void initialize() {
		System.out.println("Initializing object repo");
		File file = new File(Configuration.objRepo);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc;
			doc = db.parse(file);
			doc.getDocumentElement().normalize();
			root = doc.getDocumentElement();
			System.out.println(" Root node is :  " + root.getNodeName());
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setTCNode(String tcName) {

		// System.out.println("TC NAME IS " + tcName);

		tcNode = root.getElementsByTagName(tcName);
		// System.out.println("TC Node is "+ tcNode.toString());
	}

	/*public String getObjIDByDynamic(String objName, Integer num){
		String objString =objName.replaceFirst(Pattern.matches("li[${num}]"),Integer.toString(num));
		System.out.println("objString "+objString);
		return objString;
	}*/

	public String getObjID(String objName) {
		// System.out.println("VAR NAME IS " + objName);
		NodeList varList = tcNode.item(0).getChildNodes();

		for (int j = 0; j < varList.getLength(); j++) {

			Node varNode = varList.item(j);
			if (varNode.getNodeName() != "#text") {
				if (varNode.getNodeName().equals(objName)) {
					// System.out.println("Return Value is - " +
					// varNode.getTextContent());
					return varNode.getTextContent();
				}

			}
		}
		System.out.println("Return Value is - Invalid tcName or var name");
		return "Invalid tcName or var name";
	}

}
