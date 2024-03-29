weatherapp
========================

What is it?
-----------

This is an application which displays the current weather information given a zip code. This application is made using Spring framework 3.2.2 and include unit tests for Configuration, Controller and service layers. This application uses JSR303 validation framework for bean validations and <form:error/> tag of Spring framework for rendering the validation errors on the UI.

System requirements
-------------------

All you need to build this project is Java 7.0 (Java SDK 1.67 or better, Maven 3.0 or better. The pom.xml uses tomcat plugin for running the tomcat server. Users have to make sure that the default port (8080) used by tomcat is not occupied and is available for running this application.


Build and Deploy the Application
-------------------------

1. Open a command line and navigate to the root of the code directory.
2. The following shows the command line to compile the code and host it on tomcat:

	mvn clean compile package

	mvn tomcat:run

The first run will download all the necessary artifacts used in the application and user must be connected to the internet and with access to the maven repositories.

3. This will deploy `target/weatherapp.war` to the running instance of the server.


Access the application 
---------------------
 
The application will be running at the following URL: <http://localhost:8080/weatherapp/wa/index>.


Additional Notes
----------------
1) The application can be imported into eclipse IDE by generating artifacts with command.<br />

	mvn eclipse:eclipse -DdownloadSources=false

2) I have used JSR-303 based bean validation framework for basic validations. The annotated bean name is InputForm.java.<br />
3) Since spring-mvc-test is not production ready, I have used milestone 2 (M2) release of the jar and added the repository configuration for it. The repository details are taken from the net.<br />
4) For loading webcontext in the unit tests, I have used a class named "GenericWebContextLoader" directly from spring source code (as I have used it successfully in earlier projects as well and taken the same class from those projects).<br />
5) I have consumed JSON service for sake of simplicity and Jackson Mapper inbuilt support in Spring framework. XML parsing is a bit heavy for this particular operation and since we dont need to validate the response and in my opinion, JAXB would have been an overkill for this application. I have used Jackson API for mapping the partial response (only those fields are mapped, the ones we are interested in) from the web-service.<br />
6) I have not built exception handling framework related to network errors and not written tests for the same. </br />
7) I have used Mockito for mocking some MVC related stuff.
8) I have used the following tools and technologies for this application:<br />
	OS: Linux Mint 14, Cinamon Edition<br />
	Maven version : 3.0.4<br />
	Tested On: Tomcat 7.0.2<br />
	Browser Used: Google Chrome 24.0<br />
	JDK: Version 1.7, update:10<br />
	Spring framework: Current Version (3.2.2)<br />
9) I have not used logging just for the sake of clarity, but provided log4J to enable/disable spring logs. <br />
10) As per the new servlet Specifications, I have not used the annotations based servelt configuration and hense did not use WebApplicationInitializer for bootstraping the application. I have stick to the classic way with web.xml file only.
