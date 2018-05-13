package com.yugi;

import apriori4j.*;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import java.util.*;

public class AprioriTest {

    public static void main(String args[])  {

        List<Transaction> transactions = null;
        try {
            transactions = getTransactions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Double minSupport = 0.15;
        Double minConfidence = 0.6;

        AprioriAlgorithm apriori = new AprioriAlgorithm(minSupport, minConfidence);
        try {
            AnalysisResult result = apriori.analyze(transactions);
        } catch (AprioriTimeoutException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> getTransactions() throws IOException {
        List<String> lines = FileUtils.readLines(new File("src/data/example_csv/dataset.csv"));
        List<Transaction> transactions = new ArrayList<Transaction>();
        for (String line : lines) {
            Set<String> items = new HashSet<String>();
            for (String item : line.split(",")) {
                items.add(item);
            }
            transactions.add(new Transaction(items));
        }
        return transactions;
    }


}
