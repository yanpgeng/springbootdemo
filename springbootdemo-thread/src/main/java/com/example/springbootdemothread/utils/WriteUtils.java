package com.example.springbootdemothread.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteUtils {
    public static void writeFiles(int i) throws IOException {
        String path ="E:\\file\\file\\"+i+".txt";
        File file =new File(path);

        //if file doesnt exists, then create it
        if(!file.exists()){
            file.createNewFile();
        }
        String data = i +" This content will append to the end of the file"+"----------";
        /*FileWriter fileWritter = new FileWriter(file.getName(),true);
        fileWritter.write(data);
        fileWritter.close();*/
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data);
        bw.close();
    }

    public static void main(String[] args) {
        try {
            writeFiles(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
