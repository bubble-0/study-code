package com.magic.springboot.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsDemo {
    private static final String HDFS_PATH = "hdfs://192.168.111.121:8020";
    private static final String HDFS_USER = "root";
    private static FileSystem fileSystem;

    public static void main(String[] args) throws Exception {
        init();

        //mkdir();

        //copyFromLocalFile();

        //midirPermisson();

        //createFileAndwrite();

        //openFile();

        //renameFile();

        copyFromLocalBigFile();
        System.out.println(" succ!");

        close();
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

    //创建目录
    private static void mkdir() throws IOException {
        Path path = new Path("/test/mapreduceapp");
        if (fileSystem.exists(path)) {
            fileSystem.delete(path, true);
        }
        fileSystem.mkdirs(path);
    }

    //创建指定权限的目录
    private static void midirPermisson() throws IOException {
        Path path = new Path("/test/permissondir");
        System.out.println(fileSystem.exists(path));
        if (fileSystem.exists(path)) {
            fileSystem.delete(path, false);
        }
        boolean result = fileSystem.mkdirs(path, new FsPermission(FsAction.ALL, FsAction.READ, FsAction.READ));
        System.out.println(result);
    }

    //本地复制到远程节点
    public static void copyFromLocalFile() throws Exception {
        // 如果指定的是目录，则会把目录及其中的文件都复制到指定目录下
        Path src = new Path("D:\\magicmall\\magic-springboot\\target\\com.magic-1.0.0-SNAPSHOT.jar");
        Path dst = new Path("/test/mapreduceapp");
        fileSystem.copyFromLocalFile(src, dst);
    }

    // 创建文件并写入内容
    private static void createFileAndwrite() throws IOException {
        Path path = new Path("/test/111.txt");
        if (fileSystem.exists(path)) {
            fileSystem.delete(path, true);
        }

        FSDataOutputStream fsDataOutputStream = fileSystem.create(path);
        fsDataOutputStream.write("hello ".getBytes());
        fsDataOutputStream.write("hadoop ".getBytes());
        fsDataOutputStream.write("! ".getBytes());

        fsDataOutputStream.flush();
        fsDataOutputStream.close();
    }

    //读取文件内容
    private static void openFile() throws IOException {
        Path path = new Path("/test/111.txt");
        FSDataInputStream open = fileSystem.open(path);
        System.out.println(inputStreamToString(open, "UTF-8"));
    }

    //文件修改名字
    private static void renameFile() throws IOException {
        Path oldPath = new Path("/test/111.txt");
        Path newPath = new Path("/test/222.txt");
        fileSystem.rename(oldPath, newPath);
    }

    //上传大文件
    private static void copyFromLocalBigFile() throws IOException {

        File file = new File("D:\\hadoop\\hadoop-2.7.22222_jar.zip");
        final float fileSize = file.length();

        InputStream in = new BufferedInputStream(new FileInputStream(file));

        FSDataOutputStream out = fileSystem.create(new Path("/test/hadoop-2.7.22222_jar.zip"), new Progressable() {
            long fileCount = 0;
            @Override
            public void progress() {
                fileCount++;
                // progress 方法每上传大约 64KB 的数据后就会被调用一次
                System.out.println("上传进度：" + (fileCount * 64 * 1024 / fileSize) * 100 + " %");

            }

        });

        IOUtils.copyBytes(in, out, 4096);
    }

    //关闭
    private static void close() throws IOException {
        fileSystem.close();
    }

    /**
     * 把输入流转换为指定编码的字符
     *
     * @param inputStream 输入流
     * @param encode      指定编码类型
     */
    private static String inputStreamToString(InputStream inputStream, String encode) {
        try {
            if (encode == null || ("".equals(encode))) {
                encode = "utf-8";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encode));
            StringBuilder builder = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null) {
                builder.append(str).append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
