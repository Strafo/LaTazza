package utils;
import org.apache.commons.io.FilenameUtils;
import java.io.File;

public final class PathHandler {
    private PathHandler(){}

    /**
     * @brief
     * Adatta il path(assoluto) passato al sistema operativo corrente
     */
    public static  String modifyPath(String originalAbsolutePath){
        return FilenameUtils.separatorsToSystem(originalAbsolutePath);
    }

    public static String getSeparator(){
        return File.separator;
    }



}
