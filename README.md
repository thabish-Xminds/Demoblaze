Descripiton 

To run test file run the following files

Run the test files in the folder src/test/java
1. src/test/java/SignupTestjava
2. src/test/java/LoginTest.java
3. src/test/java/ProductPageTest.java
4. src/test/java/AddToCartTest.java
5. src/test/java/LogOutTest.java

Page Objects are listed in src/main/java/PageObjects

In order to use the framework:

a. Fork the repository.
b. Clone, i.e, download your copy of the repository to your local machine using
git clone https://github.com/thabish-Xminds/Demoblaze.git
c. Import the Project in eclipse.
d. Make your desired changes.
e. Use Eclispe IDE to run your desired tests.
f. To see the report, go to the testoutput folder in the project root and then go to the report folder.

Languages and Frameworks
The project uses the following:

Java as the programming language.
Selenium WebDriver as the web browser automation framework using the Java binding.
TestNG as the testing framework.
Extent Reports as the test reporting strategy & for capturing screenshots.
Maven as the Java build tool.
Jenkins for running the CI/CD tests.


Project Structure
The project is structured as follows:

src/
├── base/
│   └── BaseTest.java
├── pages/
│   ├── BlazePage.java
├── utils/
│   ├── ConfigReader.java
│   ├── Constants.java
│   └── TestDataGenerator.java
│   └── AbstractWorkerTask.java
│   └── CacheCore.java
│   └── CacheManager.java
│   └── CommonUtil.java
│   └── RemoveConfigurationWorker.java
│   └── SeleniumUtil.java
│   └── StoreConfigurationWorker.java
│   └── TestWorker.java
│   └── WorkerTask.java
│   └── WorkerThreadPool.java
└── com/
    └── xminds/
        └── selenium/
            └── testcase/
                └── SignUpTest.java
                └── LoginTest.java
                └── ProductPageTest.jav
                └── AddToCartTest.java
                └── LogOutTest.java
            
Configuration
The project uses a config.properties file to manage global configurations such as browser type and base url.

Test Data
The project uses xls or csv file to store test data

Browser
The project contains the implementation of the Chrome,Firefox & Edge browsers.

Page Objects and Page Component Objects
The project uses Page Objects as per the design of the Page Object Model to capture the relevant behaviors of a web page.

Tests
The project uses TestNG as the test runner.




