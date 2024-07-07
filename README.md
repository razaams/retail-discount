# Retail Discount Application

## Overview
This application calculates the net payable amount on a bill based on various discounts.

### Deliverables
1. Spring Boot Application
2. Application UML Diagram ***(available at artifacts/retail-discount-drawio.pdf)***
3. Source code static analysis report ***(available at artificats/pmd-lint-analysis)***
4. Test coverage report ***(available at artifacts/jacoco-test-coverage)***
5. Sonar qube summary for code quality ***(available at artificats/sonarqube-report)***
6. Sample input data to run application ***(avaialble at artifacts/sample-input.json file)***

### Code Analysis & Coverage
1. Junit is used for Application Unit testing
2. For Test Coverage **[Jacoco](https://www.jacoco.org/)** Plugin is used
3. For Static Code Analysis **[PMD](https://pmd.github.io/)** Plugin is used
4. For Code quality  **[Sonar Qube](https://www.sonarsource.com/)** is used

## How to Run

### Prerequisites
- Java 17
- Maven

### Steps
1. Clone the repository:
    ```bash
    git clone <repository_url>
    cd retail-discount
    ```

2. Build the project:
    ```bash
    mvn clean install
    ```

3. Run the tests:
    ```bash
    mvn test
    ```

4. Generate code coverage report:
    ```bash
    mvn jacoco:report
    ```

5. Run static code analysis:
    ```bash
	mvn clean verify
    ```

6. To run the application with sample input, execute following command from root directory of application:
    ```bash
	java -jar .\target\retail-discount-0.0.1-SNAPSHOT.jar .\artifacts\sample-input.json
    ```