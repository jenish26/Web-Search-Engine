package FrequencyCount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CalculateAll {
	
	public static void wordCount(String wordtocount) throws FileNotFoundException, IOException {
		
		try {

			File fileFolder = new File("ConvertedTextFiles"); // file path

			File[] allFiles = fileFolder.listFiles();

			for (int i = 0; i < allFiles.length; i++) {
				int count = 0; 
				if (allFiles[i].isFile()) {

					File file = new File("ConvertedTextFiles/" + allFiles[i].getName());


					InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");

					BufferedReader in = new BufferedReader(read);

					StringBuffer findword = new StringBuffer();

					String str = null;

					// integer variable i to count the occurrences of the wordtocount

					while ((str = in.readLine()) != null) {

						findword.append(str);

					}

					Pattern pattern = Pattern.compile(wordtocount);

					Matcher matcher = pattern.matcher(findword);

					while (matcher.find()) {

						count++;

					}

					in.close();

					System.out.println("The wordtocount : " + wordtocount + "\nTotal Count : " + count + " times\nThe file Name : " + allFiles[i].getName() + "\n");

				}
			}
			
		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
}
