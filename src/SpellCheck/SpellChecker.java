package SpellCheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;

public class SpellChecker {
	
	// Calculate the distance between two words
	public static int editDistance(String str1, String str2) {
		int w1Len = str1.length();
		int w2Len = str2.length();
		int[][] arr1 = new int[w1Len + 1][w2Len + 1];
		for (int i = 0; i <= w1Len; i++) {
			arr1[i][0] = i;
		}
		for (int j = 0; j <= w2Len; j++) {
			arr1[0][j] = j;
		}
		for (int k = 0; k < w1Len;k++) {
			char c1 = str1.charAt(k);
			for (int l = 0; l < w2Len; l++) {
				char c2 = str2.charAt(l);
				if (c1 == c2) {
					arr1[k + 1][l + 1] = arr1[k][l];
				} else {
					int replace = arr1[k][l] + 1;
					int insert = arr1[k][l + 1] + 1;
					int delete = arr1[k + 1][l] + 1;

					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					arr1[k + 1][l + 1] = min;
				}
			}
		}
		return arr1[w1Len][w2Len];
	}
	
	// Checks the Spelling of each word in the HTML files and matches them with the dictionary words available in words.txt.
	// Print out the suggestion for the incorrect words
	public static void SpellCheck(String s) throws IOException {
	String strWord = s.toLowerCase(); 
	int wlength = strWord.length();
	String str2 = "src/SpellCheck/words.txt";
	BufferedReader  reader = null;
	 try {
         reader  = new BufferedReader(new FileReader(str2));
         String file;
         int flag = 0;
         String suggWord = "";
         while ((file = reader.readLine()) != null) {
        	 
            int diff = editDistance(strWord,file);
            int count = 0;
            if (strWord == "") {
            	flag = 1;
            }
            if(diff == 0) {
            	flag = 1;
            }
            else {
            	if (wlength == file.length() && (diff == 1)){
            		suggWord = suggWord + " \n " + file;
            		count = count + 1;
            	}
            }
         }
         
         if ((flag == 0) && suggWord != "") {
        	 System.out.println("****************************************************************");
             	System.out.println("The word "+strWord+ " is incorrect. " + "\nDid you mean?: "+ suggWord + " ");
         }
         }
         finally {
	                reader.close();
	            }
	}	

	public static void showSpellCheckResult() throws IOException, FileNotFoundException {
		   
        File htmlFiles = new File("ConvertedTextFiles");
        File[] fileArray = htmlFiles.listFiles();
       
          
           for(int i = 0; i < 5; i++) {
               if(fileArray[i].isFile() ) {
                   File file = new File("ConvertedTextFiles/" + fileArray[i].getName());
                   org.jsoup.nodes.Document doc = Jsoup.parse(file, "UTF-8");
                  String format = doc.text();
                  //System.out.println("output is : " + format);
                  String[] inp = format.split(" ");
                  for (int j = 0; j < inp.length; j ++) {
                      String word = (inp[j].replaceAll("[^a-zA-Z]", "")).toLowerCase();
                      SpellCheck(word);
                    
                 
                  }
              }
   }
   }
	

}
