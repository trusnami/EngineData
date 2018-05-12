package com.yugi;

import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String[][] rawData = readCSV("src/data/my_csv/freeze_frame_1.csv");

        randomSamplingProcess(rawData,attrNames);

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
        System.out.println("training sample size : " + trainDataList.size() + "testing sample size : " + testDataList.size() );
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
        System.out.println("training sample size : " + trainDataList.size() + "testing sample size : " + testDataList.size() );



    }

    static void randomSamplingProcess(String[][] rawData, String[] attrNames){
        double avg = 0;
        for (int i = 0; i < 100; i++) {
            randomSampling(rawData);
            Map<Object, List<DecisionTree.Sample>> samples = DecisionTree.readSamples(attrNames,trainData);
            Object decisionTree = DecisionTree.generateDecisionTree(samples, attrNames);
            String outputPath = null;
//            outputPath = "src/data/result_txt/RandomSamplingDecisionTree.txt";
//            DecisionTree.outputFile(decisionTree, 0, null,outputPath);
            double precision = 0;
            precision = DecisionTree.testPrecision(testData,decisionTree);
            String recordPath;
            recordPath = "src/data/result_txt/record/RandomSamplingPrecision.txt";
            recordprecision(recordPath,precision);
            System.out.println("precision:"+precision);
            avg = avg + precision;
        }
        avg = avg / 100;
        System.out.println("avg precision:"+avg);
    }

}
