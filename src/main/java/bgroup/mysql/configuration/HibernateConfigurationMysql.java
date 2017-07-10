package bgroup.mysql.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "bgroup.mysql.configuration" })
@PropertySource(value = { "classpath:jdbc.properties" })
public class HibernateConfigurationMysql {

    @Autowired
    private Environment environment;

    @Bean(name = "sessionFactory")
    protected LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryMysql = new LocalSessionFactoryBean();
        sessionFactoryMysql.setDataSource(dataSourceMysql());
        sessionFactoryMysql.setPackagesToScan(new String[] { "bgroup.mysql.model" });
        sessionFactoryMysql.setHibernateProperties(hibernatePropertiesMysql());
        return sessionFactoryMysql;
     }

    @Bean
    public DataSource dataSourceMysql() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.mysql.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.mysql.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.mysql.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.mysql.password"));
        return dataSource;
    }
    
    private Properties hibernatePropertiesMysql() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.mysql.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.mysql.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.mysql.format_sql"));
        properties.put("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.mysql.hbm2ddl"));
        return properties;        
    }


	@Bean
    @Autowired
    protected HibernateTransactionManager transactionManagerMysql(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }

}

