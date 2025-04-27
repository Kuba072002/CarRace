import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    private static final String CONFIG_FILE = "cars.properties";

    public static Properties loadConfig() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
