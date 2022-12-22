# Swiggy-Coding-Challenge
    Technologies and Tools used:
      Language: Java
      Test Automation Tool: Selenium WebDriver
      Test Automation Framework: Cucumber
      Design Pattern: Page Object Model
      Build Management Tool: Maven
      Logging Framework: Log4j
      Reporting Library: Cucumber Extent Reporter
      IDE: Eclipse
      Code Quality Detector: Sonar Lint		

    Project Structure:
    +---Swiggy-Automation
        |
        +---src
        |    +---test
        |	  |    +---java
        |	  |    |    \---runner
        |	  |	    |        \---TestRunner.java
        |    |    +---resources
        |    |	    |    \---features
        |    |    |        \---*.feature
        |    +---main
        |    |    +---java
        |    |    |    \---pageobjects
        |    |    |    |    \---*.java
        |    |    |    \---stepdefs
        |    |    |    |    \---*.java
        |    |    |    |---utilities
        |    |    |    |    \---Helper.java
        |    |    |    |    \---Browser.java
        +---src
        |
        +---target
        |
        +---log4j.properties
        |
        +---pom.xml
        |
        +---readme.md
        |
        
                          
    How to build and execute the tests?
        1. To run from an Eclipse IDE using JUnit, right click on TestRunner.java -> Select Run As -> JUnit Test
        2. To run from an Eclipse IDE using Maven, right click on Project -> Select Run As -> Maven test OR Maven install
        3. To run from command prompt/terminal using Maven, run the command 'mvn clean install' OR 'mvn install' OR 'mvn test' 
              
    Where to find results?
        You can find an execution report inside target folder -> ExecutionReport.html
               
    Where to find logging information?
        You can find logging information inside target/logs folder -> ExecutionLogs.html         
              

