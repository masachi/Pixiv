package utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sdlds on 2016/6/2.
 */
public class DownloadModule {
    public static boolean download(String value,String filename) throws IOException {
        try {
            URL url = new URL(value);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(filename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static String getFilename(String value,String filename,String URL_HEAD){
        //Pattern p = Pattern.compile("/(.*?.jpg)");
        //Matcher m = p.matcher(value);
        //String filename = "D:\\Onedrive\\IFTTT\\Twitter\\Sherry\\";

        value = value.replaceAll(URL_HEAD,"");
        //while(m.find()){
            System.out.println(value);
            filename = filename + value;
            // System.out.println(filename);
         //   return filename;
        //}
        return filename;
    }

    public static boolean download_example(String value,String filename) throws IOException {
        try {
            File file = new File(filename);
            FileWriter os = new FileWriter(file);
            if(!file.exists()){
                file.createNewFile();
            }
            os.write(value);
            os.flush();
            os.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
