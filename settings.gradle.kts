plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

// 根项目名称
rootProject.name = "cloud-shield"

// include all sub projects. The name is location of the new project in the project hierarchy, for example "a:b:c", not the file path
// Sub project"s default path is the "${rootDir}/${projectName}".
include(
        ":shield-dispatch",
        ":shield-config",
        ":shield-batch",
        ":shield-store",
        ":shield-common",
        ":shield-common:shield-common-core",
        ":shield-common:shield-common-generator"
)

rootProject.children.forEach { project ->
    project.buildFileName = "build.gradle.kts"
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("springBootVersion", "3.1.6")
            version("springCloudVersion", "4.0.4")
            val jacksonVersion = version("jacksonVersion", "2.15.2")

            library("mysql-connector-java", "mysql:mysql-connector-java:8.0.28")

            library("lombok", "org.projectlombok:lombok:1.18.30")
            library("commons-lang3", "org.apache.commons:commons-lang3:3.12.0")
            library("commons-collections4", "org.apache.commons:commons-collections4:4.4")
            library("commons-httpclient5", "org.apache.httpcomponents.client5:httpclient5:5.2.3")
            library("commons-httpclient5-fluent", "org.apache.httpcomponents.client5:httpclient5-fluent:5.2.3")

            // https://mvnrepository.com/artifact/com.hierynomus/sshj 服务器连接
            library("sshj", "com.hierynomus:sshj:0.37.0")
            // https://mvnrepository.com/artifact/com.googlecode.aviator/aviator
            library("aviator", "com.googlecode.aviator:aviator:5.4.1")

            library("hutool-all", "cn.hutool:hutool-all:5.8.23")

            library("groovy-core", "org.codehaus.groovy:groovy:3.0.5")
            library("groovy-json", "org.codehaus.groovy:groovy-json:3.0.5")
            library("groovy-nio", "org.codehaus.groovy:groovy-nio:3.0.5")

            library("jackson-core", "com.fasterxml.jackson.core", "jackson-core").versionRef(jacksonVersion)
            library("jackson-databind", "com.fasterxml.jackson.core", "jackson-databind").versionRef(jacksonVersion)
            library("jackson-annotations", "com.fasterxml.jackson.core", "jackson-annotations").versionRef(jacksonVersion)
            library("jackson-dataformat-xml", "com.fasterxml.jackson.dataformat", "jackson-dataformat-xml").versionRef(jacksonVersion)
            library("jackson-dataformat-yaml", "com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml").versionRef(jacksonVersion)
            library("jackson-datatype-jsr310", "com.fasterxml.jackson.datatype", "jackson-datatype-jsr310").versionRef(jacksonVersion)

            library("commons-io", "commons-io:commons-io:2.15.0")
            library("commons-codec", "commons-codec:commons-codec:1.15")

            library("mybatis-spring-boot-starter", "org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0")
            library("mybatis-plus-boot-starter", "com.baomidou:mybatis-plus-boot-starter:3.5.3.1")
            library("mybatis-plus-generator", "com.baomidou:mybatis-plus-generator:3.5.3.1")
            library("freemarker", "org.freemarker:freemarker:2.3.31")

            bundle("jackson", listOf("jackson-core", "jackson-databind", "jackson-annotations", "jackson-dataformat-xml", "jackson-dataformat-yaml", "jackson-datatype-jsr310"))
        }
        create("testLibs") {
            val junit5 = version("junit5", "5.10.1")
            val junitPlatform = version("junitPlatform", "1.9.3")

            library("h2", "com.h2database:h2:2.2.224")

            library("junit-jupiter", "org.junit.jupiter", "junit-jupiter").versionRef(junit5)
            library("junit-api", "org.junit.jupiter", "junit-jupiter-api").versionRef(junit5)
            library("junit-engine", "org.junit.jupiter", "junit-jupiter-engine").versionRef(junit5)

            library("junit-platform-launcher", "org.junit.platform", "junit-platform-launcher").versionRef(junitPlatform)
            library("spring-boot-starter-test", "org.springframework.boot:spring-boot-starter-test:3.1.6")

            bundle("junit5", listOf("junit-jupiter", "junit-api", "junit-engine", "junit-platform-launcher"))
        }
        // 仅用于开发环境
        create("devLibs") {

            library("p6spy", "p6spy:p6spy:3.9.1")
            library("p6spy-spring-boot-starter", "com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.1")

        }
    }
}