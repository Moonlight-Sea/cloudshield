description = "数据源"

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

dependencies {
    implementation(project(":shield-common:shield-common-core"))
}
