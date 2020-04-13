 package com.teste.conexaodados;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;


import com.zaxxer.hikari.HikariDataSource;


@Configuration
public class ConfigurationDataSourceDynamic extends ConfigurationDataSource {

    public DataSource stageDataSource(String user, String pass) {
        
        DataSourceBuilder<HikariDataSource> dsBuilder = DataSourceBuilder.create().type(HikariDataSource.class)
                .url(getJdbcStageUrl())
                .username(user)
                .password(pass);
        
        HikariDataSource ds = dsBuilder.build();
        
        super.loadConnectionPoolConfigProperties(ds);

        return ds;
    }
    
}
