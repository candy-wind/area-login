package com.example.demo;


import org.jumpmind.symmetric.csv.CsvReader;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by apple on 2018/2/25.
 */
public class TspReader {
    static double[] x;
    static double[] y;

    private static void readData(String path,String file)
    {
        try {
            ArrayList<String[]> csvFileList = new ArrayList<>();
            String csvFilePath = path+"/"+file;
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            while (reader.readRecord()){
                csvFileList.add(reader.getValues());
            }
            reader.close();
            x=new double[csvFileList.size()];
            y=new double[csvFileList.size()];
            for (int i = 0; i<csvFileList.size(); i++) {
                String[] strData = csvFileList.get(i);
                x[i]=Double.valueOf(strData[1]);
                y[i]=Double.valueOf(strData[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double[][] getDistances(String path,String file){
        readData(path,file);
        double c[][]=new double[x.length][x.length];
        for(int i=0;i<x.length;i++){
            for(int j=0;j<x.length;j++){
                double temp1=Math.abs(x[i]-x[j]);
                double temp2=Math.abs(y[i]-y[j]);
                c[i][j]=(int)Math.sqrt(Math.pow(temp1,2)+Math.pow(temp2,2));
            }
        }
        for(int i=0;i<x.length;i++){
            c[i][i]=Double.MAX_VALUE;
        }
        return c;
    }
}
