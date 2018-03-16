package com.example.demo;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class SpringBootRestCrudWebMVCAppApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootRestCrudWebMVCAppApplication.class);

	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestCrudWebMVCAppApplication.class, args);
		// ApplicationContext actx =
		// SpringApplication.run(SpringBootRestCrudAppApplication.class, args);
		logger.info(CommonConstants.APP_NAME_FOR_LOG + "Welcome info log message");
	}

	/**
	 * This method is used to identify which datasource is utilitzing.
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.info(CommonConstants.APP_NAME_FOR_LOG + "DATASOURCE = " + dataSource);
	}
}

// Please follow the below code, if you want use the application as a war file
// instead of jar file

// public class SpringBootRestCrudAppApplication extends
// SpringBootServletInitializer{
//
// private static final Logger logger =
// LoggerFactory.getLogger(SpringBootRestCrudAppApplication.class);
// @Override
// public SpringApplicationBuilder configure(SpringApplicationBuilder
// application) {
// return application.sources(SpringBootRestCrudAppApplication.class);
// }
//
// public static void main(String[] args) {
// SpringApplication.run(SpringBootRestCrudAppApplication.class, args);
// // ApplicationContext actx =
// // SpringApplication.run(SpringBootRestCrudAppApplication.class, args);
// logger.info(CommonConstants.APP_NAME_FOR_LOG + "Welcome info log message");
//
//// /*
//// * Perform the below changes in pom.xml
//// *
//// * <packaging>war</packaging>
//// * Add main start class of the spring boot
//// *
//
//// * <properties>
//// <start-class>com.example.demo.SpringBootRestCrudAppApplication</start-class>
//// </properties>
//// (or) Define the main class here
//// <build>
//// <plugins>
//// <plugin>
//// <groupId>org.springframework.boot</groupId>
//// <artifactId>spring-boot-maven-plugin</artifactId>
//// <configuration>
//// <mainClass>com.example.demo.SpringBootRestCrudAppApplication</mainClass>
//// </configuration>
//// </plugin>
//// </plugins>
//// </build>
////
////
//// * <!-- marked the embedded servlet container as provided -->
//// <dependency>
//// <groupId>org.springframework.boot</groupId>
//// <artifactId>spring-boot-starter-tomcat</artifactId>
//// <scope>provided</scope>
//// </dependency>
//// * */
//// Execute mvn clean package command
// }
// }
