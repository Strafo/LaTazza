package utils;
import org.apache.commons.io.FilenameUtils;
import java.io.File;

public final class PathHandler {
    private PathHandler(){}

    /**
     * @brief
     * Adatta il path(assoluto) passato al sistema operativo corrente
     * Non funziona se almeno un
     */
    public static  String modifyPath(String originalAbsolutePath){
        return FilenameUtils.separatorsToSystem(originalAbsolutePath);
    }

    public static String getSeparator(){
        return File.separator;
    }
    /*public static void main(String[] args) {
        String test=System.getProperty("user.dir")+"\\src\\backend\\database\\config\\";
        System.out.println(PathHandler.modifyPath(test));
    }*/


}
