package Model;

import java.util.ArrayList;

public class ConfusionMatrix {

    double[][] matrix;
    String[] attrArray;
    int length;

    public ConfusionMatrix(String[] attrNames){
        attrArray = attrNames;
        length = attrNames.length;

        matrix = new double[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public ConfusionMatrix(ArrayList<String> attrNames){
        length = attrNames.size();
        attrArray = new String[length];
        for (int i = 0; i < length; i++) {
            attrArray[i] = attrNames.get(i);
        }
        matrix = new double[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = 0;
            }
        }

    }

    public void updateMatrix(String actualCode, String predictCode){

        int actualCodeNum = 0;
        int predictCodeNum = 0;

        for (int i = 0; i < length; i++) {
            if (actualCode.equals(attrArray[i])) {
                actualCodeNum = i;
                break;
            }
        }

        for (int i = 0; i < length; i++) {
            if (predictCode.equals(attrArray[i])) {
                predictCodeNum = i;
                break;
            }
        }

        matrix[actualCodeNum][predictCodeNum]++;

    }

    public void printMatrix(){
        System.out.println();

        for (int i = 0; i < length; i++) {
            System.out.print(attrArray[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public double[] getRecall(){

        double[] results = new double[length];
        for (double result:
             results) {
            result = 0;
        }

        for (int i = 0; i < length; i++) {
            results[i] = matrix[i][i] / getRowSum(i);
        }

        return results;
    }

    public double getAvgRecall(){

        double result = 0;
        double[] recall = getRecall();

        for (int i = 0; i < length; i++) {
            result += recall[i];
        }

        return result/length;
    }

    public double getAccuray(){
        double result = 0;

        for (int i = 0; i < length; i++) {
            result+=matrix[i][i];
        }

        result/=getSum();

        return result;
    }

    public double[] getFPrate(){
        double[] results = new double[length];
        for (double result:
                results) {
            result = 0;
        }

        for (int i = 0; i < length; i++) {
            results[i] = getExceptElementColSum(i) / getExceptRowSum(i);
        }

        return results;

    }

    public double[] getPrecision(){

        double[] results = new double[length];
        for (double result:
                results) {
            result = 0;
        }

        for (int i = 0; i < length; i++) {
            if (getColSum(i)!=0) {
                results[i] = matrix[i][i] / getColSum(i);
            }
        }

        return results;
    }

    public double getAvgPrecision(){

      double result = 0;
      double[] precision = getPrecision();

        for (int i = 0; i < length; i++) {
            result+=precision[i];
        }

        return result/length;
    }

    public double[] getF1(){
        double[] results = new double[length];
        for (double result:
                results) {
            result = 0;
        }

        double[] recall = getRecall();
        double[] precision = getPrecision();

        for (int i = 0; i < length; i++) {
//            System.out.println("recall i:" + recall[i] + " precision i:" + precision[i]);
        }

        for (int i = 0; i < length; i++) {
            results[i] = 2 * (recall[i]) * (precision[i]) / (recall[i] + precision[i]);
            if (Double.isNaN(results[i])) {
//                System.out.println("f1 i:" + results[i]);
                results[i] = 0;
            }
        }

        return results;

    }

    public double[] getF1(double[] recall, double[] precision){
        double[] results = new double[length];
        for (double result:
                results) {
            result = 0;
        }

        for (int i = 0; i < length; i++) {
            results[i] = 2 * (recall[i]) * (precision[i]) / (recall[i] + precision[i]);
        }

        return results;

    }

    public double getAvgF1(){
        double result = 0;
        double[] f1 = getF1();
        for (int i = 0; i < length; i++) {
            result+=f1[i];
        }

        return result/length;


    }

    private double getRowSum(int row){
        double result = 0;

        for (int i = 0; i < length; i++) {
            result+=matrix[row][i];
        }
        return result;
    }

    private double getColSum(int col){
        double result = 0;

        for (int i = 0; i < length; i++) {
            result+=matrix[i][col];
        }

        return result;
    }

    private double getExceptRowSum(int row){

        double result = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != row) {
                    result += matrix[i][j];
                }
            }
        }

        return result;

    }

    private double getExceptElementColSum(int col){
        double result = 0;

        for (int i = 0; i < length; i++) {
            if (i != col){
                result+=matrix[i][col];
            }

        }

        return result;
    }

    private double getSum(){
        double result = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result+=matrix[i][j];
            }
        }

        return result;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public String[] getAttrArray() {
        return attrArray;
    }

    public void setAttrArray(String[] attrArray) {
        this.attrArray = attrArray;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
