plugins {
    id("java")
}

dependencies {
    implementation(project(":shield-common:shield-common-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

