package com.yugi;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ClassifyByFaultCode {

    public static ArrayList<String[]> freezeFrames;
    public static ArrayList<String[]> faultCodes;

    public static void main( String[] args )

    {
        System.out.println( "Hello World!" );
        readFreezeFrames();
        readFaultCode();
        writeClassifiedFreezeFrames();

    }

    public static void readFreezeFrames() {
        try {
            // 用来保存数据
            freezeFrames = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = "src/data/my_csv/freeze_frame_1.csv";
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

    public static void writeClassifiedFreezeFrames(){
        for (String[] csvContent:
             faultCodes) {
            writeCSV(csvContent[0]);
        }
    }

    public static void writeCSV(String faultCode) {
        // 定义一个CSV路径
        String csvFilePath = "src/data/my_csv/classify/"+faultCode+".csv";
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));
            // 写表头
            String[] csvHeaders = { "id",
                    "FaultCode",
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
                    "FreezeFrameLength",
                    "IntakeAirTemperature",
                    "AcceleratorPedal1Opening",
                    "AcceleratorPedal2Opening",
                    "IntakeFlow",
                    "AcceleratorPedalSensor1Signal",
                    "MonitoringModuleVoltage" };
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            for (String[] csvContent:
                 freezeFrames) {
                if (csvContent[1].equals(faultCode)){
                    csvWriter.writeRecord(csvContent);
                }
            }
            csvWriter.close();
            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
