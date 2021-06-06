package WebSearchEngine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import PatternFind.FindPatterns;
import SpellCheck.SpellChecker;
import FrequencyCount.CalculateAll;
import RankingPage.PageRanking;
public class SearchEngine {

	// https://www.javatpoint.com/
	// \b(\w+)\s+\1\b
	
public static void main(String[] args) throws FileNotFoundException, NullPointerException, IOException {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("-> Enter the URL want Crawl: ");
		String urlname = s.nextLine();

		System.out.println("\n-> Enter number of URL want Crawl: ");
		String number = s.nextLine();
		int numberofurls = Integer.parseInt( number );  

		System.out.println("\n-> Download " + numberofurls + " HTML files : \n");
		WebCrawler.downloadHTMLFiles(urlname,numberofurls);
		
		System.out.println("\n--------------------------------------------------------------\n");
		HTMLTextConverter.convertHtmlToText();
		System.out.println("-> Converting from Html files to Text files completed \n");
		System.out.println("--------------------------------------------------------------\n");
		
		System.out.println("-> Enter your search : ");
		String searchword = s.nextLine();
		
		System.out.println("-> Page Ranking Started  : ");
		PageRanking.showRankedResult(searchword);

		System.out.println("--------------------------------------------------------------\n");
		FindPatterns.showPatterns();
		
		System.out.println("--------------------------------------------------------------\n");
		System.out.println("-> Enter your word to count frequency of the word : ");
		String wordtocount = s.nextLine();
		CalculateAll.wordCount(wordtocount);
		
		System.out.println("--------------------------------------------------------------\n");
		System.out.println("-> Start Spell Checking : ");
		System.out.println("-> Spell Check is working... : ");
		
		SpellChecker.showSpellCheckResult();
		
		s.close();
	}
}
