import com.moowork.gradle.node.NodeExtension
import com.moowork.gradle.node.yarn.YarnTask

plugins {
    id("com.github.node-gradle.node")
}

configure<NodeExtension> {
    version = "12.18.3"
    yarnVersion = "1.22.4"
    download = true
}

val install = tasks.register<YarnTask>("install") {
    inputs.file(file("$projectDir/yarn.lock"))
    inputs.file(file("$projectDir/package.json"))
    outputs.dir(file("$projectDir/node_modules"))
    args = listOf("install")
}

tasks.register<YarnTask>("test") {
    setEnvironment(mapOf("CI" to true))
    dependsOn(install)
    args = listOf("test")
}

tasks.register<YarnTask>("build") {
    dependsOn(install)
    mustRunAfter("test")
    inputs.dir(file("$projectDir/src"))
    outputs.dir(file("$projectDir/build"))
    args = listOf("build")
}


task<Delete>("clean") {
    delete(project(":frontend").buildDir)
}
