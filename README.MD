Features included

## This application Login details please see the SpringSecurityConfig.java (or) users database
john/secret - USER, ADMIN
peter/secret - USER

1. Logging Configuration
2. Spring Profiles (Dev, Production and common properties)
3. Spring JDBC
4. Spring DAO
5. Spring services
6. RESTFUL CRUD Operations
7. Spring security
8. Spring boot actuator
9. Spring boot Custom HealthCheck
10. Custom properties using java configuration
11. Generic Exception Handling
12. Changing jar file to war file code in SpringBootRestCrudAppApplication.java file

Required Tools : 

1. Eclipse (oxygen) with Spring tools, git, maven plugins
2. GIT installation in location
3. GITHub Repository
4. PostMan
5. MYSQL Database (6+): database name : springboot (see admin.properties file for more information)
6. Java 1.8
7. Install JSON Viewer in your browser


################ Spring Boot Web MVC app notes. ###################

1. Spring controller should be annotated with @Controller (Do not use @RestController
2. POM.XML changes
   1. Change packaging to war file <packaging>war</packaging>
   2. Add spring boot starter tomcat as provided means you are externally providing the server
   3. Add jstl and tomcat-embed-jasper dependencies for compiling the jsp

3. Perform all the changes to viewresolvers in the WebMvcConfig.java file which extends WebMvcConfigurer
	
}


############## Tips ##############
1. Use TCPView to kill the existing ports running for tomcat server.
