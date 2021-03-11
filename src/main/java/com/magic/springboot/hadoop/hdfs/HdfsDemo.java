package com.magic.springboot.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsDemo {
    private static final String HDFS_PATH = "hdfs://192.168.111.121:8020";
    private static final String HDFS_USER = "root";
    private static FileSystem fileSystem;

    public static void main(String[] args) throws Exception {
        init();

        mkdir();

        copyFromLocalFile();

        System.out.println("upload succ!");
    }

    //初始化
    public static void init() {
        try {
            Configuration configuration = new Configuration();
            configuration.set("dfs.replication", "1");
            fileSystem = FileSystem.get(new URI(HDFS_PATH),configuration,HDFS_USER);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mkdir() throws IOException {
        fileSystem.mkdirs(new Path("/test/mapreduceapp"));
    }

    public static void copyFromLocalFile() throws Exception {
        // 如果指定的是目录，则会把目录及其中的文件都复制到指定目录下
        Path src = new Path("D:\\magicmall\\magic-springboot\\target\\com.magic-1.0.0-SNAPSHOT.jar");
        Path dst = new Path("/test/mapreduceapp");
        fileSystem.copyFromLocalFile(src, dst);
    }
}
