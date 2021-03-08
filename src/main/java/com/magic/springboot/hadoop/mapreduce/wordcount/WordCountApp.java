package com.magic.springboot.hadoop.mapreduce.wordcount;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WordCountApp {
    private final static String HDFS_URL = "hdfs://192.168.111.121:8020";
    private final static String HADOOP_USER_NAME = "root";

    /**
     * 读取文件
     * args[0] 输入文件地址
     * args[1] 输出文件地址
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ClassNotFoundException {
        //文件的输入路径和输出路径
        if (args.length != 2) {
            System.out.println("参数个数不正确!");
            return;
        }

        //指明用户名
        System.setProperty("HADOOP_USER_NAME", HADOOP_USER_NAME);

        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", HDFS_URL);

        Job job = Job.getInstance(configuration);
        job.setJarByClass(WordCountApp.class);

        //设置map类和reduce类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //设置map输出key的类型和value的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce输出的key类型和reduce类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileSystem fileSystem = FileSystem.get(new URI(HDFS_URL), configuration, HADOOP_USER_NAME);
        Path outputPath = new Path(args[1]);
        if (fileSystem.exists(outputPath)) {
            fileSystem.delete(outputPath, true);
        }

        // 设置作业输入文件和输出文件的路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, outputPath);

        // 将作业提交到群集并等待它完成，参数设置为 true 代表打印显示对应的进度
        boolean result = job.waitForCompletion(true);

        // 关闭之前创建的 fileSystem
        fileSystem.close();

        // 根据作业结果,终止当前运行的 Java 虚拟机,退出程序
        System.exit(result ? 0 : -1);
    }


}
