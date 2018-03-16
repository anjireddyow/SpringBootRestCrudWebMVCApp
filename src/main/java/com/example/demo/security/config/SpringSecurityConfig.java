package com.example.demo.security.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.example.demo.common.CommonConstants;
import com.example.demo.controller.MySimpleUrlAuthenticationSuccessHandler;
import com.example.demo.exception.handler.CustomAccessDeniedHandler;
import com.example.demo.service.CustomerUserDetailsService;

/**
 * From Spring security 5 onwards. It is required to provide default password
 * encoder for spring security
 * 
 * @author
 *
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	private CustomerUserDetailsService customUserDetailsService;

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	/**
	 * This will return user home page or admin home page after success
	 * authentication and based on the roles of either user or admin
	 * 
	 * @return
	 */
	private AuthenticationSuccessHandler successHandler() {
		return new MySimpleUrlAuthenticationSuccessHandler();
	}

	/**
	 * Concurrent Session Control
	 * 
	 * When a user that is already authenticated tries to authenticate again, the
	 * application can deal with that event in one of a few ways. It can either
	 * invalidate the active session of the user and authenticate the user again
	 * with a new session, or allow both sessions to exist concurrently.
	 * 
	 * @return
	 */
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	/**
	 * This method will indicate the BCrypt Password Encoder. This password encoder
	 * should be used to encode the password.
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		logger.info(CommonConstants.APP_NAME_FOR_LOG + "Creating BCryptPasswordEncoder Object");
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
		// BcryptPasswordEncoder unit test or encoding as password
		// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		// String result = encoder.encode("myPassword");
		// assertTrue(encoder.matches("myPassword", result));
	}

	/**
	 * Uncomment this code, if you would like to use in memory authentication with
	 * hardcoded username and password details
	 * 
	 * @param authenticationBuilderManager
	 * @throws Exception
	 */
	@Autowired
	// public void configureGlobal(AuthenticationManagerBuilder
	// authenticationBuilderManager) throws Exception {
	//
	// // Use BCrypt Password encoder. Since you have defined your password encoder
	// is BCrypt Password encoder
	// authenticationBuilderManager.inMemoryAuthentication()
	// .withUser("john").password(bCryptPasswordEncoder().encode("secretPasswd")).roles("ADMIN")
	// // This will assign Admin role for all the requests come with username as
	// "admin" and password as "admin"
	// .and().withUser("peter").password(bCryptPasswordEncoder().encode("secretPasswd")).roles("USER");
	// // This will assign User role for all the requests come with username as
	// "user" and password as "user"
	// }

	/**
	 * This method is used to authenticate this api using spring security with
	 * validating user name and password, roles against database
	 * 
	 * customUserDetailsService is used to pull the username and password details
	 * from database and match against the login details provided by user
	 * 
	 * BCryptPasswordEncoder is used to encode the password
	 * 
	 * Database should contain the plain password and user will also enter plain
	 * password.
	 * 
	 * While retreiving the plain password from database, we need to perform encode
	 * that plain password
	 * 
	 * to bcyrptpassword encoder after retrieving from database and before assigning
	 * to CustomSecurityUserDetails object
	 * 
	 * 
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info(CommonConstants.APP_NAME_FOR_LOG
				+ "Spring security validating the username, password and authorities against Database");
		auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	/**
	 * 
	 * ----- Spring security session ----
	 * 
	 * always – a session will always be created if one doesn’t already exist
	 * ifRequired – a session will be created only if required (default) never – the
	 * framework will never create a session itself but it will use one if it
	 * already exists stateless – no session will be created or used by Spring
	 * Security
	 * 
	 */
	public void configure(HttpSecurity httpSecurity) throws Exception {
		logger.info(CommonConstants.APP_NAME_FOR_LOG
				+ "Authorizing all the application urls with spring security using roles/authorities");

		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

		httpSecurity.sessionManagement().maximumSessions(2);// Maximum number of concurrent sessions

		httpSecurity.sessionManagement().invalidSessionUrl("/invalidSession.html");

		httpSecurity.sessionManagement().sessionFixation().migrateSession();

		httpSecurity.authorizeRequests().anyRequest().hasAnyRole("USER", "ADMIN").and().authorizeRequests()
				.antMatchers("/login**").permitAll().and().formLogin().loginPage("/login") // Specifies the login page
																							// URL
				.loginProcessingUrl("/signin") // Specifies the URL,which is used in action attribute on the <from> tag
				.usernameParameter("username") // Username parameter, used in name attribute of the <input> tag in login
												// jsp file. Default is 'username'.
				.passwordParameter("passwd") // Password parameter, used in name attribute of the <input> tag login jsp
												// file. Default is 'password'.
				// .successHandler((req, res, auth) -> { // Success handler invoked after
				// successful authentication
				// for (GrantedAuthority authority : auth.getAuthorities()) {
				// System.out.println(authority.getAuthority());
				// }
				// System.out.println(auth.getName());
				// res.sendRedirect(req.getContextPath() + "/home"); // Redirect user to
				// index/home page
				// })
				.successHandler(successHandler())
				// .defaultSuccessUrl("/") // URL, where user will go after authenticating
				// successfully.
				// Skipped if successHandler() is used.
				.failureHandler((req, res, exp) -> { // Failure handler invoked after authentication failure
					String errMsg = "";
					if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
						errMsg = "Invalid username or password.";
					} else {
						errMsg = "Unknown error - " + exp.getMessage();
					}
					req.getSession().invalidate();
					req.setAttribute("message", errMsg);
					res.sendRedirect(req.getContextPath() + "/login"); // Redirect user to login page with error
																		// message.
				})
				// .failureUrl("/login?error") // URL, where user will go after authentication
				// failure.
				// Skipped if failureHandler() is used.
				.permitAll() // Allow access to any URL associate to formLogin()
				.and().logout().deleteCookies("JSESSIONID").logoutUrl("/signout") // Specifies the logout URL, default
																					// URL is '/logout'
				.logoutSuccessHandler((req, res, auth) -> { // Logout handler called after successful logout
					req.getSession().setAttribute("message", "You are logged out successfully.");
					res.sendRedirect(req.getContextPath() + "/login"); // Redirect user to login page with message.
				})
				// .logoutSuccessUrl("/login") // URL, where user will be redirect after
				// successful
				// logout. Ignored if logoutSuccessHandler() is used.
				.permitAll() // Allow access to any URL associate to logout()
				.and().csrf().disable(); // Disable CSRF support

		// // Authorize all the urls with spring security
		// httpSecurity.authorizeRequests().antMatchers("/emp**").hasAnyRole("USER",
		// "ADMIN")
		// .antMatchers("/add**", "/update**",
		// "/delete**").hasAnyRole("ADMIN").anyRequest().authenticated().and()
		// .formLogin().permitAll().and().httpBasic()
		// .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
		//// .and().exceptionHandling().accessDeniedPage("/accessDenied");
		//
		// httpSecurity.csrf().disable(); // Disables CSRF protection
		//
		// httpSecurity.headers().frameOptions().disable(); // Disables X-Frame-Options
		// in Spring Security for access to H2
		// // database console. By default, Spring Security will
		// // protect against CRSF attacks.

		// Allow all urls without spring security
		// httpSecurity.authorizeRequests().anyRequest().permitAll().and().httpBasic().and().csrf().disable();

		// httpSecurity.authorizeRequests().antMatchers("/emp**").permitAll() // Allows
		// all requests to the /** paths
		// httpSecurity.authorizeRequests().antMatchers("/**").hasRole("USER")
		// .anyRequest().permitAll(); // Secures all other paths of the application to
		// require authentication
		// .and()
		// .formLogin().loginPage("/login").permitAll() // Allows everyone to view a
		// custom /login page specified by loginPage()
		// .and()
		// .logout().permitAll(); //Permits all to make logout calls

		// Other full logic
		// https://github.com/Baeldung/spring-security-registration/blob/7c383d35650b89e6a6efb0f369f1708876d483e2/src/main/java/org/baeldung/spring/SecSecurityConfig.java#L106
		// hhttpSecurity
		// .csrf().disable()
		// .authorizeRequests()
		// .antMatchers("/login*","/login*", "/logout*", "/signin/**", "/signup/**",
		// "/user/registration*", "/registrationConfirm*", "/expiredAccount*",
		// "/registration*",
		// "/badUser*", "/user/resendRegistrationToken*" ,"/forgetPassword*",
		// "/user/resetPassword*",
		// "/user/changePassword*", "/emailError*",
		// "/resources/**","/old/user/registration*","/successRegister*","/qrcode*").permitAll()
		// .antMatchers("/invalidSession*").anonymous()
		// .anyRequest().authenticated()
		// .and()
		// .formLogin()
		// .loginPage("/login")
		// .defaultSuccessUrl("/homepage.html")
		// .failureUrl("/login?error=true")
		// .successHandler(myAuthenticationSuccessHandler)
		// .failureHandler(authenticationFailureHandler)
		// .authenticationDetailsSource(authenticationDetailsSource)
		// .permitAll()
		// .and()
		// .sessionManagement()
		// .invalidSessionUrl("/invalidSession.html")
		// .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
		// .sessionFixation().none()
		// .and()
		// .logout()
		// .logoutSuccessHandler(myLogoutSuccessHandler)
		// .invalidateHttpSession(false)
		// .logoutSuccessUrl("/logout.html?logSucc=true")
		// .deleteCookies("JSESSIONID")
		// .permitAll();

		// .and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}

	/**
	 * This method will indicate there is no password encoder
	 * 
	 * @return
	 */
	// @Bean
	// public static NoOpPasswordEncoder passwordEncoder() {
	// return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	// }
	//

	// @Bean
	// public PasswordEncoder md5PasswordEncoder(){
	// //implements PasswordEncoder and overide encode method with the MD5 protocol
	// return new MD5PasswordEncoder();
	// }

}
