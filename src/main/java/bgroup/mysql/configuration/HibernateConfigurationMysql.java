package bgroup.mysql.configuration;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.access.method.P;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "bgroup.mysql" })
@PropertySource(value = { "classpath:jdbc.mysql.properties" })
public class HibernateConfigurationMysql {
    static final Logger logger = LoggerFactory.getLogger(HibernateConfigurationMysql.class);
    @Autowired
    private Environment environment;

    @Bean(name = "sessionFactoryMysql")
    protected LocalSessionFactoryBean sessionFactoryMysql() {
        LocalSessionFactoryBean sessionFactoryMysql = new LocalSessionFactoryBean();
        sessionFactoryMysql.setDataSource(dataSourceMysql());
        sessionFactoryMysql.setPackagesToScan(new String[] { "bgroup.mysql.model" });
        sessionFactoryMysql.setHibernateProperties(hibernatePropertiesMysql());
        return sessionFactoryMysql;
     }

    @Bean(name="dataSourceMysql")
    protected DataSource dataSourceMysql() {
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
    @Autowired()
    protected HibernateTransactionManager transactionManagerMysql(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
/*
    @Autowired
    public ProgramaSgteDAOHibernate(@Qualifier("sessionFactory3") SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    */
}

