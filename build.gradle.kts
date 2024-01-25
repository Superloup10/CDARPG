plugins {
    application
    java
}

group = "fr.wolfdev.cda"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(libs.bundles.databases)
    testImplementation(libs.junit)
    testRuntimeOnly(libs.junit.platform)
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get()))
    }
}

application {
    mainClass.set("$group.${projects.rpg.name.lowercase()}.RPGMain")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
