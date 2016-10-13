package org.galagosearch.exercises;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.galagosearch.core.parse.Document;
import org.galagosearch.core.parse.Tag;
import org.galagosearch.core.parse.TagTokenizer;

public class Crawler {
	String folder;
	
	public void setFolder(String fol) {
		folder = fol;
	}
	
	public String fetchUrlToString(URL site) throws IOException{
		System.out.println("Crawling " + site);
		URLConnection connection = site.openConnection();
		connection.connect();
		Scanner textGetter = new Scanner(connection.getInputStream());
		String text = "";
		while(textGetter.hasNext()){
			text += textGetter.next() + " ";
		}
		return text;
	}
	
	public ArrayList<String> getLinks (Document document){
		ArrayList<String> links = new ArrayList<String>();
		for(Tag tag : document.tags){
			if (tag.name.equals("a")){
				links.add(tag.attributes.get("href"));
			}
		}
		return links;
	}
	public String getBaseURL(URL url) throws MalformedURLException {
		String comURL = url.toString();
		String[] splitURL = comURL.split("//");
		int leftCount = splitURL[0].length();
		int rightCount = splitURL[1].indexOf('/');
		String baseURL = comURL.substring(0, leftCount+rightCount+2);
		return baseURL;
	}
	public URL getFullURL(URL current, String target) throws MalformedURLException{
		if(target == null){
			return null;
		}
		else if (target.substring(0,2).equals("//")){
			
			return new URL("http:" + target) ;
		}
		else if (target.substring(0,1).equals("/")){
			return new URL(getBaseURL(current) + target);
		}
		else {
			return(new URL(target));
		}
	}
	public void run(URL seedURL) throws IOException{
		String siteText;
		String current = new java.io.File( "." ).getCanonicalPath();
	    System.out.println("Current dir: "+current);
		new File(folder).mkdir();
		Queue<URL> URLS = new LinkedList<URL>();
		for(int i=0; i<10; i++){
			siteText = fetchUrlToString(seedURL);
			PrintWriter fileWriter = new PrintWriter(folder+"/"+i, "UTF-8");
			fileWriter.print(siteText);
			fileWriter.close();
			Document document = new TagTokenizer().tokenize(siteText);
			HashSet<URL> seen = new HashSet<URL>();
			ArrayList<String> links = getLinks(document);
			int count=0;
			for (String ref : links){
				if(ref != null && ref.charAt(0) != '#'){
					URLS.add(getFullURL(seedURL, ref));
					count++;
				}
			}
			System.out.println("Crawler Found " + count + " Websites in "+ seedURL);
			seedURL = URLS.poll();
			while(seedURL==null)
				seedURL = URLS.poll();
			if(i!=9){
				try{
					Thread.sleep(5000);
				}catch(Exception e){
					System.out.println("Sleep Interupted");
				}
			}
		}
	}
	
}
