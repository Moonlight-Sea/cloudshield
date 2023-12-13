plugins {
    id("java")
    `java-library`
}

dependencies {
    api(libs.hutool.all)
    api(libs.bundles.jackson)

    api(libs.commons.lang3)
    api(libs.commons.io)
    api(libs.commons.collections4)

    api(libs.mybatis.plus.boot.starter)
    api(libs.mysql.connector.java)

    api(libs.aviator)


    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // 仅限于开发环境使用的依赖
    // https://mvnrepository.com/artifact/com.github.gavlyukovskiy/p6spy-spring-boot-starter
    api(devLibs.p6spy.spring.boot.starter)
}