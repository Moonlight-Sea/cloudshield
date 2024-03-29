plugins {
    id("java")
    id("java-library")
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-config-server")

    testImplementation(testLibs.spring.boot.starter.test)
}
