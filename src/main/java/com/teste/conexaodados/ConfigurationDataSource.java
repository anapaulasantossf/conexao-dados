package com.teste.conexaodados;

import org.springframework.beans.factory.annotation.Value;

import com.zaxxer.hikari.HikariDataSource;

public class ConfigurationDataSource {
	
    @Value("${jdbc.oracle.url}")
    private String jdbcEntitiesUrl;
    
    @Value("${jdbc.oracle.user}")
    private String jdbcEntitiesUser;
    
    @Value("${jdbc.oracle.pass}")
    private String jdbcEntitiesPass;
    
    @Value("${jdbc.mysql.url}")
    private String jdbcMetadataUrl;
    
    @Value("${jdbc.mysql.user}")
    private String jdbcMetadataUser;
    
    @Value("${jdbc.mysql.pass}")
    private String jdbcMetadataPass;
    
    @Value("${config.datasource.maximumPoolSize}")
    private Integer maximumPoolSize;
    
    @Value("${config.datasource.minimumIdle}")
    private Integer minimumIdle;
    
    @Value("${config.datasource.idleTimeout}")
    private Integer idleTimeout;
    
    @Value("${jdbc.stage.url}")
    private String jdbcStageUrl;
    
   
    public String getJdbcEntitiesUrl() {
		return jdbcEntitiesUrl;
	}


	public void setJdbcEntitiesUrl(String jdbcEntitiesUrl) {
		this.jdbcEntitiesUrl = jdbcEntitiesUrl;
	}


	public String getJdbcEntitiesUser() {
		return jdbcEntitiesUser;
	}


	public void setJdbcEntitiesUser(String jdbcEntitiesUser) {
		this.jdbcEntitiesUser = jdbcEntitiesUser;
	}


	public String getJdbcEntitiesPass() {
		return jdbcEntitiesPass;
	}


	public void setJdbcEntitiesPass(String jdbcEntitiesPass) {
		this.jdbcEntitiesPass = jdbcEntitiesPass;
	}


	public String getJdbcMetadataUrl() {
		return jdbcMetadataUrl;
	}


	public void setJdbcMetadataUrl(String jdbcMetadataUrl) {
		this.jdbcMetadataUrl = jdbcMetadataUrl;
	}


	public String getJdbcMetadataUser() {
		return jdbcMetadataUser;
	}


	public void setJdbcMetadataUser(String jdbcMetadataUser) {
		this.jdbcMetadataUser = jdbcMetadataUser;
	}


	public String getJdbcMetadataPass() {
		return jdbcMetadataPass;
	}


	public void setJdbcMetadataPass(String jdbcMetadataPass) {
		this.jdbcMetadataPass = jdbcMetadataPass;
	}


	public Integer getMaximumPoolSize() {
		return maximumPoolSize;
	}


	public void setMaximumPoolSize(Integer maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}


	public Integer getMinimumIdle() {
		return minimumIdle;
	}


	public void setMinimumIdle(Integer minimumIdle) {
		this.minimumIdle = minimumIdle;
	}


	public Integer getIdleTimeout() {
		return idleTimeout;
	}


	public void setIdleTimeout(Integer idleTimeout) {
		this.idleTimeout = idleTimeout;
	}


	public String getJdbcStageUrl() {
		return jdbcStageUrl;
	}


	public void setJdbcStageUrl(String jdbcStageUrl) {
		this.jdbcStageUrl = jdbcStageUrl;
	}


	protected void loadConnectionPoolConfigProperties(HikariDataSource ds) {
        ds.setMaximumPoolSize(maximumPoolSize);
        ds.setMinimumIdle(minimumIdle);
        ds.setIdleTimeout(idleTimeout);
    }
}
