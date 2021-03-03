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

val install = tasks.register<YarnTask>("install") {
  inputs.file(file("$projectDir/yarn.lock"))
  inputs.file(file("$projectDir/package.json"))
  outputs.dir(file("$projectDir/node_modules"))
  args.set(listOf("install"))
}

tasks.register<YarnTask>("test") {
  environment.set(mapOf("CI" to "true"))
  dependsOn(install)
  args.set(listOf("test"))
}

tasks.register<YarnTask>("build") {
  dependsOn(install)
  mustRunAfter("test")
  inputs.dir(file("$projectDir/src"))
  outputs.dir(file("$projectDir/build"))
  args.set(listOf("build"))
}


task<Delete>("clean") {
  delete(project(":frontend").buildDir)
}
