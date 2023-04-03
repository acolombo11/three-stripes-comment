plugins {
    id("kotlin")
}

dependencies {
    val ktlintVersion = "0.48.2"
    compileOnly("com.pinterest.ktlint:ktlint-core:$ktlintVersion")

    testImplementation("junit:junit:4.13.2")

    testImplementation("com.pinterest.ktlint:ktlint-core:$ktlintVersion")
    testImplementation("com.pinterest.ktlint:ktlint-test:$ktlintVersion")
}
