plugins {
    id("java")
    id("java-library")
}

dependencies {
    implementation(project(":shield-common:shield-common-core"))
}

tasks.test {
    useJUnitPlatform()
}