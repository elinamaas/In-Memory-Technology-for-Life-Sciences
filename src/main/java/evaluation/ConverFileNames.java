package evaluation;

import java.io.File;
import java.util.ArrayList;

public class ConverFileNames {
	
	public static ArrayList<String> convert (String path, File[] filesList){
		ArrayList<String> files = new ArrayList<String>();
		for (File fileName: filesList){
			String name = path + fileName.getName();
			files.add(name);
			
		}
		return files;
		
	}

}
