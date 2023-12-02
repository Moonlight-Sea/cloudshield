# Intercept SQL Logging with P6Spy

## 1. Introduction

In this tutorial, we’ll discuss [P6Spy](https://github.com/p6spy/p6spy), **an open-source, free library useful for
intercepting SQL logs in Java applications.**

In the first part of the article, we’ll discuss the main advantage of relying on this external library instead of just
enabling SQL logging for JPA or Hibernate and the different ways we can integrate the library into our apps. Then we’ll
present a simple example of a Spring Boot application working with P6Spy to see some of the most important
configurations available.

## 2. P6Spy Installation

P6Spy needs to be installed on an application server. In general, it’s enough to put the application JAR in the
classpath and conveniently configure the drivers and the JDBC connection.

Another way of using P6Spy is through integration with the existing code of our application, assuming that making small
code changes is acceptable. In the next section, we’ll see an example of how it’s possible to integrate P6Spy in a
Spring Boot application by relying on auto-configuration.

The [p6spy-spring-boot-starter](https://mvnrepository.com/artifact/com.github.gavlyukovskiy/p6spy-spring-boot-starter)
is a repository that provides integration with P6Spy and other database monitoring
libraries. Thanks to this library, enabling P6Spy logging is as simple as adding a jar on the classpath. With Maven,
It’s enough to add this code snippet in the POM.xml:

```gradle
implementation 'com.p6spy:p6spy:3.9.1'
```

If we want to configure logging, we need to add a file named “spy.properties” in the resources folder:

```properties
# P6Spy configuration
appender=com.p6spy.engine.spy.appender.FileLogger
logfile=database.log
append=true
logMessageFormat=com.p6spy.engine.spy.appender.CustomLineFormat
customLogMessageFormat=%(currentTime)|%(executionTime)|%(category)|%(sqlSingleLine)
```

### 参考

[Intercept SQL Logging with P6Spy](https://www.baeldung.com/java-p6spy-intercept-sql-logging)