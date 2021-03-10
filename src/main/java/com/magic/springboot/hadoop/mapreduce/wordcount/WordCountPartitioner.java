package com.magic.springboot.hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.Arrays;

public class WordCountPartitioner extends Partitioner<Text, IntWritable> {
    @Override
    public int getPartition(Text text, IntWritable intWritable, int i) {
        String[] arr = new String[]{"Flink", "HBase", "Hadoop", "Hive", "Spark", "Storm"};
        return Arrays.asList(arr).indexOf(text.toString());
    }
}
