import com.github.gradle.node.NodeExtension
import com.github.gradle.node.yarn.task.YarnTask

plugins {
  id("com.github.node-gradle.node")
}

configure<NodeExtension> {
  version.set("12.18.3")
  yarnVersion.set("1.22.5")
  download.set(true)
}

tasks {
  val install = register<YarnTask>("install") {
    inputs.file(file("$projectDir/yarn.lock"))
    inputs.file(file("$projectDir/package.json"))
    outputs.dir(file("$projectDir/node_modules"))
    args.set(listOf("install"))
  }

  register<YarnTask>("test") {
    shouldRunAfter(":backend:test")
    shouldRunAfter(":frontend:test")
    dependsOn(install)
    args.set(listOf("test"))
  }

  register<YarnTask>("open") {
    shouldRunAfter(":backend:test")
    shouldRunAfter(":frontend:test")
    dependsOn(install)
    args.set(listOf("open"))
  }
}
