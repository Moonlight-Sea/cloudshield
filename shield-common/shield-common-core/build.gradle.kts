plugins {
    id("java")
    `java-library`
}

dependencies {
    api(libs.hutool.all)
//    api(libs.spring.boot.starter.log)
//    api("ch.qos.logback:logback-core:1.4.11")
    api(libs.bundles.jackson)

    // https://mvnrepository.com/artifact/com.googlecode.aviator/aviator
    implementation("com.googlecode.aviator:aviator:5.4.1")

//    testImplementation(testLibs.junit.jupiter)
//    testImplementation(testLibs.junit.api)
//    testImplementation(testLibs.junit.engine)
//    testImplementation(testLibs.junit.platform.launcher)

    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

}