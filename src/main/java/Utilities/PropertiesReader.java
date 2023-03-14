package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

    private static final String CONFIGFILEPATH =System.getProperty("user.dir")+"/src/test/resources/config";

    public static String getValue(String key) throws Exception {
    	System.out.println("CONFIGFILEPATH  :"+CONFIGFILEPATH);
        File file = new File(CONFIGFILEPATH + "/config.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public Long getTimeout() throws Exception {
        String timeout = PropertiesReader.getValue("timeout");
        return Long.parseLong(timeout);
    }
    
    public String getReportConfigPath() throws Exception{
    	String reportConfigPath = PropertiesReader.getValue(CONFIGFILEPATH +"/extent-config.xml");
    	if(reportConfigPath!= null) return reportConfigPath;
    	else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
    }
	
}