package com.magic.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration //声明该类是一个java启动类，相当于一个xml配置文件
//@PropertySource("jdbc.properties") //声明properties的文件路径
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfig {
 /* 传统方式直接获取properties的值注入属性
    @Value("${dataSource.driverClassName}")
    private String driverClassName;

    @Value("${dataSource.url}")
    private String url;

    @Value("${dataSource.username}")
    private String username;

    @Value("${dataSource.password}")
    private String password;
*/
    //使用autowired注入
    //@Autowired
    //private JdbcProperties jdbcProperties;

    //使用构造函数注入
    //private JdbcProperties jdbcProperties;
    //
    //public JdbcConfig (JdbcProperties jdbcProperties) {
    //    this.jdbcProperties = jdbcProperties;

    //}

    /* //直接作用在方法上注入
    @Bean //把方法的返回值注入spring容器
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource dataSource () {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    } */

    //使用bean的形参的方式注入
    @Bean
    public DataSource dataSource (JdbcProperties jdbcProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setPassword(jdbcProperties.getPassword());
        return dataSource;
    }

/*  结合value注解使用
    @Bean
    public DataSource dataSource () {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setPassword(username);
        dataSource.setUsername(password);
        return dataSource;
    }
*/


}
