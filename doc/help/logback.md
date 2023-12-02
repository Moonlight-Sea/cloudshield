# This section describes how to configure logback

## 1. Overview

Logback is one of the most widely used logging frameworks in the Java Community. It's a replacement for its predecessor,
Log4j. Logback offers a faster implementation, provides more options for configuration, and more flexibility in
archiving
old log files.

## 2. Logback Architecture

The Logback architecture is comprised of three classes: Logger, Appender, and Layout

## 3. SetUp

### 3.1 Maven Dependency

```xml

<dependencys>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-core</artifactId>
        <version>1.3.5</version>
    </dependency>

    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.4</version>
        <scope>test</scope>
    </dependency>
</dependencys>

```

## 4. Basic Example and Configuration

## Quote

[A Guide To Logback](https://www.baeldung.com/logback)  
[Logging in Spring Boot](https://www.baeldung.com/spring-boot-logging)  