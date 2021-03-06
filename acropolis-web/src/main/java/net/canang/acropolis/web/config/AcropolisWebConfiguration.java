package net.canang.acropolis.web.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Configuration
@ComponentScan({"net.canang.acropolis.core", "net.canang.acropolis.biz", "net.canang.acropolis.web.controller"})
@PropertySource("classpath:app.properties")
@EnableWebMvc
@EnableTransactionManagement
public class AcropolisWebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages("net.canang.acropolis.core.model")
                .addProperties(hibernateProperties())
                .buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory());
        return hibernateTransactionManager;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.format_sql", "false");
        properties.put("javax.persistence.validation.mode", "none");
        properties.put("hibernate.search.default.directory_provider", "filesystem");
        properties.put("hibernate.search.default.indexBase", "/var/log/tomcat6/indexes/acropolis");
//        properties.put("hibernate.search.default.indexBase", "C:/Projects/GitHub/acropolis/acropolis-web/indexes");

        //properties.put("hibernate.connection.pool_size", "1");
        //properties.put("hibernate.format_sql", "true");
        //properties.put("hibernate.use_sql_comments", "true");
        //properties.put("hibernate.c3p0.min_size", "5");
        //properties.put("hibernate.c3p0.max_size", "20");
        //properties.put("hibernate.c3p0.timeout", "300");
        //properties.put("hibernate.c3p0.max_statements", "50");
        //properties.put("hibernate.c3p0.idle_test_period", "3000");
        //properties.put("hibernate.cache.use_second_level_cache", "true");
        //properties.put("hibernate.cache.region.factory_class","org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
        //properties.put("hibernate.cache.use_query_cache", "true");
        //properties.put("hibernate.cache.use_minimal_puts", "true");
        //properties.put("hibernate.max_fetch_depth", "10");
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername("cfi");
        dataSource.setPassword("abcd1234");
        dataSource.setUrl("jdbc:mysql://cfi.cwetwsccwmot.us-east-1.rds.amazonaws.com:3306/cfi?zeroDateTimeBehavior=convertToNull");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

//        dataSource.setUsername("cca");
//        dataSource.setPassword("abc123");
//        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
//        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(5);
        dataSource.setMaxWait(5000);
        return dataSource;
    }
}
