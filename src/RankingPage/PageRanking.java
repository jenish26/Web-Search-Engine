package RankingPage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class PageRanking {


	static Scanner sc = new Scanner(System.in);


	// Finding the positions and occurrences of the words in the files
	public int searchWord(File filePath, String str) throws IOException, NullPointerException {
		int occ_counter = 0;
		String filedata = "";
		
		BufferedReader my_Object = new BufferedReader(new FileReader(filePath));
		String line = null;

		while ((line = my_Object.readLine()) != null) {
			filedata = filedata + line;
		}
		
		String txt = filedata;
		
		SearchingWords SW = new SearchingWords(str);
		
		int offset = 0;
		for (int j = 0; j <= txt.length(); j += offset + str.length()) {
			// search for the word
			offset = SW.search(str, txt.substring(j));
			if ((offset + j) < txt.length()) {
				occ_counter++;
				// printing position of word in the file
				//System.out.println(occ_counter + ") " + str + " is at position " + (offset + j)); 																									
			}
		}
		
		if (occ_counter != 0) {
			//System.out.println("\nFile Name: " + filePath.getName());
			//System.out.println("-----------------------------------------------------\n");
		}
		my_Object.close();	
		return occ_counter;
	}

	
	// Used Collections.sort method for sorting which uses Merge-sort 
	
	public static void rankingPages(Hashtable<String, Integer> fname, int occur) {
		
		// Convert into List and then sort it using sort from Collections

		ArrayList<Map.Entry<String, Integer>> my_list = new ArrayList<Map.Entry<String, Integer>>(fname.entrySet());

		// Sort the list using sort from Collections
		Collections.sort(my_list, new Comparator<Map.Entry<String, Integer>>() {

			public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
				return obj1.getValue().compareTo(obj2.getValue());
			}
		});

		// reverse the sorted list
		Collections.reverse(my_list);

		if (occur != 0) {
			System.out.println("\n--------Top 5 Search Results-------\n");

			int my_number = 5;
			int j = 0;
			while (my_list.size() > j && my_number > 0) {
				
				int dotPos = my_list.get(j).toString().lastIndexOf(".");
				String temp_filename = my_list.get(j).toString().substring(0, dotPos);
				
	        	String htm_fileName = temp_filename +".html";
				
				System.out.println("(" + (j+1) + ") " + htm_fileName + " page ");
				j++;
				my_number--;
			}
		} else {
			
			System.out.println("\nSearch Query is not found");
		}

	}

	public static void showRankedResult(String searchword) {
		try {
		
		PageRanking PageRanking = new PageRanking();

		Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
		
		int frequency_count = 0;
		int rep = 0; 
		
		// Number of files that contains the Searched query
		
			File my_dir = new File("ConvertedTextFiles");

			File[] fileArray = my_dir.listFiles();
			
			for (int i = 0; i < fileArray.length; i++) {

				frequency_count = PageRanking.searchWord(fileArray[i], searchword);

				ht.put(fileArray[i].getName(), frequency_count);
				
				if (frequency_count != 0) {
					rep++;
				}

			}
			
			System.out.println("\nNo. of Files for '" + searchword + "' is : " + rep);
			
			rankingPages(ht, rep);
					} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
