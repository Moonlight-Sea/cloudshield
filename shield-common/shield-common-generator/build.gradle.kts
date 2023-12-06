dependencies {

    // mybatis plus code generator dependency start
    implementation(libs.mysql.connector.java)
    implementation(libs.mybatis.plus.boot.starter)
    implementation(libs.mybatis.plus.generator)
    implementation(libs.freemarker)
    // end

    testImplementation(testLibs.junit.engine)
}
