# Automated Test for Expedia Flight Search
This project is set up with Selenium WebDriver for running browser-based automated tests. Follow the instructions below to configure and run the tests on macOS.
## Prerequisites
Ensure you have the following installed:

### Required Dependencies
#### Java Development Kit (JDK 17)
Make sure you have JDK 17 installed. You can install it via Homebrew:

```bash
brew install openjdk@17
```

After installation, configure the environment:

```bash
export PATH="/usr/local/opt/openjdk@17/bin:$PATH"
```
Verify installation:

```bash
java -version
```

#### Maven
Install Maven to manage dependencies and run tests:

```bash
brew install maven
```

Verify installation:

```bash
mvn -version
```
#### Browser

Google Chrome or Mozilla Firefox must be installed to run the tests.
#### Project Dependencies
Selenium WebDriver (Managed via Maven)
WebDriver Manager (Automatically manages the necessary browser drivers)
JUnit 5 (For running the tests)
All required libraries are defined in the pom.xml, and Maven will automatically download them.
## Setting Up the Project
###   Step-by-Step Setup Instructions
####   Clone this project from GitHub to your local machine:
```bash
git clone https://github.com/SergPileyko/automated-expedia.git
cd automated-expedia
cd expedia
```
#### Install Maven Dependencies
```bash
mvn clean install
```
## Run tests
#### Running the Tests on Default Browser (Chrome)
The tests will run using ChromeDriver by default. To execute the tests, simply run:
```bash
mvn test
```

To specify the browser (Chrome, Firefox), use the -Dbrowser option:
```bash
mvn -Dbrowser=chrome clean test
```
or
```bash
mvn -Dbrowser=firefox clean test
```
## Stack

| Component             | Technologies       |
| --------------------- |:-------------------|
| Programming languages | Java 17            |
| Test tool             | Selenium WebDriver |
| Testing library       | JUnit 5            |
