package com.yugi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;

import Model.FreezeFrame1;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * Hello world!
 *
 */
public class RawDataRefactor
{
    static ArrayList<FreezeFrame1> freezeFrame1s;

    public static void main( String[] args )

    {
        System.out.println( "Hello World!" );
        //readCSV();
        readFreezeDetail();
        writeFreezeFrames();
    }

    public static void readCSV() {
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = "src/data/wf_csv/tb_stream_detail.csv";
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
//                System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
            reader.close();

            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {
                // 取得第row行第0列的数据
                String cell = csvFileList.get(row)[0];
                System.out.println("------------>"+cell);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void writeCSV() {
        // 定义一个CSV路径
        String csvFilePath = "D://StemQ.csv";
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));
            // 写表头
            String[] csvHeaders = { "编号", "姓名", "年龄" };
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            for (int i = 0; i < 20; i++) {
                String[] csvContent = { i + "000000", "StemQ", "1" + i };
                csvWriter.writeRecord(csvContent);
            }
            csvWriter.close();
            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readStreamDetail() {
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = "src/data/wf_csv/tb_stream_detail.csv";
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
            reader.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFreezeDetail() {
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = "src/data/wf_csv/tb_freeze_detail.csv";
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
//                System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());

            }
            reader.close();

            freezeFrame1s = new ArrayList<>();
            int id = 8241;
            //车速
            double Speed;
            //发动机转速
            double EngineSpeed;
            //冷却液温度
            double CoolantTemperature;
            //加速踏板
            double AccelerationPedal1;
            //故障码
            String FaultCode;
            //环境温度
            double AmbientTemperature;
            //蓄电池电压
            double BatteryVoltage;
            //轨压
            double RailPressure;
            //计算负荷值
            double ComputationalLoadValue;
            //大气压力
            double AtmosphericPressure;
            //发动机负荷
            double EngineLoad;
            //进气压力
            double ManifoldAbsolutePressure;
            //扭矩模式
            double TorqueMode;
            //冻结帧长度
            int FreezeFrameLength;
            //进气温度
            double IntakeAirTemperature;
            //油门踏板1开度
            double AcceleratorPedal1Opening;
            //油门踏板2开度
            double AcceleratorPedal2Opening;
            //进气流量
            double IntakeFlow;
            //油门踏板传感器1信号
            double AcceleratorPedalSensor1Signal;
            //监控模块电压
            double MonitoringModuleVoltage;
            FreezeFrame1 freezeFrame1 = new FreezeFrame1();
            freezeFrame1.setId(id);

            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {
                // 取得第row行第0列的数据

                id = Integer.parseInt(csvFileList.get(row)[1]);
                if (id != freezeFrame1.getId()) {
                    freezeFrame1s.add(freezeFrame1);
                    freezeFrame1 = new FreezeFrame1();
                    freezeFrame1.setId(id);
                }
                String attribute = csvFileList.get(row)[2];

                switch (attribute) {
                    case "Speed":
                        Speed = Double.parseDouble(csvFileList.get(row)[3]);
                        while (Speed>200){
                            Speed = Speed/10;
                        }
                        freezeFrame1.setSpeed(Speed);
                        break;
                    case "EngineSpeed":
                        EngineSpeed = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setEngineSpeed(EngineSpeed);
                        break;
                    case "CoolantTemperature":
                        CoolantTemperature = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setCoolantTemperature(CoolantTemperature);
                        break;
                    case "AccelerationPedal1":
                        AccelerationPedal1 = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setAccelerationPedal1(AccelerationPedal1);
                        break;
                    case "FaultCode":
                        FaultCode = csvFileList.get(row)[3];
                        freezeFrame1.setFaultCode(FaultCode);
                        break;
                    case "AmbientTemperature":
                        AmbientTemperature = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setAmbientTemperature(AmbientTemperature);
                        break;
                    case "BatteryVoltage":
                        BatteryVoltage = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setBatteryVoltage(BatteryVoltage);
                        break;
                    case "RailPressure":
                        RailPressure = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setRailPressure(RailPressure);
                        break;
                    case "ComputationalLoadValue":
                        ComputationalLoadValue = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setComputationalLoadValue(ComputationalLoadValue);
                        break;
                    case "AtmosphericPressure":
                        AtmosphericPressure = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setAtmosphericPressure(AtmosphericPressure);
                        break;
                    case "EngineLoad":
                        EngineLoad = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setEngineLoad(EngineLoad);
                        break;
                    case "ManifoldAbsolutePressure":
                        ManifoldAbsolutePressure = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setManifoldAbsolutePressure(ManifoldAbsolutePressure);
                        break;
                    case "TorqueMode":
                        TorqueMode = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setTorqueMode(TorqueMode);
                        break;
                    case "FreezeFrameLength":
                        FreezeFrameLength = Integer.parseInt(csvFileList.get(row)[3]);
                        freezeFrame1.setFreezeFrameLength(FreezeFrameLength);
                        break;
                    case "IntakeAirTemperature":
                        IntakeAirTemperature = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setIntakeAirTemperature(IntakeAirTemperature);
                        break;
                    case "AcceleratorPedal1Opening":
                        AcceleratorPedal1Opening = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setAcceleratorPedal1Opening(AcceleratorPedal1Opening);
                        break;
                    case "AcceleratorPedal2Opening":
                        AcceleratorPedal2Opening = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setAcceleratorPedal2Opening(AcceleratorPedal2Opening);
                        break;
                    case "IntakeFlow":
                        IntakeFlow = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setIntakeFlow(IntakeFlow);
                        break;
                    case "AcceleratorPedalSensor1Signal":
                        AcceleratorPedalSensor1Signal = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setAcceleratorPedalSensor1Signal(AcceleratorPedalSensor1Signal);
                        break;
                    case "MonitoringModuleVoltage":
                        MonitoringModuleVoltage = Double.parseDouble(csvFileList.get(row)[3]);
                        freezeFrame1.setMonitoringModuleVoltage(MonitoringModuleVoltage);
                        break;
                }

            }

//            freezeFrame1s.sort(new Comparator<FreezeFrame1>() {
//                @Override
//                public int compare(FreezeFrame1 o1, FreezeFrame1 o2) {
//                    if (o1.getFaultCode().compareTo(o2.getFaultCode())<0) {
//                        return 0;
//                    }
//                    else {
//                        return 1;
//                    }
//                }
//            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFreezeFrames() {
        // 定义一个CSV路径
        String csvFilePath = "src/data/my_csv/freeze_frame_0.csv";
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

            for (FreezeFrame1 freezeFrame1:
                 freezeFrame1s) {
                if (freezeFrame1.getFaultCode().contains("Mar") || freezeFrame1.getFaultCode().contains("May")
                        || freezeFrame1.getFaultCode().contains("Nov") || freezeFrame1.getFaultCode().contains("Apr")
                        || freezeFrame1.getFaultCode().contains("Sep")){
//                    System.out.println(freezeFrame1.getFaultCode());
                    continue;
                }

                String[] csvContent = { freezeFrame1.getId()+"",
                        freezeFrame1.getFaultCode(),
                        freezeFrame1.getSpeed()+"",
                        freezeFrame1.getEngineSpeed()+"",
                        freezeFrame1.getCoolantTemperature()+"",
                        freezeFrame1.getAccelerationPedal1()+"",
                        freezeFrame1.getAmbientTemperature()+"",
                        freezeFrame1.getBatteryVoltage()+"",
                        freezeFrame1.getRailPressure()+"",
                        freezeFrame1.getComputationalLoadValue()+"",
                        freezeFrame1.getAtmosphericPressure()+"",
                        freezeFrame1.getEngineLoad()+"",
                        freezeFrame1.getManifoldAbsolutePressure()+"",
                        freezeFrame1.getTorqueMode()+"",
                        freezeFrame1.getFreezeFrameLength()+"",
                        freezeFrame1.getIntakeAirTemperature()+"",
                        freezeFrame1.getAcceleratorPedal1Opening()+"",
                        freezeFrame1.getAcceleratorPedal2Opening()+"",
                        freezeFrame1.getIntakeFlow()+"",
                        freezeFrame1.getAcceleratorPedalSensor1Signal()+"",
                        freezeFrame1.getMonitoringModuleVoltage()+""
                };
                csvWriter.writeRecord(csvContent);

            }
            csvWriter.close();
            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
