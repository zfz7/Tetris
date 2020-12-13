import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun


plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    id("com.gorylenko.gradle-git-properties") version "2.2.4"
}

group = "org.zfz7"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.mockk:mockk:1.10.3")

    runtimeOnly("org.postgresql:postgresql")
}

gitProperties {
    keys = arrayOf(
        "git.commit.id"
    ).toMutableList()
} // adds git commit ID to the actuator

tasks.withType<KotlinCompile> {
    kotlinOptions {
        allWarningsAsErrors = true
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
    }
}

tasks.withType<BootRun> {
    dependsOn("copy_frontend_to_backend")
}

tasks.withType<BootJar> {
    dependsOn("copy_frontend_to_backend")
    doLast {
        println(message = "artifact_path: ${outputs.files.files.single()}")
    }
}

tasks.register<Sync>("copy_frontend_to_backend") {
    dependsOn(":frontend:build")
    from(file("${project(":frontend").projectDir}/build"))
    into(file("$buildDir/resources/main/static"))
}

tasks.withType<Jar> {
    baseName = "relay-strat"
    archiveName = "$baseName.jar"
}
