plugins {
    id("java")
    id("java-library")
}

dependencies {

    implementation(project(":shield-common:shield-common-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-config")

    // https://mvnrepository.com/artifact/org.dom4j/dom4j
    implementation("org.dom4j:dom4j:2.1.4")


    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

