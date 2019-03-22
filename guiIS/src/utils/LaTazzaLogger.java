package utils;

import java.io.IOException;
import java.util.logging.*;

public final class LaTazzaLogger  {

    private static final Logger LOGGER=Logger.getLogger(LaTazzaLogger.class.getName());
    private static final String LOG_FILE_NAME="./LaTazza.log";

    private LaTazzaLogger(){}


    public static void initLogger(boolean stderrActive){
        try {
            Handler fileHandler = new FileHandler(LOG_FILE_NAME);
            Handler consoleHandler=new ConsoleHandler();
            LOGGER.addHandler(fileHandler);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            if(!stderrActive) {
                LOGGER.removeHandler(consoleHandler);
            }
        }catch (IOException exc){
            LOGGER.log(Level.SEVERE,"Impossibile creare logger.",exc);
        }
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

}
