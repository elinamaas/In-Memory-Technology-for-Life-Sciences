package parser;
public class Preprocessing {
	
	public static StringBuilder replaceApostrophe (StringBuilder text){
		
		//replace apostrophe to white space, but not in files
		for (int index = 0; index < text.length(); index++) {
		    if (text.charAt(index) == '\'') {
		        text.setCharAt(index, ' ');
		    }
		}
		return text;
		
	}

}
