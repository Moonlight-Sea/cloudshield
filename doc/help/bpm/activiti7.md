# How to use activiti7 with spring boot

## 1. 什么是 activiti7

- [which is best choose for activiti](https://zhuanlan.zhihu.com/p/616464702)

activiti 7 是基于 activiti 6 的重构，支持云原生架构，不建议首选。





##  依赖

**You can consume all the Activiti artifacts for this release from Alfresco Nexus:**
```xml
<repositories>
  <repository>
    <id>activiti-releases</id>
    <url>https://artifacts.alfresco.com/nexus/content/repositories/activiti-releases</url>
  </repository>
</repositories>
```

**Activiti Core**

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-dependencies</artifactId>
            <version>7.1.0-M16</version>
            <scope>import</scope>
            <type>pom</type>
        </dependency>
    </dependencies>
</dependencyManagement>
```



## 参考文档

- [A Guide to Activiti with Java](https://www.baeldung.com/java-activiti)
- [Introduction to Activiti with Spring](https://www.baeldung.com/spring-activiti)