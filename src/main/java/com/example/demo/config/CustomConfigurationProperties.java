package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This is a class to hold Custom Key and value pair messages in the
 * application.properties file This properties will start with "custom.name",
 * "custom.value" etc
 * 
 * @author
 *
 */
@Component
@ConfigurationProperties("custom")
public class CustomConfigurationProperties {

	private String message;
	private int age;
	private String error;

	private List<Menu> menus = new ArrayList<Menu>();
	private Compiler compiler = new Compiler();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * each .(dot) in a properties files requires to create a static java class
	 * 
	 * Example: custom.menu. means needs to create a Menu Class.
	 * 
	 * @author
	 *
	 */
	private static class Menu {
		private String name;
		private String title;
		private String path;

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @param title
		 *            the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @return the path
		 */
		public String getPath() {
			return path;
		}

		/**
		 * @param path
		 *            the path to set
		 */
		public void setPath(String path) {
			this.path = path;
		}

		@Override
		public String toString() {
			return "Menu{" + "name='" + name + '\'' + ", path='" + path + '\'' + ", title='" + title + '\'' + '}';
		}
	}

	private static class Compiler {
		private String timeout;
		private String outputFolder;

		/**
		 * @return the timeout
		 */
		public String getTimeout() {
			return timeout;
		}

		/**
		 * @param timeout
		 *            the timeout to set
		 */
		public void setTimeout(String timeout) {
			this.timeout = timeout;
		}

		/**
		 * @return the outputFolder
		 */
		public String getOutputFolder() {
			return outputFolder;
		}

		/**
		 * @param outputFolder
		 *            the outputFolder to set
		 */
		public void setOutputFolder(String outputFolder) {
			this.outputFolder = outputFolder;
		}

		@Override
		public String toString() {
			return "Compiler{" + "timeout='" + timeout + '\'' + ", outputFolder='" + outputFolder + '\'' + '}';
		}

	}
}
