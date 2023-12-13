plugins {
    id("java")
    id("java-library")
}

dependencies {

    implementation(project(":shield-common:shield-common-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-config")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

