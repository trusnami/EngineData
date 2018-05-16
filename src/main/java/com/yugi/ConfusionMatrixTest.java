package com.yugi;

import Model.ConfusionMatrix;

public class ConfusionMatrixTest {

    public static void main(String[] args) throws Exception {

        String[] attrNames = new String[]{"A", "B", "C", "D"};
        ConfusionMatrix matrix = new ConfusionMatrix(attrNames);
        matrix.updateMatrix("A","A");
        matrix.updateMatrix("A","A");
        matrix.updateMatrix("A","B");
        matrix.updateMatrix("A","B");
        matrix.updateMatrix("A","C");
        matrix.updateMatrix("A","C");
        matrix.updateMatrix("A","D");
        matrix.updateMatrix("A","D");
        matrix.updateMatrix("B","B");
        matrix.updateMatrix("B","B");
        matrix.updateMatrix("B","C");
        matrix.updateMatrix("B","C");
        matrix.updateMatrix("B","D");
        matrix.updateMatrix("B","D");
        matrix.updateMatrix("B","A");
        matrix.updateMatrix("B","A");
        matrix.updateMatrix("C","C");
        matrix.updateMatrix("C","C");
        matrix.updateMatrix("C","C");
        matrix.updateMatrix("C","C");
        matrix.updateMatrix("C","C");
        matrix.updateMatrix("C","B");
        matrix.updateMatrix("C","D");
        matrix.updateMatrix("D","D");
        matrix.updateMatrix("D","D");
        matrix.printMatrix();
        System.out.println(matrix.getAccuray());

    }
}
