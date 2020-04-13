 package com.teste.conexaodados;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
        basePackages =  {
            "com.teste.conexaodados.oracle.repository"
        },
        entityManagerFactoryRef = AppConstants.ORACLE_ENTITY_MANAGER,
        transactionManagerRef = AppConstants.ORACLE_TRANSACTION_MANAGER
)
public class ConfigurationDataSourceOracle extends ConfigurationDataSource {

	@Bean(name = AppConstants.ORACLE_DATA_SOURCE)
	public DataSource entitiesDataSource() {
		
		DataSourceBuilder<HikariDataSource> dsBuilder = DataSourceBuilder.create().type(HikariDataSource.class)
                .url(getJdbcEntitiesUrl())
                .username(getJdbcEntitiesUser())
                .password(getJdbcEntitiesPass());
		
		HikariDataSource ds = dsBuilder.build();
		
		super.loadConnectionPoolConfigProperties(ds);

        return ds;
	}

	@Bean(name = AppConstants.ORACLE_ENTITY_MANAGER)
	public LocalContainerEntityManagerFactoryBean entitiesEntityManager(EntityManagerFactoryBuilder builder,
			@Qualifier(AppConstants.ORACLE_DATA_SOURCE) DataSource dataSource) {

		return builder.dataSource(dataSource)
				.packages("com.teste.conexaodados.oracle.entity")
				.persistenceUnit("EntityPersistenceUnit").build();
	}
	
	@Bean(name=AppConstants.ORACLE_TRANSACTION_MANAGER)
	public PlatformTransactionManager entitiesTransactionManager(
			@Qualifier(AppConstants.ORACLE_ENTITY_MANAGER) EntityManagerFactory oracleManagerFactory) {

		return new JpaTransactionManager(oracleManagerFactory);
	}

}
