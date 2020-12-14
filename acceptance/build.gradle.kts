import com.moowork.gradle.node.yarn.YarnTask
import com.moowork.gradle.node.NodeExtension

plugins {
  id("com.github.node-gradle.node")
}

configure<NodeExtension> {
  version = "12.18.3"
  yarnVersion = "1.22.4"
  download = true
}

tasks {
  val install = register<YarnTask>("install") {
    inputs.file(file("$projectDir/yarn.lock"))
    inputs.file(file("$projectDir/package.json"))
    outputs.dir(file("$projectDir/node_modules"))
    args = listOf("install")
  }

  register<YarnTask>("test") {
    shouldRunAfter(":backend:test")
    shouldRunAfter(":frontend:test")
    dependsOn(install)
    args = listOf("test")
  }

  register<YarnTask>("open") {
    shouldRunAfter(":backend:test")
    shouldRunAfter(":frontend:test")
    dependsOn(install)
    args = listOf("open")
  }
}
