package evaluation;

import java.util.Arrays;
import java.util.List;

public class CheckType {
	
	private List<String> typeList = Arrays.asList ("DATE", "MONTH","YEAR", "DAY", "TIME_PERIOD", "TIME",
			"ADDRESS1","ADDRESS2", "COUNTRY", "LOCATION",  "PHONE",  "LOCALITY", "GEO_FEATURE/LAND","URI/EMAIL",
			"PERSON", "TITLE", "PEOPLE",  "REGION/MINOR", "REGION/MAJOR",
			"ORGANIZATION/COMMERCIAL", "ORGANIZATION/MEDIA", "ORGANIZATION/MEDICALSCIENCE", "ORGANIZATION/GOVERNMENT",
			"ORGANIZATION/EDUCATIONAL", "ORGANIZATION/OTHER", "ORGANIZATION/SPORTS", "ORGANIZATION/RELIGIOUS","ORGANIZATION/ENTERTAINMENT");
	private List<String> categoryList = Arrays.asList("NAME", "PROFESSION", "LOCATION", "AGE", "DATE", "IDs", "CONTACT");
	private static List <String> dateList = Arrays.asList("DATE", "MONTH","YEAR", "DAY", "TIME");
	private static List <String> locationList = Arrays.asList("COUNTRY", "LOCATION","ORGANIZATION/COMMERCIAL", "ORGANIZATION/MEDIA", "ORGANIZATION/MEDICALSCIENCE", "ORGANIZATION/GOVERNMENT",
			"ORGANIZATION/EDUCATIONAL", "ORGANIZATION/OTHER", "ORGANIZATION/SPORTS", "ORGANIZATION/RELIGIOUS","ORGANIZATION/ENTERTAINMENT", "ADDRESS1", "ADDRESS2" );
	private static List<String> skipList = Arrays.asList("NOUN_GROUP", "MEASURE", "TIME_PERIOD", "WEAPON/BIOLOGICAL", "GEO_FEATURE/WATER", "PRODUCT", "WEAPON/CHEMICAL", "PERCENT");
	private static List<String> contactList = Arrays.asList("PHONE", "URI/EMAIL");
	public static String convertTypeToCategory (String type){
		String category = "";
		if (dateList.contains(type))
			category = "DATE";
		else if (locationList.contains(type))
			category = "LOCATION";
		else if (skipList.contains(type))
			category = "skip";
		else if (type.equals("PERSON"))
			category = "NAME";
		else if (contactList.contains(type))
			category = "CONTACT";
		else if (type.equals("PHI_id"))
			category = "ID";
		else
			category ="OTHER";
		
		
		return category;
	}

}
