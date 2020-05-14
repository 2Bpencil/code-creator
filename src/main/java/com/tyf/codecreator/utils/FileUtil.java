package com.tyf.codecreator.utils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.stream.Stream;

/**
 * @Description: 文件工具类
 * @Author: tyf
 * @Date: 2019/10/25 14:21
 */
public class FileUtil {

    private static String encoding = "UTF-8";

    public static void main(String[] args) {
        try {
            writeContentToFile("F:\\test\\bbb.txt","hahha哈哈哈");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建文件
     *
     * @param path
     * @author CRF
     * @date 2017年5月3日 下午4:57:09
     */
    public static void creatFile(String path){
        File folder = new File(path);
        if(!folder.exists()){
            try {
                folder.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
    * @Description:  创建文件夹
    * @Param:
    * @return:
    * @Author: Mr.Tan
    * @Date: 2019/10/25 14:46
    */
    public static void creatFolder(String path){
        File folder = new File(path);
        if(!folder.exists() && ! folder.isDirectory()){
            folder.mkdirs();
        }
    }


    /**
    * @Description:
    * @Param: source 源文件  target 目标文件
    * @return:
    * @Author: Mr.Tan
    * @Date: 2019/10/25 14:38
    */
    public static void copyFile(String source, String target) throws IOException {
        File sourceFile = new File(source);
        File targetFile = new File(target);
        FileChannel inputChannel = new FileInputStream(sourceFile).getChannel();
        FileChannel outputChannel = new FileOutputStream(targetFile).getChannel();
        outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        outputChannel.close();
        inputChannel.close();
    }
    /** 
    * @Description: 复制文件夹
    * @Param:  
    * @return:  
    * @Author: Mr.Tan 
    * @Date: 2019/10/25 15:02
    */ 
    public static void copyFolder(String source, String target){
        
    }
    /** 
    * @Description: 删除文件
    * @Param:  
    * @return:  
    * @Author: Mr.Tan 
    * @Date: 2019/10/25 15:05
    */ 
    public static void deleteFile(String path){
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }
    /** 
    * @Description: 清空文件夹
    * @Param:
    * @return:
    * @Author: Mr.Tan 
    * @Date: 2019/10/25 15:07
    */ 
    public static void clearFolder(String path){
        File folder = new File(path);
        String[] fileNames = folder.list();
        Stream.of(fileNames).forEach(fileName->{
            File file = null;
            if (path.endsWith(File.separator)) {
                file = new File(path+fileName);
            }else{
                file = new File(path+File.separator+fileName);
            }
            if(file.isFile()){
                file.delete();
            }
            if(file.isDirectory()){
                deleteFolder(file.getPath());
            }
        });
    }
    /** 
    * @Description: 删除文件夹
    * @Param:  
    * @return:  
    * @Author: Mr.Tan 
    * @Date: 2019/10/25 15:08
    */ 
    public static void deleteFolder(String path){
        clearFolder(path);
        new File(path).delete();
    }

    /** 
    * @Description: 在文件中写入内容
    * @Param:  文件路径(包括文件名称)
    * @return:  
    * @Author: Mr.Tan 
    * @Date: 2019/10/25 15:52
    */ 
    public static void writeContentToFile(String filePath,String content)throws IOException{
        String fileIn = content+"\r\n";
        String temp = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br= null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            //文件原有内容
            for(int i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(fileIn);
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }

}
    