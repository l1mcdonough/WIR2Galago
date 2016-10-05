package org.galagosearch.exercises;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
	public void run(URL seedURL) throws IOException{
		String siteText = fetchUrlToString(seedURL);
		System.out.println(siteText);
		Document document = new TagTokenizer().tokenize(siteText);
		ArrayList<String> links = getLinks(document);
		for (String ref : links){
			System.out.println(ref);
		}
	}
	
}
