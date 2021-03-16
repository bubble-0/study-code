package com.magic.springboot.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsDemo {
    private static final String HDFS_PATH = "hdfs://192.168.3.43:8020";
    private static final String HDFS_USER = "root";
    private static FileSystem fileSystem;

    public static void main(String[] args) throws Exception {
        init();

        //mkdir();

        //copyFromLocalFile();
        //listFiles();
        //listFilesRecursive();
        getFileBlockLocations();
        System.out.println("upload succ!");
    }

    //初始化
    public static void init() {
        try {
            Configuration configuration = new Configuration();
            configuration.set("dfs.replication", "1");
            fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, HDFS_USER);
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

    public static void listFiles() throws IOException {
        FileStatus[] fileStatus = fileSystem.listStatus(new Path("/"));
        for (int i = 0; i < fileStatus.length; i++) {
            System.out.println(fileStatus[i]);
        }
    }

    public static void listFilesRecursive() throws IOException {
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("/"), true);
        while (files.hasNext()) {
            System.out.println(files.next());
        }
    }

    public static void getFileBlockLocations() throws IOException {
        FileStatus fileStatus = fileSystem.getFileStatus(new Path("/test/fxq_235_poc-1.0-SNAPSHOT.jar"));
        System.out.println(fileStatus.getLen());
        BlockLocation[] fileBlockLocations = fileSystem.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
        for (BlockLocation blockLocation : fileBlockLocations) {
            System.out.println(blockLocation);
        }
    }
}
