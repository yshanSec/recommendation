import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by yishan on 27/7/16.
 */
public class LoggerTest {
    static Logger log = Logger.getLogger(LoggerTest.class.getName());
    public static void main(String[] args) throws IOException{
//        PropertyConfigurator.configure("/home/yishan/IdeaProjects/recommendation/src/main/java/config/log4j.properties");
        log.debug("Hello this is a debug message");
        log.info("Hello this is a info message");
    }
}
