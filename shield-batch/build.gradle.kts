plugins {
    id("java")
    `java-library`
}

dependencies {

    implementation(project(":shield-common:shield-common-core"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.cloud:spring-cloud-starter-config")


    // api
    implementation(libs.sshj)

    testImplementation(testLibs.spring.boot.starter.test)
    testImplementation(testLibs.h2)

//    implementation(devLibs.p6spy)
}