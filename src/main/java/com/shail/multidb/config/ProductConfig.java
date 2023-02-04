package com.shail.multidb.config;

import com.google.common.base.Preconditions;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.shail.multidb.prd",
        entityManagerFactoryRef = "productEntityManager",
        transactionManagerRef = "productTransactionManager"
)
public class ProductConfig {

    @Autowired
    private Environment env;

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean productEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(productDataSource());
        em.setPackagesToScan("com.shail.multidb.prd");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public DataSource productDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(Preconditions.checkNotNull(env.getProperty("mysql.datasource.driver_class_name")));
        config.setJdbcUrl(env.getProperty("prd.datasource.jdbcUrl"));
        config.setUsername(env.getProperty("prd.datasource.username"));
        config.setPassword(env.getProperty("prd.datasource.password"));
        config.setMaximumPoolSize(Integer.parseInt(env.getProperty("hikariCP.datasource.maximum_pool_size")));
        config.setMaxLifetime(Long.parseLong(env.getProperty("hikariCP.datasource.max_lifetime")));
        config.setPoolName(env.getProperty("hikariCP.datasource.pool_name"));
        config.setConnectionTimeout(Long.parseLong(env.getProperty("hikariCP.datasource.connection_timeout")));
        config.setMinimumIdle(Integer.parseInt(env.getProperty("hikariCP.datasource.minimum_idle")));
        config.setIdleTimeout(Long.parseLong(env.getProperty("hikariCP.datasource.idle_timeout")));

        return new HikariDataSource(config);
    }

    @Bean
    @Primary
    public PlatformTransactionManager productTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(productEntityManager().getObject());
        return transactionManager;
    }
}
