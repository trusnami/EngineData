package com.yugi;

import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.*;

public class Sampling {

    static String[][] trainData;
    static String[][] testData;

    public static void main(String[] args) throws Exception {

        String[] attrNames = null;
        attrNames = new String[] {  "Speed",
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

//        randomSamplingProcess(attrNames);
//        stratifiedSampling(attrNames);
        stratifiedSamplingProcess(attrNames);
    }

    static void recordprecision(String filePath, double precision){
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter fileWriter = new FileWriter(filePath, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
//            fileWriter.write("precision:"+precision+"\n");
            printWriter.println("precision:"+precision);
            printWriter.flush();
            fileWriter.flush();
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void recordAvgPrecision(String filePath, double precision,String samplingName){
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter fileWriter = new FileWriter(filePath, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
//            fileWriter.write("precision:"+precision+"\n");
            printWriter.println(samplingName+" avg precision:"+precision);
            printWriter.flush();
            fileWriter.flush();
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String[][] readCSV(String path){
        // "src/data/my_csv/freeze_frame_2.csv"
        String[][] rawData = null;
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = path ;
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
            rawData  = new String[csvFileList.size()][csvFileList.get(0).length];
            for (int i = 0; i < csvFileList.size(); i++) {
                rawData[i] = csvFileList.get(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rawData;
    }

    static void randomSampling(String[][] rawData){
        ArrayList<String[]> trainDataList = new ArrayList<>();
        ArrayList<String[]> testDataList = new ArrayList<>();
        for (String[] rawDataElement : rawData) {
            double ifTest = Math.random();
            if (ifTest > 0.8){
                testDataList.add(rawDataElement);
            }
            else {
                trainDataList.add(rawDataElement);
            }
        }

        trainData = new String[trainDataList.size()][trainDataList.get(0).length];
        testData = new String[testDataList.size()][testDataList.get(0).length];

        for (int i = 0; i < trainData.length; i++) {
            trainData[i] = trainDataList.get(i);
        }

        for (int i = 0; i < testData.length; i++) {
            testData[i] = testDataList.get(i);
        }
        System.out.println("training sample size : " + trainDataList.size() + " testing sample size : " + testDataList.size() );
    }

    static void randomSampling2(String[][] rawData){
        ArrayList<String[]> trainDataList = new ArrayList<>();
        ArrayList<String[]> testDataList = new ArrayList<>();
        double probability = 0.8;
        for (String[] rawDataElement : rawData) {
            double ifTest = Math.random();
            if (ifTest > probability){
                testDataList.add(rawDataElement);
            }

        }

        for (String[] rawDataElement : rawData) {
            double ifTest = Math.random();
            if (ifTest <= probability){
                trainDataList.add(rawDataElement);
            }

        }

        trainData = new String[trainDataList.size()][trainDataList.get(0).length];
        testData = new String[testDataList.size()][testDataList.get(0).length];

        for (int i = 0; i < trainData.length; i++) {
            trainData[i] = trainDataList.get(i);
        }

        for (int i = 0; i < testData.length; i++) {
            testData[i] = testDataList.get(i);
        }
        System.out.println("training sample size : " + trainDataList.size() + " testing sample size : " + testDataList.size() );

    }

    static void randomSampling3(String[][] rawData){
        ArrayList<String[]> trainDataList = new ArrayList<>();
        ArrayList<String[]> testDataList = new ArrayList<>();

        for (int i = 0; i < 4000; i++) {
            int num = (int)(Math.random() * rawData.length);
            trainDataList.add(rawData[num]);
        }

        for (int i = 0; i < 1000; i++) {
            int num = (int)(Math.random() * rawData.length);
            testDataList.add(rawData[num]);
        }


        trainData = new String[trainDataList.size()][trainDataList.get(0).length];
        testData = new String[testDataList.size()][testDataList.get(0).length];

        for (int i = 0; i < trainData.length; i++) {
            trainData[i] = trainDataList.get(i);
        }

        for (int i = 0; i < testData.length; i++) {
            testData[i] = testDataList.get(i);
        }
        System.out.println("training sample size : " + trainDataList.size() + " testing sample size : " + testDataList.size() );

    }

    static void randomSamplingProcess(String[] attrNames){
        String[][] rawData = readCSV("src/data/my_csv/freeze_frame_1.csv");
        double avg = 0;
        String recordPath;
        String samplingName = "RandomSampling1";
        for (int i = 0; i < 100; i++) {
            randomSampling(rawData);
//            randomSampling2(rawData);
//            randomSampling3(rawData);
            Map<Object, List<DecisionTree.Sample>> samples = DecisionTree.readSamples(attrNames,trainData);
            Object decisionTree = DecisionTree.generateDecisionTree(samples, attrNames);
            String outputPath = null;
//            outputPath = "src/data/result_txt/RandomSamplingDecisionTree.txt";
//            DecisionTree.outputFile(decisionTree, 0, null,outputPath);
            double precision = 0;
            precision = DecisionTree.testPrecision(testData,decisionTree);
            recordPath = "src/data/result_txt/record/"+samplingName+"Precision.txt";
            recordprecision(recordPath,precision);
            System.out.println("precision:"+precision);
            avg = avg + precision;
        }
        avg = avg / 100;
        System.out.println("avg precision:"+avg);
        recordPath = "src/data/result_txt/record/"+"AvgPrecision.txt";
        recordAvgPrecision(recordPath,avg,samplingName);
    }

    static void stratifiedSampling(){
        ArrayList<String[]> trainDataList = new ArrayList<>();
        ArrayList<String[]> testDataList = new ArrayList<>();
        String[] codeList = readFaultCode();
        Map<String,String[][]> codeDataMap = new HashMap<>();
        for (String code:
             codeList) {
            String[][] data = readCSV("src/data/my_csv/classify2/"+code+".csv");
            codeDataMap.put(code,data);
        }
        Set<Map.Entry<String, String[][]>> set = codeDataMap.entrySet();
        for (Map.Entry<String, String[][]> entry : set) {
            String[][] data = entry.getValue();
            if (data.length < 100){
                for (String[] record:
                     data) {
                    trainDataList.add(record);
                }
            } else {
                double probability = 0.6;
                for (String[] record:
                        data) {
                    double ifTest = Math.random();
                    if (ifTest > probability){
                        testDataList.add(record);
                    }
                    else {
                        trainDataList.add(record);
                    }
                }
            }
        }

        trainData = new String[trainDataList.size()][trainDataList.get(0).length];
        testData = new String[testDataList.size()][testDataList.get(0).length];

        for (int i = 0; i < trainData.length; i++) {
            trainData[i] = trainDataList.get(i);
        }

        for (int i = 0; i < testData.length; i++) {
            testData[i] = testDataList.get(i);
        }
        System.out.println("training sample size : " + trainDataList.size() + " testing sample size : " + testDataList.size() );




    }

    static String[] readFaultCode() {
        ArrayList<String[]> faultCodes = new ArrayList<String[]>();
        try {
            // 用来保存数据

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


        String[] codeList = null;
        if (faultCodes.size() == 0){
            return codeList;
        }
        codeList = new String[faultCodes.size()];
        for (int i = 0; i < faultCodes.size(); i++) {
            codeList[i] = faultCodes.get(i)[0];
        }
        return codeList;
    }

    static void stratifiedSamplingProcess(String[] attrNames){
        double avg = 0;
        String recordPath;
        String samplingName = "StratifiedSampling";
        Object decisionTree = null;
        String outputPath = null;
        for (int i = 0; i < 100; i++) {
            stratifiedSampling();
            Map<Object, List<DecisionTree.Sample>> samples = DecisionTree.readSamples(attrNames,trainData);
            decisionTree = DecisionTree.generateDecisionTree(samples, attrNames);
            double precision = 0;
            precision = DecisionTree.testPrecision(testData,decisionTree);
            recordPath = "src/data/result_txt/record/"+samplingName+"Precision.txt";
            recordprecision(recordPath,precision);
            System.out.println("precision:"+precision);
            avg = avg + precision;
        }
        outputPath = "src/data/result_txt/"+samplingName+"DecisionTree.txt";
        DecisionTree.outputFile(decisionTree, 0, null,outputPath);
        avg = avg / 100;
        System.out.println("avg precision:"+avg);
        recordPath = "src/data/result_txt/record/"+"AvgPrecision.txt";
        recordAvgPrecision(recordPath,avg,samplingName);
    }

    static void systematicSampling(){

    }

    static void systematicSamplingProcess(String[] attrNames){
        double avg = 0;
        String recordPath;
        String samplingName = "SystematicSampling";
        Object decisionTree = null;
        String outputPath = null;
        for (int i = 0; i < 100; i++) {
            systematicSampling();
            Map<Object, List<DecisionTree.Sample>> samples = DecisionTree.readSamples(attrNames,trainData);
            decisionTree = DecisionTree.generateDecisionTree(samples, attrNames);
            double precision = 0;
            precision = DecisionTree.testPrecision(testData,decisionTree);
            recordPath = "src/data/result_txt/record/"+samplingName+"Precision.txt";
            recordprecision(recordPath,precision);
            System.out.println("precision:"+precision);
            avg = avg + precision;
        }
        outputPath = "src/data/result_txt/"+samplingName+"DecisionTree.txt";
        DecisionTree.outputFile(decisionTree, 0, null,outputPath);
        avg = avg / 100;
        System.out.println("avg precision:"+avg);
        recordPath = "src/data/result_txt/record/"+"AvgPrecision.txt";
        recordAvgPrecision(recordPath,avg,samplingName);
    }

}
