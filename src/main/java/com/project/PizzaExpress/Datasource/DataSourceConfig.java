package com.project.PizzaExpress.Datasource;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
@ComponentScan
@MapperScan("com.project.PizzaExpress")//扫描有@Mapper注解的interface定义
public class DataSourceConfig {

        //logger引入
        //private static final Logger logger = Logger.getLogger(DataSourceConfig.class);
        @Autowired
        ApplicationContext applicationContext;//引入上下文
        @Autowired
        DataSourceProperties dataSourceProperties;//引入数据源参数

        // DataSource配置
        /**
      * "@Bean"注解就不多解释了，就是说把它当成一个Bean类来处理
      * "@ConfigurationProperties"注解会默认读取application.proerties文件中的spring.datasource配置，并自动赋值到DataSource中
      */
        @Bean
        public DataSource dataSource(){
                return new BasicDataSource();
        }

        // 提供SqlSession(数据库事务操作相关的配置)
        @Bean
        public SqlSessionFactory sqlSessionFactoryBean() throws Exception{
                SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();//创建SqlSessionFactoryBean类
                sqlSessionFactoryBean.setDataSource(dataSource());//设置数据库链接
                //如果你不想写mapper.xml文件来实现功能的话，下面两行可以注释。
                //PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                //sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/com/example/mapper/*.xml"));
                return sqlSessionFactoryBean.getObject();
        }

        /**
      * 事务配置引入
      * @return
      */
        @Bean
        public PlatformTransactionManager transactionManager(){
                return new DataSourceTransactionManager(dataSource());//事务声明
        }

}
