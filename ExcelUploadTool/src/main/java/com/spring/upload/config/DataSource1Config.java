package com.spring.upload.config;
 
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
 
@Configuration
// Load to Environment
// (@see resources/datasource-cfg.properties).
@PropertySources({ @PropertySource("classpath:datasource-cfg.properties") })
@EnableJpaRepositories(
	    basePackages = "com.spring.upload.repository", 
	    entityManagerFactoryRef = "gpartsEntityManager", 
	    transactionManagerRef = "gpartsTransactionManager"
	)
public class DataSource1Config {
 
    @Autowired
    private Environment env; // Contains Properties Load by @PropertySources
 
    @Bean
    public DataSource gpartsDatasource() {
 
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.gparts.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.gparts.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.gparts.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.gparts.datasource.password"));
 
        return dataSource;
    }
 
    @Bean
    public LocalContainerEntityManagerFactoryBean gpartsEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(gpartsDatasource());
 
        // Scan Entities in Package:
        em.setPackagesToScan(new String[] { Constants.PACKAGE_GPARTS });
        em.setPersistenceUnitName(Constants.JPA_UNIT_NAME_1); // Important !!
 
        //
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
 
        em.setJpaVendorAdapter(vendorAdapter);
 
        HashMap<String, Object> properties = new HashMap<>();
 
        // JPA & Hibernate
        properties.put("hibernate.show-sql", env.getProperty("spring.gparts.jpa.show-sql"));
        properties.put("hibernate.dialect", env.getProperty("spring.gparts.jpa.properties.hibernate.dialect"));
 
 
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }
 
    @Bean
    public PlatformTransactionManager gpartsTransactionManager() {
 
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(gpartsEntityManager().getObject());
        return transactionManager;
    }
 
}