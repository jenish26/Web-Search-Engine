package PatternFind;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;


//***************referenced libraries: jsoup-1.13.1.jar
public class FindPatterns {

	public static void PatternFinding(String wordPatternString) throws IOException {

		//String filePath = "W3C Web Pages/Text";
		String filePath = "ConvertedTextFiles";
		File fileFolder = new File(filePath); // file path
		File[] allFiles = fileFolder.listFiles();
		//&& fileText.equals(allFiles[i].getName())
		for(int i = 0; i < allFiles.length; i++) {
			if(allFiles[i].isFile() ) {
				int count = 0;
				File file = new File(filePath + "/" + allFiles[i].getName());
				org.jsoup.nodes.Document doc = Jsoup.parse(file, "UTF-8");
				Pattern StringSearchingPattern = Pattern.compile(wordPatternString);
				Matcher MatchStringSearching = StringSearchingPattern.matcher(doc.text());
				
				while (MatchStringSearching.find()) {
					count += 1;
					System.out.println("Output :" + MatchStringSearching.group()+"\nPosition :"+ MatchStringSearching.start() + "\n" + "In File: " + allFiles[i].getName()+"\n");
				}
				if(count == 0) {
					System.out.println("\nDidn't find the pattern in file: " + allFiles[i].getName());
				} else {
					count = 0;
				}
			
				
			}
			
		}
	}

	public static void showPatterns() {
		// TODO Auto-generated method stub
				try {
					
					//choose to search
					
					String email = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+";
					String phoneNumber = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
					String website = "((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+";
					
					
					int loop = 1;
					while (loop == 1) {
						System.out.println("\nWhat do you want to search? Please input the number : ");
						System.out.println("1. email    2. phone number    3. website    4. other(you need to enter relugar expression by yourself)");
						@SuppressWarnings("resource")
						Scanner inputNumberScanner = new Scanner(System.in);
						String inputNumberString = inputNumberScanner.nextLine();
						int answer = Integer.parseInt(inputNumberString);
						
						
						if(answer == 1) {
							PatternFinding(email);
							System.out.println("\nDo you want to search again? If yes, input y; If no,input n");
							@SuppressWarnings("resource")
							Scanner loopScanner = new Scanner(System.in);
							String loopString = loopScanner.nextLine();
							if (loopString.equals("y")) {
								loop = 1;
							}else {
								loop = 0;
							}
							
						}else if (answer == 2) {
							PatternFinding(phoneNumber);
							System.out.println("\nDo you want to search again? If yes, input y; If no,input n");
							@SuppressWarnings("resource")
							Scanner loopScanner = new Scanner(System.in);
							String loopString = loopScanner.nextLine();
							if (loopString.equals("y")) {
								loop = 1;
							}else {
								loop = 0;
							}
						}else if (answer ==3) {
							PatternFinding(website);
							System.out.println("\nDo you want to search again? If yes, input y; If no,input n");
							@SuppressWarnings("resource")
							Scanner loopScanner = new Scanner(System.in);
							String loopString = loopScanner.nextLine();
							if (loopString.equals("y")) {
								loop = 1;
							}else {
								loop = 0;
							}
						}else if (answer == 4) {
							@SuppressWarnings("resource")
							Scanner searchword = new Scanner(System.in);
							System.out.println("\nInput the word you want to search(with regular expression) : ");
							String wordPatternString = searchword.nextLine(); // get word which want to search
							PatternFinding(wordPatternString);
							System.out.println("\nDo you want to search again? If yes, input y; If no,input n");
							@SuppressWarnings("resource")
							Scanner loopScanner = new Scanner(System.in);
							String loopString = loopScanner.nextLine();
							if (loopString.equals("y")) {
								loop = 1;
							}else {
								loop = 0;
							}
						}else {
							System.out.println("please choose from 1,2,3,4");
							System.out.println("\nDo you want to search again? If yes, input y; If no,input n");
							@SuppressWarnings("resource")
							Scanner loopScanner = new Scanner(System.in);
							String loopString = loopScanner.nextLine();
							if (loopString.equals("y")) {
								loop = 1;
							}else {
								loop = 0;
							}
						}
					}
					
										
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				

			

	}
	

}
