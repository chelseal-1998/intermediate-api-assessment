# Mid API Assessment

#### INTRODUCTION

In this project I am automating an API using a Base Framework, the Framework I implemented is an API test automation
framework built on Java and uses Rest Assured.

#### PRE-REQUISITES
* Gradle - version
* Java - version
* IDE - preferably IntelliJ IDEA
* Web browser - preferably the latest stable version of Chrome or Firefox

#### SYSTEM UNDER TEST
API restful-booker:https://restful-booker.herokuapp.com/apidoc/index.html

#### FRAMEWORK TEST STRUCTURE
Each section's tests are contained within a package, each package has a base class to allow for ease of maintainability
and above all, adhering to the DRY(Don't Repeat Yourself) principle.

#### BaseTest
Contains the set-up method. May include methods that are required for the set of tests.
#### CustomConfig
Contains custom properties from the properties file that will be sent to you directly, you will need the properties file
to be able to run the tests.
#### Common
Contains all the TestData needed for the tests to run.

### Running tests locally (Command Line or IDE:) ###
Execute the following commands in your IDE terminal:
* In Windows terminal: ./gradlew test
* In Mac terminal: gradlew test

#### REPORTING
A test report is generated after each run in the /build/reports/tests/index.html folder in the base repository of the repo.

To view this report:
* Step 1: Right-click on the index.html file
* Step 2: Go to 'open in' > browser > Chrome
* Step 3: Select Chrome or your preferred browser, and you should be able to view the report.