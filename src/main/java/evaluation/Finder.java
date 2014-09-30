package evaluation;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Finder {
	
	public static File findFileInResultsSet (File goldenStadard, File[] resultSet){
		
		File found = null;
		for(File resultFile: resultSet){
			if (goldenStadard.getName().equals(resultFile.getName()))
				return resultFile;
		}
		return found;
		
	}
	
	public static boolean findIdentifiers (Document resultDoc, String category,String startOffset,
			String endOffset, String token){
		NodeList categorNodes = resultDoc.getElementsByTagName(category);
		for (int i = 0; i < categorNodes.getLength(); i++){
			Node node = categorNodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) node;
				
				//different attributes
				String startOffsetResult = element.getAttribute("start");
				String endOffsetResult = element.getAttribute("end");
				String tokenResult = element.getAttribute("text");
				if (startOffset.equals(startOffsetResult) && endOffset.equals(endOffsetResult) && token.equals(tokenResult)){
					return true;
												
					
				}
			}
		}
		return false;
	}

}
