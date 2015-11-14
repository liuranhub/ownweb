package org.weblr.app;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component
@Configurable
@EnableWebMvc
public class WebConfig {

	public WebConfig() {
		System.out.println("初始化 web 应用程序");
	}

	public @Bean ComboPooledDataSource createDataSource4Mysql() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		Properties properties = loadProperties("c3p0.properties") ;
		
		String username = properties.getProperty("c3p0.user");
		String password = properties.getProperty("c3p0.password") ;
		String driver = properties.getProperty("c3p0.driverClass");
		String url = properties.getProperty("c3p0.jdbcUrl") ;

		try {
			comboPooledDataSource.setDriverClass(driver);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		comboPooledDataSource.setUser(username);
		comboPooledDataSource.setPassword(password);
		comboPooledDataSource.setJdbcUrl(url);
		
		return comboPooledDataSource;
	}

	private Properties loadProperties(String path) {
		Properties properties = new Properties();
		try {
			InputStream is = this.getClass().getResourceAsStream(path);
			properties.load(is);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return properties ;
	}
}
