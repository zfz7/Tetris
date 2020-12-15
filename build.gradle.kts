plugins {
	val kotlinVersion = "1.4.21"
	val springBootVersion = "2.4.1"
	val springDependencyManagementVersion = "1.0.10.RELEASE"
	val nodePluginVersion = "2.2.4"

	kotlin("jvm") version kotlinVersion apply false
	kotlin("kapt") version kotlinVersion apply false
	kotlin("plugin.jpa") version kotlinVersion apply false
	kotlin("plugin.spring") version kotlinVersion apply false

	id("org.springframework.boot") version springBootVersion apply false
	id("io.spring.dependency-management") version springDependencyManagementVersion apply false

	id("com.github.node-gradle.node") version nodePluginVersion apply false
}

group = "org.zfz7"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

