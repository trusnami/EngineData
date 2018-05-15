package com.yugi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import Model.FreezeFrame1;
import Model.FreezeFrame2;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * Hello world!
 *
 */
public class RawDataRefactor
{
    static ArrayList<FreezeFrame1> freezeFrame1s;

    static ArrayList<FreezeFrame2> freezeFrame2s;

    public static void main( String[] args )

    {
        System.out.println( "Hello World!" );
        //readCSV();
//        readFreezeDetail1();
//        writeFreezeFrame1s();
        readFreezeDetail2();
        writeFreezeFrame2s();
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

    public static void readFreezeDetail1() {
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
//                        Speed = Double.parseDouble(csvFileList.get(row)[3]);
//                        while (Speed>200){
//                            Speed = Speed/10;
//                        }
//                        freezeFrame1.setSpeed(Speed);
                        freezeFrame1.setSpeed(1);
                        break;
                    case "EngineSpeed":
//                        EngineSpeed = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setEngineSpeed(EngineSpeed);
                        freezeFrame1.setEngineSpeed(1);
                        break;
                    case "CoolantTemperature":
//                        CoolantTemperature = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setCoolantTemperature(CoolantTemperature);
                        freezeFrame1.setCoolantTemperature(1);
                        break;
                    case "AccelerationPedal1":
//                        AccelerationPedal1 = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setAccelerationPedal1(AccelerationPedal1);
                        freezeFrame1.setAccelerationPedal1(1);
                        break;
                    case "FaultCode":
                        FaultCode = csvFileList.get(row)[3];
                        freezeFrame1.setFaultCode(FaultCode);
                        break;
                    case "AmbientTemperature":
//                        AmbientTemperature = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setAmbientTemperature(AmbientTemperature);
                        freezeFrame1.setAmbientTemperature(1);
                        break;
                    case "BatteryVoltage":
//                        BatteryVoltage = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setBatteryVoltage(BatteryVoltage);
                        freezeFrame1.setBatteryVoltage(1);
                        break;
                    case "RailPressure":
//                        RailPressure = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setRailPressure(RailPressure);
                        freezeFrame1.setRailPressure(1);
                        break;
                    case "ComputationalLoadValue":
//                        ComputationalLoadValue = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setComputationalLoadValue(ComputationalLoadValue);
                        freezeFrame1.setComputationalLoadValue(1);
                        break;
                    case "AtmosphericPressure":
//                        AtmosphericPressure = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setAtmosphericPressure(AtmosphericPressure);
                        freezeFrame1.setAtmosphericPressure(1);
                        break;
                    case "EngineLoad":
//                        EngineLoad = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setEngineLoad(EngineLoad);
                        freezeFrame1.setEngineLoad(1);
                        break;
                    case "ManifoldAbsolutePressure":
//                        ManifoldAbsolutePressure = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setManifoldAbsolutePressure(ManifoldAbsolutePressure);
                        freezeFrame1.setManifoldAbsolutePressure(1);
                        break;
                    case "TorqueMode":
//                        TorqueMode = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setTorqueMode(TorqueMode);
                        freezeFrame1.setTorqueMode(1);
                        break;
                    case "FreezeFrameLength":
//                        FreezeFrameLength = Integer.parseInt(csvFileList.get(row)[3]);
//                        freezeFrame1.setFreezeFrameLength(FreezeFrameLength);
                        freezeFrame1.setFreezeFrameLength(1);
                        break;
                    case "IntakeAirTemperature":
//                        IntakeAirTemperature = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setIntakeAirTemperature(IntakeAirTemperature);
                        freezeFrame1.setIntakeAirTemperature(1);
                        break;
                    case "AcceleratorPedal1Opening":
//                        AcceleratorPedal1Opening = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setAcceleratorPedal1Opening(AcceleratorPedal1Opening);
                        freezeFrame1.setAcceleratorPedal1Opening(1);
                        break;
                    case "AcceleratorPedal2Opening":
//                        AcceleratorPedal2Opening = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setAcceleratorPedal2Opening(AcceleratorPedal2Opening);
                        freezeFrame1.setAcceleratorPedal2Opening(1);
                        break;
                    case "IntakeFlow":
//                        IntakeFlow = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setIntakeFlow(IntakeFlow);
                        freezeFrame1.setIntakeFlow(1);
                        break;
                    case "AcceleratorPedalSensor1Signal":
//                        AcceleratorPedalSensor1Signal = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setAcceleratorPedalSensor1Signal(AcceleratorPedalSensor1Signal);
                        freezeFrame1.setAcceleratorPedalSensor1Signal(1);
                        break;
                    case "MonitoringModuleVoltage":
//                        MonitoringModuleVoltage = Double.parseDouble(csvFileList.get(row)[3]);
//                        freezeFrame1.setMonitoringModuleVoltage(MonitoringModuleVoltage);
                        freezeFrame1.setMonitoringModuleVoltage(1);
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

    public static void writeFreezeFrame1s() {
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

    public static void readFreezeDetail2() {
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

            freezeFrame2s = new ArrayList<>();
            int id = 8241;
            //车速
            double Speed;
            FreezeFrame2 freezeFrame2 = new FreezeFrame2();
            freezeFrame2.setId(id);

            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {
                // 取得第row行第0列的数据

                id = Integer.parseInt(csvFileList.get(row)[1]);
                if (id != freezeFrame2.getId()) {
                    freezeFrame2s.add(freezeFrame2);
                    freezeFrame2 = new FreezeFrame2();
                    freezeFrame2.setId(id);
                }
                String attribute = csvFileList.get(row)[2];

                switch (attribute) {
                    case "Speed":
                        Speed = Double.parseDouble(csvFileList.get(row)[3]);
                        while (Speed>200){
                            Speed = Speed/10;
                        }
                        freezeFrame2.setSpeed(Speed+"");
                        break;
                    case "EngineSpeed":
                        freezeFrame2.setEngineSpeed(csvFileList.get(row)[3]);
                        break;
                    case "CoolantTemperature":
                        freezeFrame2.setCoolantTemperature(csvFileList.get(row)[3]);
                        break;
                    case "AccelerationPedal1":
                        freezeFrame2.setAccelerationPedal1(csvFileList.get(row)[3]);
                        break;
                    case "FaultCode":
                        freezeFrame2.setFaultCode(csvFileList.get(row)[3]);
                        break;
                    case "AmbientTemperature":
                        freezeFrame2.setAmbientTemperature(csvFileList.get(row)[3]);
                        break;
                    case "BatteryVoltage":
                        freezeFrame2.setBatteryVoltage(csvFileList.get(row)[3]);
                        break;
                    case "RailPressure":
                        freezeFrame2.setRailPressure(csvFileList.get(row)[3]);
                        break;
                    case "ComputationalLoadValue":
                        freezeFrame2.setComputationalLoadValue(csvFileList.get(row)[3]);
                        break;
                    case "AtmosphericPressure":
                        freezeFrame2.setAtmosphericPressure(csvFileList.get(row)[3]);
                        break;
                    case "EngineLoad":
                        freezeFrame2.setEngineLoad(csvFileList.get(row)[3]);
                        break;
                    case "ManifoldAbsolutePressure":
                        freezeFrame2.setManifoldAbsolutePressure(csvFileList.get(row)[3]);
                        break;
                    case "TorqueMode":
                        freezeFrame2.setTorqueMode(csvFileList.get(row)[3]);
                        break;
                    case "IntakeAirTemperature":
                        freezeFrame2.setIntakeAirTemperature(csvFileList.get(row)[3]);
                        break;
                    case "AcceleratorPedal1Opening":
                        freezeFrame2.setAcceleratorPedal1Opening(csvFileList.get(row)[3]);
                        break;
                    case "AcceleratorPedal2Opening":
                        freezeFrame2.setAcceleratorPedal2Opening(csvFileList.get(row)[3]);
                        break;
                    case "IntakeFlow":
                        freezeFrame2.setIntakeFlow(csvFileList.get(row)[3]);
                        break;
                    case "AcceleratorPedalSensor1Signal":
                        freezeFrame2.setAcceleratorPedalSensor1Signal(csvFileList.get(row)[3]);
                        break;
                    case "MonitoringModuleVoltage":
                        freezeFrame2.setMonitoringModuleVoltage(csvFileList.get(row)[3]);
                        break;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeFreezeFrame2s() {
        // 定义一个CSV路径
        String csvFilePath = "src/data/my_csv/freeze_frame2_0.csv";
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));
            // 写表头
            String[] csvHeaders = {
                    "id",
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
                    "IntakeAirTemperature",
                    "AcceleratorPedal1Opening",
                    "AcceleratorPedal2Opening",
                    "IntakeFlow",
                    "AcceleratorPedalSensor1Signal",
                    "MonitoringModuleVoltage" };
            csvWriter.writeRecord(csvHeaders);
            // 写内容

            for (FreezeFrame2 freezeFrame2:
                    freezeFrame2s) {
                if (freezeFrame2.getFaultCode().contains("Mar") || freezeFrame2.getFaultCode().contains("May")
                        || freezeFrame2.getFaultCode().contains("Nov") || freezeFrame2.getFaultCode().contains("Apr")
                        || freezeFrame2.getFaultCode().contains("Sep")){
//                    System.out.println(freezeFrame1.getFaultCode());
                    continue;
                }
                if (freezeFrame2.getFaultCode().equals("NULL")){
                    continue;
                }

                String[] csvContent = { freezeFrame2.getId()+"",
                        freezeFrame2.getFaultCode(),
                        freezeFrame2.getSpeed(),
                        freezeFrame2.getEngineSpeed(),
                        freezeFrame2.getCoolantTemperature(),
                        freezeFrame2.getAccelerationPedal1(),
                        freezeFrame2.getAmbientTemperature(),
                        freezeFrame2.getBatteryVoltage(),
                        freezeFrame2.getRailPressure(),
                        freezeFrame2.getComputationalLoadValue(),
                        freezeFrame2.getAtmosphericPressure(),
                        freezeFrame2.getEngineLoad(),
                        freezeFrame2.getManifoldAbsolutePressure(),
                        freezeFrame2.getTorqueMode(),
                        freezeFrame2.getIntakeAirTemperature(),
                        freezeFrame2.getAcceleratorPedal1Opening(),
                        freezeFrame2.getAcceleratorPedal2Opening(),
                        freezeFrame2.getIntakeFlow(),
                        freezeFrame2.getAcceleratorPedalSensor1Signal(),
                        freezeFrame2.getMonitoringModuleVoltage()
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
