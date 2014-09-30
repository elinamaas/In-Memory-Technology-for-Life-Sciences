//package evaluation;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.stream.XMLEventReader;
//import javax.xml.stream.XMLInputFactory;
//import javax.xml.stream.XMLStreamConstants;
//import javax.xml.stream.XMLStreamException;
//import javax.xml.stream.XMLStreamReader;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import parser.Preprocessing;
//
//public class CompareToGoldenStandard {
//	//return persentage
////	private String goldenStandardPath = "/Users/snownettle/Documents/HPI/4.Semester/MedDocs/testData/test10/";
////	private  String resulFolderPath = "/Users/snownettle/Documents/HPI/4.Semester/MedDocs/results/";
//	private static String goldenStandardPath = "/Users/snownettle/Documents/HPI/4.Semester/MedDocs/testData/testing-PHI-Gold";
//	private static String resulFolderPath = "/Users/snownettle/Documents/HPI/4.Semester/MedDocs/results/";
//	File goldenStandardFolder = new File(goldenStandardPath);
//	File resultFolder = new File (resulFolderPath);
//	
//	public void Evaluation (){
//		File[] listGoldenStandard = goldenStandardFolder.listFiles();
//		File[] listResults = resultFolder.listFiles();
//		for (File fileName: listGoldenStandard){
//			try{
//				File resultFile = Finder.findFileInResultsSet(fileName, listResults);
//				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//				Document goldenStandardDoc = dBuilder.parse(fileName);
//				Document resultDoc = dBuilder.parse(resultFile);
//				
//				goldenStandardDoc.getDocumentElement().normalize();
//				resultDoc.getDocumentElement().normalize();
//				
//				NodeList goldenStandardNodes = goldenStandardDoc.getElementsByTagName("TAGS");
//				
//				for (int i = 0; i < goldenStandardNodes.getLength(); i++){
//					Node node = goldenStandardNodes.item(i);
//					if (node.getNodeType() == Node.ELEMENT_NODE){
//						Element element = (Element) node;
//						NodeList tokList = element.getChildNodes();
//						for (int j = 0; j < tokList.getLength(); j++){
//							Node tokNode = tokList.item(j);
//							if (tokNode.getNodeType() == Node.ELEMENT_NODE){
//								Element tokElement = (Element) tokNode;
//								String category = tokElement.getNodeName();
//								//different attributes
//								String startOffset = tokElement.getAttribute("start");
//								String endOffset = tokElement.getAttribute("end");
//								String token = tokElement.getAttribute("text");
//								if (Finder.findIdentifiers(resultDoc, category, startOffset, endOffset, token)){
//								}
////								System.out.println(token);
//								
//							}
//						}
//					}
//				}
//				
//			}catch (FileNotFoundException e) {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	        }catch (ParserConfigurationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SAXException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}
//}
//  
