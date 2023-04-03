plugins {
    id("kotlin")
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    /// The Lint version is strongly tied to the Android Gradle Plugin (AGP) version
    /// Add 23 to the major version number of AGP (x in x.y.z)
    /// The project is currently dependent on AGP v7.4.1
    val lintVersion = "30.4.1"

    compileOnly("com.android.tools.lint:lint-api:${lintVersion}")
    compileOnly("com.android.tools.lint:lint-checks:${lintVersion}")

    testImplementation("com.android.tools.lint:lint:${lintVersion}")
    testImplementation("com.android.tools.lint:lint-tests:${lintVersion}")

    testImplementation("junit:junit:4.13.2")
}

tasks {
    jar {
        manifest {
            attributes(
                    "Lint-Registry-v2" to "com.adidas.threestripes.ThreeStripesIssueRegistry",
            )
        }
    }
}