package utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    final static Logger logger = LoggerFactory.getLogger(PropertyReader.class);
    InputStream inputStream = null;
    Properties properties = new Properties();

    public PropertyReader(String propertyFileName)
    {
        loadProperties(propertyFileName);
    }
    private  void loadProperties(String propFileName )
    {
        InputStream fileInputStream = null;
        try {
            fileInputStream = this.getClass().getResourceAsStream(propFileName);
            properties.load(fileInputStream);
        }
        catch(IOException ioe)
        {
            logger.error(ioe.getCause().getMessage()) ;
        }
    }

    public String readProperty(String key)
    {
        return properties.getProperty(key);
    }
}