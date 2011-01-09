package AddressBook.bean;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Program Name: CMIS440 Lab 4 Address Book Web App
* @author Brandon R Russell
* @Course CMIS440
* Date: Jan 6, 2011
*/

/** This class writes messages and exceptions for this program to a file.
*|----------------------------------------------------------------------------|
*|                             CRC: Logger                                    |
*|----------------------------------------------------------------------------|
*|Logs messages and exceptions                                   AddressBean  |
*|                                                               AddressesDAO |
*|----------------------------------------------------------------------------|
*
* @TheCs Cohesion - All methods in this class work together on similar task.
* Completeness - Completely logs messages and exceptions.
* Convenience - There are sufficient methods and variables to complete the
*                required task.
* Clarity - The methods and variables are distinguishable and work in a
*           uniform manner to provide clarity to other programmers.
* Consistency - All names,parameters ,return values , and behaviors follow
*               the same basic rules.
*/


public class Logger {
	
	private String fileName = "AddressBook.log"; //Default value
	/**
	 * Get the current working directory below
	 */
	private String path = 
		Logger.class.getProtectionDomain().getCodeSource().getLocation().toString();
	/**
	 * Setup a date/time format for when inputting into the log file.
	 */
	public final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

	

	/** Constructor setups path and filename of log file.
	 *@TheCs Cohesion - Constructor setups path and filename of log file.
	 * Completeness - Completely setups path and filename of log file.
	 * Convenience - Simply setups path and filename of log file.
	 * Clarity - It is simple to understand that this setups path and filename
	 *           of log file.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 */	
	public Logger(){
		setupPath(fileName); //Use default filename
	}

	/** Constructor setups path and filename of log file.
	 *@TheCs Cohesion - Constructor setups path and filename of log file.
	 * Completeness - Completely setups path and filename of log file.
	 * Convenience - Simply setups path and filename of log file.
	 * Clarity - It is simple to understand that this setups path and filename
	 *           of log file.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param aFileName filename of log file               
	 */		
	public Logger(String aFileName){
		setupPath(aFileName); //Use provided filename
	}
	
	/** Constructor setups path and filename of log file.
	 *@TheCs Cohesion - Constructor setups path and filename of log file.
	 * Completeness - Completely setups path and filename of log file.
	 * Convenience - Simply setups path and filename of log file.
	 * Clarity - It is simple to understand that this setups path and filename
	 *           of log file.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param afileName filename of log file
	 * @param aPath path to log file               
	 */		
	public Logger(String afileName, String aPath){
		fileName = aPath + afileName; //Use provided path and filename
	}
	
    /** Remove 'file:/' , if filename present(remove it), add filename to end
	  * The regex below will find the file:/ at the beginning of the url, if
	  * present, and will then capture into group 1 the full path to the
	  * application directory. It will then find and remove the filename
	  * at the end w/ extension and omit it. Also, makes a call to os.name
	  * If OS is Linux is it appends a '/' to the beginning of the file path, if
	  * not already there.
	  * @TheCs Cohesion - Removes 'file:/' from the beginning, if filename is
	  *                   present then remove it, and finally add filename
	  *                   to the end.
	  * Completeness - Completely removes 'file:/' from the beginning, if filename
	  *                   is present then remove it, and finally add filename
	  *                   to the end.
	  * Convenience - Simply removes 'file:/' from the beginning, if filename is
	  *                   present then remove it, and finally add filename
	  *                   to the end.
	  * Clarity - It is simple to understand that this Removes 'file:/' from the
	  *           beginning, if filename is present then remove it, and finally
	  *           add filename to the end.
	  * Consistency - It uses the same syntax rules as the rest of the class and
	  *               continues to use proper casing and indentation.
	  *
	  *@param aFileName provided filename of log file.
	  */	
	private void setupPath(String aFileName){
		/**
		 * This regex takes in the working directory and gets rid of the unwanted
		 * information and then appends the log file filename to the end of it.
		 */
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
	
	/** Writes given message to log file.
	 *@TheCs Cohesion - Writes given message to log file.
	 * Completeness - Completely writes given message to log file.
	 * Convenience - Simply writes given message to log file.
	 * Clarity - It is simple to understand that this writes given message to log file.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param msg message or exception to be written to log file.
	 */		
	public void log(String msg){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
		    out.write("\n" + now() + " : " + msg);
		    out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/** Writes given message and value to log file.
	 *@TheCs Cohesion - Writes given message and value to log file.
	 * Completeness - Completely writes given message and value to log file.
	 * Convenience - Simply writes given message and value to log file.
	 * Clarity - It is simple to understand that this writes given message and 
	 *           value to log file.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @param msg message or exception to be written to the log file.
	 * @param value associated value to be written to the log file.
	 */			
	public void log(String msg, int value){
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
		    out.write("\n" + now() + " : " + msg + " = " + value);
		    out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/** Returns formatted current Date/Time.
	 *@TheCs Cohesion - Returns formatted current Date/Time.
	 * Completeness - Completely returns formatted current Date/Time.
	 * Convenience - Simply returns formatted current Date/Time.
	 * Clarity - It is simple to understand that this returns formatted current Date/Time.
	 * Consistency - It uses the same syntax rules as the rest of the class and
	 *               continues to use proper casing and indentation.
	 * @return Current Date/Time formatted.
	 */		
	private String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
	
}
