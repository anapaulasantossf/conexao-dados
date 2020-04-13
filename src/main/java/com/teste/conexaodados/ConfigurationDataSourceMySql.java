package com.teste.conexaodados;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableJpaRepositories(basePackages =
	{
			"com.teste.conexaodados.mysql.repository"
	},
	entityManagerFactoryRef = AppConstants.MYSQL_ENTITY_MANAGER,
	transactionManagerRef = AppConstants.MYSQL_TRANSACTION_MANAGER
)
public class ConfigurationDataSourceMySql extends ConfigurationDataSource{

	@Primary
	@Bean(name = AppConstants.MYSQL_DATA_SOURCE)
	public DataSource metadataDataSource() {

		HikariDataSource ds = DataSourceBuilder.create().type(HikariDataSource.class)
                .url(getJdbcMetadataUrl())
                .username(getJdbcMetadataUser())
                .password(getJdbcMetadataPass())
				.build();
		
		super.loadConnectionPoolConfigProperties(ds);

		return ds;
	}
	
	@Primary
	@Bean(name = AppConstants.MYSQL_ENTITY_MANAGER)
	public LocalContainerEntityManagerFactoryBean metadataEntityManager(EntityManagerFactoryBuilder builder,
			@Qualifier(AppConstants.MYSQL_DATA_SOURCE) DataSource dataSource) {
			return builder.dataSource(dataSource)
					.packages("com.teste.conexaodados.mysql.entity")
					.persistenceUnit("MetadataPersistenceUnit")
					.build();
	}


	@Primary
	@Bean(name = AppConstants.MYSQL_TRANSACTION_MANAGER)
	public PlatformTransactionManager metadataTransactionManager(
			@Qualifier(AppConstants.MYSQL_ENTITY_MANAGER) EntityManagerFactory mysqlManagerFactory) {
		return new JpaTransactionManager(mysqlManagerFactory);
	}

}
