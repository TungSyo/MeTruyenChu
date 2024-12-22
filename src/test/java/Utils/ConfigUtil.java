package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    private Properties properties;
    private FileInputStream fileIn;
    private FileOutputStream fileOut;
    private String propertiesFilePath;

    public ConfigUtil(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
        properties = new Properties();
        loadPropertiesFile();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        try {
            fileOut = new FileOutputStream(propertiesFilePath);
            properties.setProperty(key, value);
            properties.store(fileOut, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadPropertiesFile() {
        try {
            fileIn = new FileInputStream(propertiesFilePath);
            properties.load(fileIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
