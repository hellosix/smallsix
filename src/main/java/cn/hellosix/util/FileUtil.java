package cn.hellosix.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/10/17.
 */
public class FileUtil {
    public static void checkOrMkdirDir(String path){
        File file =new File(path);
        if  (!file .exists()  && !file .isDirectory()) {
            file .mkdir();
        }
    }

    public static List<String> getFiles(String path){
        List<String> res = new ArrayList<>();
        File file = new File( path );
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                res.add(files[i].getName());
            }
        }
        return res;
    }

    public static boolean deleteFile(String path){
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
