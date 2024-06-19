package com.mysite.sbb.dbConfig;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.mysite.sbb.user",
        entityManagerFactoryRef = "thirdEntityManager",
        transactionManagerRef = "thirdTransactionManager"
)
public class thirdConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource thirdDataSource() {
    	HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder.create().build();
        dataSource.setConnectionTestQuery("values 1");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean thirdEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(thirdDataSource());
        em.setPackagesToScan(new String[] {"com.mysite.sbb.user"});
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        em.setJpaVendorAdapter(vendorAdapter);
        
        HashMap<String, Object> prop = new HashMap<>();
        prop.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        prop.put("hibernate.hbm2ddl.auto", "none");
        prop.put("hibernate.format_sql", true);
        em.setJpaPropertyMap(prop);
        
        return em;
    }

    @Bean
    public PlatformTransactionManager thirdTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(thirdEntityManager().getObject());
        return transactionManager;
    }
}
