# Overview

[![build_status](https://travis-ci.org/appulse-projects/logging-java.svg?branch=master)](https://travis-ci.org/appulse-projects/logging-java)
[![maven_central](https://maven-badges.herokuapp.com/maven-central/io.appulse/logging-java/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.appulse/logging-java)

Common logging project for Appulse project. It contains all required dependencies as well as colorizing configurations for logging output.

## Usage

Just add dependency to your project.

**Maven**:

```xml
<dependencies>
    ...
    <dependency>
        <groupId>io.appulse</groupId>
        <artifactId>logging-java</artifactId>
        <version>1.1.1</version>
    </dependency>
    ...
</dependencies>
```

**Gradle**:

```groovy
compile 'io.appulse:logging-java:1.1.1'
```

And use it as usual:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeClass {

  Logger log = LoggerFactory.getLogger(SomeClass.class);

  public void doSomeLog () {
    log.info("info message");
    log.warn("warn message");
    log.error("error message");
  }
}
```

or, if you use a Lombok Project:

```java
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SomeClass {

  public void doSomeLog () {
    log.info("info message");
    log.warn("warn message");
    log.error("error message");
  }
}
```

With default configuration (no `logback.xml` in classpath at all) it looks like:

![override example](https://github.com/appulse-projects/logging-java/blob/master/.images/example.png?raw=true)

You also could to override default `logback`'s configuration, like an example below.

**resources/logback.xml**:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<configuration>
  <property
    name="CONSOLE_LOG_PATTERN"
    value="${CONSOLE_LOG_PATTERN:-%clr(${LOG_LEVEL_PATTERN:-%5p}) %clr([%25.25thread]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}}"
  />

  <include resource="io/appulse/logging/logback/base.xml"/>
</configuration>
```

And output will be like:

![override example](https://github.com/appulse-projects/logging-java/blob/master/.images/override_example.png?raw=true)

## Development

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

For building the project you need only a [Java compiler](http://www.oracle.com/technetwork/java/javase/downloads/index.html).

> **IMPORTANT:** the project requires Java version starting from **8**

And, of course, you need to clone the project from GitHub:

```bash
$> git clone https://github.com/appulse-projects/logging-java
$> cd logging-java
```

### Building

For building routine automation, I am using [maven](https://maven.apache.org).

To build the project, do the following:

```bash
$> mvn clean package
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 18.447 s
[INFO] Finished at: 2018-01-23T18:36:20+03:00
[INFO] Final Memory: 52M/175M
[INFO] ------------------------------------------------------------------------
```

### Running the tests

To run the project's test, do the following:

```bash
$> mvn clean test
...
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
...
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 20, Failures: 0, Errors: 0, Skipped: 0
[INFO]
...
```

Also, if you do `package` or `install` goals, the tests launch automatically.

## Built With

* [Java](http://www.oracle.com/technetwork/java/javase) - is a systems and applications programming language

* [Lombok](https://projectlombok.org) - is a java library that spicing up your java

* [Junit](http://junit.org/junit4/) - is a simple framework to write repeatable tests

* [AssertJ](http://joel-costigliola.github.io/assertj/) - AssertJ provides a rich set of assertions, truly helpful error messages, improves test code readability

* [Maven](https://maven.apache.org) - is a software project management and comprehension tool

## Changelog

To see what has changed in recent versions of the project, see the [changelog](./CHANGELOG.md) file.

## Contributing

Please read [contributing](./CONTRIBUTING.md) file for details on my code of conduct, and the process for submitting pull requests to me.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/appulse-projects/logging-java/tags).

## Authors

* **[Artem Labazin](https://github.com/xxlabaza)** - creator and the main developer

## License

This project is licensed under the Apache License 2.0 License - see the [license](./LICENSE) file for details
