package evaluation;

public class EvaluatedToken {
	
	String category;
	String id;
	int startOffset;
	int endOffset;
	String token;
	String tokenType;
	String id_doc;
	
	EvaluatedToken(String categoryToken, String idToken, int startOffsetToken, int endOffsetToken, String text, String type, String idDoc){
		
		category = categoryToken;
		id= idToken;
		startOffset = startOffsetToken;
		endOffset = endOffsetToken;
		token= text;
		tokenType = type;
		id_doc = idDoc;
		
	}
	
	public String getIdDoc (){
		return this.id_doc;
	}
	public String getCategory (){
		return this.category;
	}
	
	public String getId (){
		return this.id;
	}
	
	public int getStartOffset (){
		return this.startOffset;
	}
	
	public int getEndOffset(){
		return this.endOffset;
	}
	
	public String getToken(){
		return this.token;
	}
	
	public String getTokenType(){
		return this.tokenType;
	}
	
	public void updateToken(String newToken){
		this.token = newToken;
	}
	
	public void updateStart (int newStartOffset){
		this.startOffset = newStartOffset;
	}
	
	public void updateEnd (int newEndOffset){
		this.endOffset = newEndOffset;
	}
	
	public void updateType (String type){
		this.tokenType = type;
	}
}
