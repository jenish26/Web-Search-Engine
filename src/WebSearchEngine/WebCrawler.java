package WebSearchEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	private static final int MAX_DEPTH = 3;
	static HashSet<String> hs = new HashSet<String>();;
	static ArrayList<String> arr_links = new ArrayList<String>();
	
	public static void getURLs(String myURL,int numberofurls, int depth) {
		
		String[]  only_filename;
		File f1 = new File("HTMLFiles");	
		only_filename = f1.list();
		
		// limit the number of files you want to download
		if(only_filename.length<numberofurls) {			
			if ((!hs.contains(myURL) && (depth < MAX_DEPTH))) {
				try {
					if (hs.add(myURL)) {
						
						//System.out.println(myURL);
						
						//add URL in the array list
						arr_links.add(myURL);
						String str = myURL;
						downloadURLs(str);
					}
					// Get the HTML code from URL
					Document my_document = Jsoup.connect(myURL).get();
					// Parse the HTML 
					Elements link_on_page = my_document.select("a[href]");
					
					depth++;
					   
					// For each extracted myURL
					for (Element page : link_on_page) {
						getURLs(page.attr("abs:href"), numberofurls, depth);
					}	
				} catch (IOException e) {
					System.err.println("For '" + myURL + "': " + e.getMessage());
				}
			}
		}
	}

	public static void downloadURLs( final String urlString) throws IOException, MalformedURLException {
		{
			try {

				// Create object URL 
				URL url = new URL(urlString);
				
				// Create object of BufferedReader  	
				BufferedReader BRURls = new BufferedReader(new InputStreamReader(url.openStream()));

				int dotPos = url.toString().lastIndexOf("/");
				String temp_filename = url.toString().substring(dotPos, url.toString().length());
				
	        	
				//  filename in which you want to download
				String str = temp_filename + ".html";

				// Create object of BufferedWriter
				BufferedWriter BWURLs = new BufferedWriter(new FileWriter("HTMLFiles/" + str));
				
				// read each line from stream till end
				String line;
				while ((line = BRURls.readLine()) != null) {
					BWURLs.write(line);
				}
				
				BRURls.close();
				BWURLs.close();
				System.out.println(urlString + "		Successfully Downloaded.\n");	
				
			}

			// Exceptions
			catch (MalformedURLException mal) {
				System.out.println("Malformed URL Exception : "+ mal.getMessage());
			} catch (IOException i) {
				System.out.println("IOException : " +  i.getMessage());
			}
		}
	}

	public static void downloadHTMLFiles(String urlname, int numberofurls) {
		
		File f1 = new File("HTMLFiles");	
		//clear the directory before the downloading new file  
		for(File file: f1.listFiles()) {
			if (!file.isDirectory()) 
		        file.delete();
		}
		getURLs(urlname, numberofurls,0);
		
		System.out.println("All files Downloading complected");
	
	}
	
}
