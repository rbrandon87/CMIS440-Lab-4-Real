package AddressBook.bean;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Program Name: CMIS440 Lab 4 Address Book Web App
* @author Brandon R Russell
* @Course CMIS440
* Date: Jan 6, 2011
*/

/** This class 
*|----------------------------------------------------------------------------|
*|                           CRC: ThreadControl                               |
*|----------------------------------------------------------------------------|
*|Create shared Total results object                             TotalResults |
*|                                                               FileResults  |
*|Run Threads for counting txt file lines/words                  WordCounter  |
*|----------------------------------------------------------------------------|
*
* @TheCs Cohesion - All methods in this class work together on similar task.
* Completeness - Completely creates word counter threads to process file input
* Convenience - There are sufficient methods and variables to complete the
*                required task.
* Clarity - The methods and variables are distinguishable and work in a
*           uniform manner to provide clarity to other programmers.
* Consistency - All names,parameters ,return values , and behaviors follow
*               the same basic rules.
*/


public class Logger {
	
	private String fileName = "AddressBook.log";
	private String path = Logger.class.getProtectionDomain().getCodeSource().getLocation().toString();
	

	/** Constructor initializes variables and creates array of filenames.
	 *@TheCs Cohesion - initializes variables/creates array of filenames.
	 * Completeness - Completely initializes variables/creates array of filenames
	 * Convenience - Simply initializes variables/creates array of filenames.
	 * Clarity - It is simple to understand that this initializes
	 *           variables/creates array of filenames.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param fileArguments contains filenames or name directory of files
	 * @param aDelimiter will give user specified delimiter
	 * @param aCaseCheck will give user specified case sensitive check
	 *@exception exception if none or other than text files are given.
	 */	
	public Logger(){
		setupPath(fileName);
	}
	
	public Logger(String aFileName){
		setupPath(aFileName);
	}
	
	public Logger(String afileName, String aPath){
		fileName = aPath + afileName;
	}
	
	public void setupPath(String aFileName){
		
        String extRegex = "(?:file:\\/)?(.*\\/)(?:.*[^\\/]\\.\\p{Alpha}*)?";
        Pattern pattern = Pattern.compile(extRegex);
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()){
            path = matcher.group(1);
        }

        if (System.getProperty("os.name").toLowerCase().contains("linux")){
            if (!path.substring(0, 0).equals("/")){
                path = "/" + path;
            }
        }
		
		fileName = path + aFileName;
		
		
	}
	
	public void log(String msg){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
		    out.write("\n" + msg);
		    out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void log(String msg, int value){
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
		    out.write("\n" + msg + " = " + value);
		    out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
				
		
	}

}
