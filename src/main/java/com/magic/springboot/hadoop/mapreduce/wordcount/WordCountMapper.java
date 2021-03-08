package com.magic.springboot.hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        System.out.println("map key" + key);
        System.out.println("map value"+ value);
        System.out.println("map context" + context);

        String[] words = value.toString().split("\t");
        for (int i = 0; i < words.length; i++) {
            context.write(new Text(words[i]), new IntWritable(1));
        }
    }
}
