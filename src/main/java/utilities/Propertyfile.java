package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propertyfile {
	
	public static String getValueForKey(String key) throws IOException{
   Properties pi=new Properties();
   FileInputStream fi=new FileInputStream("E:\\Srikanth_82\\Orangehrm_POM\\PropertiesFile\\Environment.propertoes");
   pi.load(fi);
    return pi.getProperty(key);
	}

}
