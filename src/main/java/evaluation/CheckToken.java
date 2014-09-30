package evaluation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CheckToken {
	private static List<String> skipList = Arrays.asList("LOCATION", "NAME");
	
	//do not write in evaluation file, if it is too short
	public static boolean checker (EvaluatedToken currentToken){
		if (skipList.contains(currentToken.getCategory()))
			if (currentToken.getToken().length()>3)
				return true;
			else
				return false;
		else
			return true;
		
	}
	
	public static void checkNames (EvaluatedToken currentToken){
		if (currentToken.getCategory().equals("NAME")){
			String token = currentToken.getToken();
			// Starts with
			if (token.matches("(?i)DR.*")){
				String[] newToken = token.split("(?i)DR.");
				String t = newToken[1].trim();
				int difference = token.length() - t.length();
				int newStartOffset = currentToken.startOffset + difference;
				currentToken.updateToken(t);
				currentToken.updateStart(newStartOffset);
				currentToken.updateType("DOCTOR");
			}else if (token.matches("(?i)MR.*")){
				String[] newToken = token.split("(?i)MR.");
				String t=newToken[1].trim();
				int difference = token.length() - t.length();
				int newStartOffset = currentToken.startOffset + difference;
				currentToken.updateToken(t);
				currentToken.updateStart(newStartOffset);
				currentToken.updateType("DOCTOR");
			}
			else if (token.matches("(?i)DR.*")){
				String[] newToken = token.split("(?i)DR.");
				String t=newToken[1].trim();
				int difference = token.length() - t.length();
				int newStartOffset = currentToken.startOffset + difference;
				currentToken.updateToken(t);
				currentToken.updateStart(newStartOffset);
				currentToken.updateType("DOCTOR");
			}else if (token.matches("(?i)MRS.*")){
				String[] newToken = token.split("(?i)MRS.");
				String t = newToken[1].trim();
				int difference = token.length() - t.length();
				int newStartOffset = currentToken.startOffset + difference;
				currentToken.updateToken(t);
				currentToken.updateStart(newStartOffset);
				currentToken.updateType("DOCTOR");
			}
			else if (token.matches("(?i).*m.d.")){
				String[] newToken = token.split("(?i),.m.d.");
				String t = newToken[0].trim();
				int difference = token.length() - t.length();
				int newEndToken = currentToken.endOffset - difference;
				currentToken.updateToken(t);
				currentToken.updateEnd(newEndToken);
				currentToken.updateType("DOCTOR");
				
			}
			else if (token.matches("(?i).*md")){
				String[] newToken = token.split("(?i),.md");
				String t= newToken[0].trim();
				int difference = token.length() - t.length();
				int newEndToken = currentToken.endOffset - difference;
				currentToken.updateToken(t);
				currentToken.updateEnd(newEndToken);
				currentToken.updateType("DOCTOR");
				
			}else{
				currentToken.updateType("PATIENT");
			}
			
		}
		
	}

}
