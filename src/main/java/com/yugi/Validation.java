package com.yugi;

import Model.ConfusionMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Validation {







    public static void main(String[] args) throws Exception {
        String[] attrNames;
        attrNames = new String[] {
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
//  subgroup1
//        attrNames = new String[] {
//                "Speed",
//                "EngineSpeed",
//                "CoolantTemperature",
//                "EngineLoad",
//                "ManifoldAbsolutePressure",
//                "TorqueMode",
//                 };
//  subgroup2
        attrNames = new String[] {
                "Speed",
                "EngineSpeed",
                "CoolantTemperature",

                "BatteryVoltage",
                "RailPressure",
                "ComputationalLoadValue",
                "AtmosphericPressure",

                "IntakeAirTemperature",
                "AcceleratorPedal1Opening",
                "AcceleratorPedal2Opening",
                };
//  subgroup4
//        attrNames = new String[] {
//                "Speed",
//                "EngineSpeed",
//                "CoolantTemperature",
//                "AccelerationPedal1",
//                "AmbientTemperature",
//                "BatteryVoltage",
//                "RailPressure",
//                "ComputationalLoadValue",
//                "AtmosphericPressure",
//                "ManifoldAbsolutePressure",
//                 };
//  subgroup5
//        attrNames = new String[] {
//                "Speed",
//                "EngineSpeed",
//                "CoolantTemperature",
//
//                "BatteryVoltage",
//
//                "ComputationalLoadValue",
//                "AtmosphericPressure",
//
//                "ManifoldAbsolutePressure",
//
//                "AcceleratorPedalSensor1Signal",
//                 };
//  subgroup6
//        attrNames = new String[] {
//                "Speed",
//                "EngineSpeed",
//                "CoolantTemperature",
//                "AccelerationPedal1",
//                "AmbientTemperature",
//                "ManifoldAbsolutePressure",
//                "MonitoringModuleVoltage"
//        };
        kFoldCrossValidation(10,attrNames);
//        double avg = 0;
//        for (int i = 0; i < 10; i++) {
//           avg += kFoldCrossValidation(10);
//        }
//        avg/=10;
//        System.out.println("final precision " + avg);
    }

    static double kFoldCrossValidation (int k, String[] attrNames){
        String[][] rawData;
//        rawData = Sampling.readCSV("src/data/my_csv/freeze_frame_1_proto.csv");
//        rawData = Sampling.readCSV("src/data/my_csv/freeze_frame2_proto.csv");
//        rawData = Sampling.readCSV("src/data/my_csv/classify7/subgroup1.csv");
        rawData = Sampling.readCSV("src/data/my_csv/classify6/subgroup2.csv");
        ArrayList<ArrayList<String[]>> arrayLists = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            arrayLists.add(new ArrayList<String[]>());
        }
        for (int i = 0; i < rawData.length; i++) {
            int remainder = i % k;
            arrayLists.get(remainder).add(rawData[i]);
        }
        double accuracy = 0;
        double recall = 0;
        double precision = 0;
        double f1 = 0;
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

            ConfusionMatrix matrix;
            matrix = DecisionTree2.testPrecision(Sampling.testData,decisionTree,attrNames);


            System.out.println("acccruracy:"+matrix.getAccuray()+
                    " recall:"+matrix.getAvgRecall()+
                    " precision:"+matrix.getAvgPrecision()+
                    " F1:"+matrix.getAvgF1());
            System.out.println();
            if (!Double.isNaN(matrix.getAccuray())) {
                accuracy = accuracy + matrix.getAccuray();
            }
            if (!Double.isNaN(matrix.getAvgRecall())) {
                recall = recall + matrix.getAvgRecall();
            }
            if (!Double.isNaN(matrix.getAvgPrecision())) {
                precision = precision + matrix.getAvgPrecision();
            }
            if (!Double.isNaN(matrix.getAvgF1())) {
                f1 = f1 + matrix.getAvgF1();
            }
        }
        accuracy = accuracy / k;
        recall = recall/ k;
        precision = precision / k;
        f1 = f1  / k;
        System.out.println("avg accuracy:"+accuracy + " avg recall:"+recall
                + " avg precision:"+precision+ " avg f1:"+f1);
        return accuracy;
    }

}
