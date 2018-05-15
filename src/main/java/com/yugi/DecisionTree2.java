package com.yugi;

import java.util.List;
import java.util.Map;

import static com.yugi.DecisionTree.generateDecisionTree;
import static com.yugi.DecisionTree.readCSV;
import static com.yugi.DecisionTree.readSamples;

public class DecisionTree2 {


    public static void main(String[] args) throws Exception {
//        System.out.print("测试结果：");
        String[] attrNames = null;
        attrNames = new String[]{"Speed",
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
                "MonitoringModuleVoltage"};
//        double[] data;
//        data = new double[] {25.5, 4340, 1, 0 ,0, 0, 0, 0, 0, 5, 15 ,0, 0, 0, 0, 0, 0, 0
//
//        };
        Object[][] rawData = readCSV();
        // 读取样本集
        Map<Object, List<DecisionTree.Sample>> samples = DecisionTree.readSamples(attrNames, rawData);
        // 生成决策树
        Object decisionTree = DecisionTree.generateDecisionTree(samples, attrNames);
        String path = "src/data/my_csv/test.csv";
        String[][] testData = DecisionTree.readTestCSV(path);
        for (String[] data:
             testData) {
            decide(data, decisionTree,attrNames);
        }
    }

    /**
     * 读取测试数据集，并返回决策准确率
     */

    static double testPrecision(String[][] testData, Object decisionTree, String[] attrNames){
        double rightCode = 0;
        String faultCode = null;
        for (String[] data:
                testData) {
//            System.out.println(data[18]);
            faultCode = decide(data, decisionTree,attrNames);
//            System.out.println("faultCode:"+faultCode);
            if (faultCode.equals(data[data.length-1])){
                rightCode++;
            }
        }

        return 100 * rightCode/testData.length;
    }

    /**
     * 扫描决策树进行决策
     */

    static String decide(String[] data ,Object decisionTree, String[] attrNames){

//
        String faultCode = scanRecursive(decisionTree, 0, null, data, attrNames);
        System.out.println("decide faultCode: "+faultCode);
        return faultCode;
    }

    static String scanRecursive(Object obj, int level, Object from, String[] data, String[] attrNames) {
        String result = "unknown fault";
        if (from != null)
            System.out.printf("(%s):", from);
        if (obj instanceof DecisionTree.Tree) {
            DecisionTree.Tree tree = (DecisionTree.Tree) obj;
            String attrName = tree.getAttribute();
            int num = 0;

            for (int i = 0; i < attrNames.length; i++) {
                if (attrName.equals(attrNames[i])) {
                    num = i;
                    break;
                }
            }


            for (Object attrValue : tree.getAttributeValues()) {
                Object child = tree.getChild(attrValue);
                if (data[num].equals(attrValue)) {
                    result = scanRecursive(child, level, attrName + " = "
                            + attrValue, data, attrNames);
                }

            }

            return result;
        } else {
            String a = (String) obj;
            return a;
        }
    }
}
