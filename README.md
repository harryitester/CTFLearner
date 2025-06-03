# Test automation framework for CTFLearn platform using Java, Selenium WebDriver, TestNG, and Maven.

## Tech Stack:

- Java 11
- Selenium WebDriver 4.16.1
- TestNG 7.8.0
- Maven
- Allure Reporting
- Log4j2 for logging
- WebDriverManager for browser management

## Project Structure

```
ctflearn-automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/         # Page Object Model classes
│   │       └── utils/         # Utility classes
│   ├── test/
│       └── java/
│           └── tests/         # Test classes
│           └── base/          # Test base classes
├── config/                    # Environment configurations
├── testng.xml                # TestNG configuration
├── pom.xml                   # Maven configuration
```

## 🚀 Setup Step

- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome/Firefox browser installed

### Setup

1. Clone the repository:
```bash
git clone https://github.com/harryitester/CTFLearner.git
```

2. Install dependencies:
```bash
mvn clean install
```

### Running Tests

Run all tests:
```bash
mvn clean test
```

Run specific test suite:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

Run tests with specific environment:
```bash
mvn clean test -Denv=qa
```

## Test Reports

Allure reports are generated after test execution. To view the reports:

1. Install Allure command-line tool
2. Run: `allure serve target/allure-results`

## Configuration

Environment configurations are stored in `config/` directory:
- config.qa.properties
- config.staging.properties
