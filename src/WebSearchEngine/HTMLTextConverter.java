package WebSearchEngine;
import java.io.*;

import org.jsoup.Jsoup;

public class HTMLTextConverter {
	
	// This method converts HTML Files into text documents.
	public static void convertHtmlToText() throws IOException, FileNotFoundException, NullPointerException {
		org.jsoup.nodes.Document document = null;
		

		try {
			File file = new File("HTMLFiles");
			File f1 = new File("ConvertedTextFiles");	
			//clear the directory before the downloading new file  
			for(File fi: f1.listFiles()) {
				if (!fi.isDirectory()) 
			        fi.delete();
			}
			File[] file_lists = file.listFiles();
			for (File file_path : file_lists) {
				
				document = Jsoup.parse(file_path, "UTF-8");
				 String textString = document.text();
				String file_name = file_path.getName().substring(0, file_path.getName().lastIndexOf('.'));
				BufferedWriter bw = new BufferedWriter(new FileWriter("ConvertedTextFiles/" + file_name + ".txt"));
				bw.write(textString);
				bw.close();
			}

		} catch (Exception e) {
			e.printStackTrace();	
		}

	}

}
