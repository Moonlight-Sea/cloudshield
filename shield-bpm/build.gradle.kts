plugins {
    id("java")
    id("java-library")
}



dependencies {
    implementation(project(":shield-common:shield-common-core"))


    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}