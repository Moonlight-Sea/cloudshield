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
    // https://mvnrepository.com/artifact/com.hierynomus/sshj
    implementation("com.hierynomus:sshj:0.37.0")



    // api
    implementation(libs.commons.lang3)
    implementation(libs.commons.io)
    implementation(libs.commons.collections4)

    implementation(libs.mybatis.plus.boot.starter)
    implementation(libs.mysql.connector.java)


    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation(testLibs.spring.boot.starter.test)
    testImplementation(testLibs.h2)

    // 仅限于开发环境使用的依赖
    implementation(devLibs.p6spy)
}