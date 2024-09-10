description = "数据源"

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

dependencies {  
    implementation(project(":shield-common:shield-common-core"))

    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation(libs.aerospike.client)


    // https://mvnrepository.com/artifact/org.apache.pdfbox/pdfbox
    implementation("org.apache.pdfbox:pdfbox:3.0.2")
    // https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
    implementation("org.apache.poi:poi-ooxml:5.2.5")

    // fastjson
    implementation(libs.com.alibaba.fastjson)


    testImplementation(testLibs.junit.jupiter)
}
