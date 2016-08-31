package SearchForCarShowroom.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by Alexandr on 15.05.2016.
 */
@Configuration
@ComponentScan(basePackages = "SearchForCarShowroom.*")
@EnableTransactionManagement
@PropertySource("classpath:db_config.properties")
public class SpringConfig {
    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/TestDB?useUnicode=true&amp;characterEncoding=UTF-8");
//        dataSource.setUsername("root");
//        dataSource.setPassword("nba777");
//        dataSource.setInitialSize(5);
//        dataSource.setMaxActive(10);
        dataSource.setDriverClassName(environment.getProperty("DataSource.DriverClassName", String.class));
        dataSource.setUrl(environment.getProperty("DataSource.URL", String.class));
        dataSource.setUsername(environment.getProperty("DataSource.Username", String.class));
        dataSource.setPassword(environment.getProperty("DataSource.Password", String.class));
        dataSource.setInitialSize(environment.getProperty("DataSource.InitialSize", Integer.class));
        dataSource.setMaxActive(environment.getProperty("DataSource.MaxActive", Integer.class));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("SearchForCarShowroom.*");
        sessionFactory.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new HibernateTransactionManager(sessionFactory().getObject());
    }
}
