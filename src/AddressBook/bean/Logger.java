package AddressBook.bean;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {
	
	private String fileName = "AddressBook.log";
	private String path = Logger.class.getProtectionDomain().getCodeSource().getLocation().toString();
	
	
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
