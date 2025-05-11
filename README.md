# My QA Automation Project

This project contains automated tests for API and UI using Java, Maven, Selenide, RestAssured, and Allure reporting.

## Table of Contents
- [Dependencies](#dependencies)
- [Test Groups](#test-groups)
- [How to Run Tests](#how-to-run-tests)

---

## Dependencies

The project uses the following dependencies:

- **JUnit 5**: For test execution.
- **Selenide**: For UI testing.
- **RestAssured**: For API testing.
- **Allure**: For generating test reports.
- **Lombok**: For reducing boilerplate code.
- **Jackson**: For JSON parsing.
- **AspectJ Weaver**: For weaving aspects in Allure.

You can find the full list of dependencies in the `pom.xml` file.

---

## Test Groups

Tests are organized into groups for easier execution:

- **API**:
    - `positiveApi`: Positive API tests.
    - `NegativeApi`: Negative API tests.
    - `API`: All API tests (both positive and negative).

- **UI**:
    - `UI`: All UI tests.

---

## How to Run Tests

Use the following Maven commands to run tests based on groups:

### Run Positive API Tests
```bash
mvn test -Dgroups="positiveApi"
mvn test -Dgroups="NegativeApi"
mvn test -Dgroups="API"
mvn test -Dgroups="UI"