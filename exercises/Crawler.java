package org.galagosearch.exercises;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.galagosearch.core.parse.Document;
import org.galagosearch.core.parse.Tag;
import org.galagosearch.core.parse.TagTokenizer;

public class Crawler {
	private String root;
	/**
	* Looks for a BASE tag in the web page, and returns the URL that tag
	* refers to if it exists.  
	* 
	If the tag doesn't exist, this method 
	returns fetchUrl.
	*
	* The BASE tag in a web page specifies the base URL
	for any relative links.
	* 
	* 
	@param
	document The document that may contain a BASE tag.
	* 
	@param
	fetchUrl The URL of the document.
	* 
	@return
	*/
	public URL getBaseUrl(Document document, URL fetchUrl) {
	try {
		for(Tag tag : document.tags) {
			if (tag.name.equals("base") && tag.attributes.containsKey("href")) {
				return new URL(tag.attributes.get("href"));
			}
		}
	} catch(MalformedURLException e) {
		return fetchUrl;
	}
		return fetchUrl;
	}
	/**
	* Given a URL, fetches its contents to a String and returns it.
	* Pages over 256KB are truncated to 256KB to keep the system from
	* trying to load huge files.
	*
	* 
	@param
	fetchUrl
	* 
	@return
	dataString
	* 
	@throws
	java.io.IOException
	*/
	public String fetchUrlToString(URL fetchUrl) throws IOException {
	// Download actual pages. Java.net.URL.openConnection()
	URLConnection connection = fetchUrl.openConnection();
	connection.connect();
	InputStream stream = connection.getInputStream();
	// Only read things that might have tags in them: Hint:“text/html”
	// Limit reads to 256k
	// Read until there's no more data, or until we've read 256K, then quit.
	// Decode the stream to UTF-8 format. Hint: look at String class
	}
	/**
	* Writes the downloaded 
	String 
	to a file.
	* 
	@param
	url
	* 
	@throws
	IOException 
	*/
	public String fetchUrlToFile(URL url) throws IOException {
	// String format of HTML file. Hint: call fetchUrlToString(url)
	// Get rid of any multiple spaces, or new lines to single space
	// Basically put everything to one line
	}
	/**
	* Runs the crawler.  Starting at rootUrl, this message fetches a new page
	* every 5 seconds and its internal state tells you what's happening.
	* 
	* 
	@param
	rootUrl
	* 
	@throws
	java.io.UnsupportedEncodingException
	* 
	@throws
	java.io.IOException
	Information Retrieval/Filtering: Assignment #2
	5
	* 
	@throws
	java.lang.InterruptedException
	*/
	public void run(URL rootUrl) throws UnsupportedEncodingException, IOException, InterruptedException {
	// ~/parse/TagTokenizer.java
	TagTokenizer tokenizer = new TagTokenizer();
	// Java.util.LinkedList, Java.util.Queue
	Queue<URL> urls = new LinkedList<URL>();
	// Java.util.HashSet
	HashSet<URL> seen = new HashSet<URL>();
	// Seeds are added to a URL request queue. Hint: look at methods in Queue
	// Add to seen queue to avoid duplicated crawl
	// Repeat the loop until the number of downloaded HTML files is 10
	// Fetch a url from the queue, Java.util.queue.poll()
	// Write the downloaded String to a HTML file into the folder
	// Hint: call method fetchUrlToFile
	// Our file should become a Document object 
	// We need this so that we can look at tag <a href>
	// Hint: Call tokenize() method in tokenizer. 
	// Hint: Return type: Document(~/parse/Document.java)
	//Getting the base url to resolve relative links (not absolute url)
	// Hint: call getBaseUrl()
	// After fetching a document, we need to look for new URLs to crawl.
	// Hint: use loop –refer getBaseUrl. It is very similar
	// URLs on an HTML page are relative to the base URL.
	// For instance, <a href="/search">on yahoo.com is
	// equivalent to http://yahoo.com/search.
	// We check here to see if we've seen this URL before.
	// It's important to do the check when adding to the queue
	// to keep the queue size low.
	// Wait 5 seconds between fetches.
	Thread.sleep(5000);
	}
	/**
	* Create folder under root e.g., galagosearch-1.0.4
	* In our example, it will create “download” folder
	* 
	@param root
	*/
	public void setFolder(String root) {
	// Hint: look at mkdir method in File class
	}

}
