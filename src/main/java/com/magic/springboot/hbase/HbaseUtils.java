package com.magic.springboot.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

public class HbaseUtils {

    private static Connection connection;

    static {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hmaster");
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建hbase 表
     */
    public static boolean createTable(String tableName, List<String> columnFamilies) {
        try {
            HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();

            if (admin.tableExists(TableName.valueOf(tableName))) {
                return false;
            }

            TableDescriptorBuilder tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));

            columnFamilies.forEach(columnFamily -> {
                ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamily));
                columnFamilyDescriptorBuilder.setMaxVersions(1);
                ColumnFamilyDescriptor build = columnFamilyDescriptorBuilder.build();
                tableDescriptor.setColumnFamily(build);
            });

            admin.createTable(tableDescriptor.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 删除habse表
     */


}
