package com.yugi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Validation {

    static String[] attrNames = new String[] {
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

    public static void main(String[] args) throws Exception {
        kFoldCrossValidation(100);
//        double avg = 0;
//        for (int i = 0; i < 10; i++) {
//           avg += kFoldCrossValidation(10);
//        }
//        avg/=10;
//        System.out.println("final precision " + avg);
    }

    static double kFoldCrossValidation (int k){
        String[][] rawData;
//        rawData = Sampling.readCSV("src/data/my_csv/freeze_frame_1_proto.csv");
        rawData = Sampling.readCSV("src/data/my_csv/freeze_frame2_proto.csv");
        ArrayList<ArrayList<String[]>> arrayLists = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            arrayLists.add(new ArrayList<String[]>());
        }
        for (int i = 0; i < rawData.length; i++) {
            int remainder = i % k;
            arrayLists.get(remainder).add(rawData[i]);
        }
        double avg = 0;
        for (int i = 0; i < k; i++) {
            ArrayList<String[]> trainDataList = new ArrayList<>();
            ArrayList<String[]> testDataList = new ArrayList<>();

            for (int j = 0; j < k; j++) {
                ArrayList<String[]> arrayList = arrayLists.get(j);
                if (i == j){
                    testDataList = arrayList;
                }
                else {
                    for (String[] strings : arrayList) {
                        trainDataList.add(strings);
                    }
                }
            }
            Sampling.trainData = new String[trainDataList.size()][trainDataList.get(0).length];
            Sampling.testData = new String[testDataList.size()][testDataList.get(0).length];

            for (int l = 0; l < Sampling.trainData.length; l++) {
                Sampling.trainData[l] = trainDataList.get(l);
            }

            for (int l = 0; l < Sampling.testData.length; l++) {
                Sampling.testData[l] = testDataList.get(l);
            }
            System.out.println("training sample size : " + trainDataList.size() + " testing sample size : " + testDataList.size() );
            Map<Object, List<DecisionTree.Sample>> samples = DecisionTree.readSamples(attrNames,Sampling.trainData);
            Object decisionTree = DecisionTree.generateDecisionTree(samples, attrNames);
            double precision = 0;
            precision = DecisionTree2.testPrecision(Sampling.testData,decisionTree,attrNames);
            System.out.println("precision:"+precision);
            avg = avg + precision;
        }
        avg = avg / k;
        System.out.println("avg precision:"+avg);
        return avg;
    }

}
