package VodafoneFramework.utilities;

import VodafoneFramework.utilities.readers.PropertiesReader;

public class ConfigUtil {

    public static String BROWSER;
    public static String BASE_URL;
    public static String USER_NAME;
    public static String PASSWORD;
    public static int WAIT_TIME;
    public static int PATTERN_SIZE;
    public static boolean HEADLESS;
    public static String Attachement_URL;
    
    public static void loadTestConfigurations() {
        PropertiesReader reader = new PropertiesReader( "config.properties");
        BROWSER = reader.getPropertyUsingKey("Browser");
        BASE_URL = reader.getPropertyUsingKey("Base_URL");
        USER_NAME = reader.getPropertyUsingKey("User_Name");
        PASSWORD = reader.getPropertyUsingKey("Password");
        WAIT_TIME = Integer.parseInt(reader.getPropertyUsingKey("Wait_time"));
        PATTERN_SIZE = Integer.parseInt(reader.getPropertyUsingKey("Random_Pattern_Size"));
        HEADLESS = Boolean.valueOf(reader.getPropertyUsingKey("Headless"));
        Attachement_URL =reader.getPropertyUsingKey("Attachement_URL");
    }
}
