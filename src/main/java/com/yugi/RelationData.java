package com.yugi;

import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class RelationData {

    static ArrayList<String[]> freezeFrames;
    static String[] csvHeaders = new String[]{
        "Speed",
                "EngineSpeed",
                "CoolantTemperature",
                "AccelerationPedal1",
                "AmbientTemperature",
                "BatteryVoltage",
                "RailPressure",
                "ComputationalLoadValue",
                "AtmosphericPressure",
                "EngineLoad",
                "ManifoldAbsolutePressure",
                "TorqueMode",
                "IntakeAirTemperature",
                "AcceleratorPedal1Opening",
                "AcceleratorPedal2Opening",
                "IntakeFlow",
                "AcceleratorPedalSensor1Signal",
                "MonitoringModuleVoltage",
                "FaultCode",};
    public static ArrayList<String[]> faultCodes;

    public static void main( String[] args ) {

        readFaultCode();
        for (String[] faultCode : faultCodes) {
            String path = "src/data/my_csv/classify4/"+faultCode[0]+".csv";
            readFreezeDetail(path);
            path = "src/data/relation_txt/data/"+faultCode[0]+".txt";
            writeFreezeDetail(path);
        }
    }

    public static void readFreezeDetail(String path) {

        try {
            // 用来保存数据
            freezeFrames = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = path;
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
//                System.out.println(reader.getRawRecord());
                freezeFrames.add(reader.getValues());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeFreezeDetail(String path){
        String record = "";
        try {
            PrintStream out = new PrintStream(path);

            for (String[] freezeFrame : freezeFrames) {
                for (int i = 0; i < freezeFrame.length-1; i++) {
                    if (!freezeFrame[i].equals("NULL")){
                        record = record + csvHeaders[i] + ",";
                    }
                }
                if (!record.equals("")) {
                    out.println(record);
                }
                record = "";
            }
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void readFaultCode() {
        try {
            // 用来保存数据
            faultCodes = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = "src/data/my_csv/fault_code.csv";
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
//                System.out.println(reader.getRawRecord());
                faultCodes.add(reader.getValues());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
