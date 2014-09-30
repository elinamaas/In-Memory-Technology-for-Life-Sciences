package evaluation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLWriter {
	private String configFile;

	public void setFile(String configFile) {
		this.configFile = configFile;
	}

	public void saveConfig(ResultSet resultList, File testFile) throws Exception {
		String docId = testFile.getName().split(".xml")[0];
		// create an XMLOutputFactory
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		//read XML
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream in = new FileInputStream(testFile.getAbsoluteFile().getPath());
		XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		String text = "";
;		while (eventReader.hasNext()){
			XMLEvent event = eventReader.nextEvent();

	          if (event.isStartElement()) {
	            if (event.asStartElement().getName().getLocalPart().equals("TEXT")) {
	            	event = eventReader.nextEvent();
	            	text = event.asCharacters().getData();
	            	break;
	            }
	          }
		}
		
		// create XMLEventWriter
		XMLEventWriter eventWriter = outputFactory
				.createXMLEventWriter(new FileOutputStream(configFile));
		// create an EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent tabForTag = eventFactory.createDTD("\n\t");
		XMLEvent end = eventFactory.createDTD("\n");
		// create and write Start Tag
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		eventWriter.add(tabForTag);
		
		StartElement startDE = eventFactory.createStartElement("", "", "deIdi2b2");
		eventWriter.add(startDE);
		
		Characters cData = eventFactory.createCData(text);
		StartElement sE = eventFactory.createStartElement("", "", "TEXT");
		eventWriter.add(sE);
//		Characters characters = eventFactory.createCharacters(text);
		eventWriter.add(cData);
		EndElement eE = eventFactory.createEndElement("", "", "TEXT");
		eventWriter.add(eE);

		// create config open tag
		StartElement configStartElement = eventFactory.createStartElement("",
				"", "TAGS");
		eventWriter.add(configStartElement);
		eventWriter.add(end);
		// Write the different nodes
		//forLOOp
		int i = 0;
		while (resultList.next()){
			String id = "P"+i;
			String token = resultList.getString(1);
			String tokenType = resultList.getString(2);
			String offset = resultList.getString(3);
			int startOffset = Integer.parseInt(offset)+3;
			int endOffset = startOffset + token.length();
			String category = CheckType.convertTypeToCategory(tokenType);
			if (category.equals("DATE"))
				tokenType = "DATE";
			EvaluatedToken currentToken = new EvaluatedToken (category, id, startOffset, endOffset, token, tokenType, docId);
			if (!CheckToken.checker(currentToken))
				continue;
			if (category.equals("skip"))
				continue;
			if(currentToken.getCategory().equals("NAME")){
				CheckToken.checkNames(currentToken);
			}
			if (currentToken.getCategory().equals("ID")){
				currentToken.updateType("IDNUM");
			}
			createNode (eventWriter, currentToken);
			i++;
			
		}

		eventWriter.add(tabForTag);
		eventWriter.add(eventFactory.createEndElement("", "", "TAGS"));
		eventWriter.add(end);
		EndElement endDE = eventFactory.createEndElement("","", "deIdi2b2");
		eventWriter.add(endDE);
		
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

	private void createNode(XMLEventWriter eventWriter, EvaluatedToken currentToken) throws XMLStreamException {

		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t\t");
		// create Start node
		StartElement sElement = eventFactory.createStartElement("", "", currentToken.getCategory());
//				eventFactory.createStartElement("", "", tokenType);
		
		eventWriter.add(tab);
		eventWriter.add(sElement);
		//create attributes
		Attribute idAttribute = eventFactory.createAttribute("id", currentToken.getId());
		eventWriter.add(idAttribute);
		Attribute start = eventFactory.createAttribute("start", Integer.toString(currentToken.getStartOffset()));
		eventWriter.add(start);
		
		Attribute endAttribute = eventFactory.createAttribute("end", Integer.toString(currentToken.getEndOffset()));
		eventWriter.add(endAttribute);
		Attribute text = eventFactory.createAttribute("text", currentToken.getToken());
		eventWriter.add(text);
		Attribute type = eventFactory.createAttribute("TYPE", currentToken.getTokenType());
		eventWriter.add(type);
		
		Attribute comment = eventFactory.createAttribute("comment", "");
		eventWriter.add(comment);

		// create End node
		EndElement eElement = eventFactory.createEndElement("", "", currentToken.getCategory());
		eventWriter.add(eElement);
		eventWriter.add(end);

	}

}
