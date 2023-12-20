import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    // 管理依赖版本
    id("org.springframework.boot") version "3.1.6"
    id("io.spring.dependency-management") version "1.1.4"
    `java-library`
    checkstyle
    kotlin("jvm") version "1.9.10"
}

group = "pre.sea.cloud.cloud-shield"
version = "1.0.0"
description = "cloud-shield 聚合父工程"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(8)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}


allprojects {

    repositories {
        mavenLocal()
        maven { setUrl("https://maven.aliyun.com/repository/central") }
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin") }
//        maven { setUrl("https://artifacts.alfresco.com/nexus/content/repositories/activiti-releases") }
        mavenCentral()
    }
}

subprojects {

    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencyManagement {
        imports {
//            mavenBom("org.activiti:activiti-dependencies:7.1.0-M16")
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.1.6")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.4")
        }
        dependencies {
            dependency("org.springframework:spring-core:6.0.10")
        }
    }

    dependencies {
        annotationProcessor("org.projectlombok:lombok:1.18.30")
        compileOnly("org.projectlombok:lombok:1.18.30")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
        testCompileOnly("org.projectlombok:lombok:1.18.30")
    }
}