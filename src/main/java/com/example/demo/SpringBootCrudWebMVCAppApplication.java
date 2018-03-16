package com.example.demo;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.example.demo.common.CommonConstants;

/**
 * Spring Boot Rest application with Spring security either using jar or war
 * file. Code needs to modified for war file
 * 
 * CommandLineRunner is used only it implement run method for identifying the
 * datasource using in this application
 * 
 * @author
 *
 */
@SpringBootApplication
public class SpringBootCrudWebMVCAppApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootCrudWebMVCAppApplication.class);

	@Autowired
	private DataSource datasource;

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootCrudWebMVCAppApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudWebMVCAppApplication.class, args);
		// ApplicationContext actx =
		// SpringApplication.run(SpringBootRestCrudAppApplication.class, args);
		logger.info(
				CommonConstants.APP_NAME_FOR_LOG + "Welcome to  SpringBootRestCrudWebMVCAppApplication log message");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.info(CommonConstants.APP_NAME_FOR_LOG + "Datasource Name:" + datasource);
	}
}

// Please follow the below code, if you want use the application as a war file
// instead of jar file

// public class SpringBootRestCrudWebMVCAppApplication implements
// CommandLineRunner {
//
// private static final Logger logger =
// LoggerFactory.getLogger(SpringBootRestCrudWebMVCAppApplication.class);
//
// @Autowired
// DataSource dataSource;
//
// public static void main(String[] args) {
// SpringApplication.run(SpringBootRestCrudWebMVCAppApplication.class, args);
// // ApplicationContext actx =
// // SpringApplication.run(SpringBootRestCrudAppApplication.class, args);
// logger.info(CommonConstants.APP_NAME_FOR_LOG + "Welcome info log message");
// }
//
// /**
// * This method is used to identify which datasource is utilitzing.
// */
// @Override
// public void run(String... args) throws Exception {
// logger.info(CommonConstants.APP_NAME_FOR_LOG + "DATASOURCE = " + dataSource);
// }
// }
