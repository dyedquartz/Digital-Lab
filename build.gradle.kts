plugins {
    java
    idea
    id("fabric-loom")
    `maven-publish`
    kotlin("jvm")
    kotlin("kapt")

}

val modVersion: String by project
val mavenGroup: String by project
val archivesBaseName: String by project

version = modVersion
group = mavenGroup

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    sourceSets["main"].java {
        srcDir("${buildDir.absolutePath}/generated/source/kaptKotlin/")
    }
}

base {
    archivesBaseName = archivesBaseName
}

kapt {
    // Required line!
    annotationProcessor("com.thinkslynk.fabric.annotations.FabricProcessor")
}

minecraft {
}

repositories {
    maven("http://maven.fabricmc.net/")
    maven("https://server.bbkr.space/artifactory/libs-release")
}

dependencies {
    val minecraftVersion: String by project
    val yarnMappings: String by project
    val loaderVersion: String by project
    val fabricVersion: String by project
    val fabricKotlinVersion: String by project

    //to change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnMappings:v2")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlinVersion")

    // GUI Library
    // https://github.com/CottonMC/LibGui/wiki/Getting-Started-with-GUIs
    modImplementation("io.github.cottonmc:LibGui:3.0.0-beta.1+$minecraftVersion-rc2")

    compileOnly(project(":annotations"))
    kapt(project(":processor"))

    // https://github.com/TechReborn/Energy
    // https://github.com/natanfudge/Working-Scheduler
    // https://github.com/Siphalor/nbt-crafting
}

val processResources = tasks.getByName<ProcessResources>("processResources") {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        filter { line -> line.replace("%VERSION%", "${project.version}") }
    }
}


val javaCompile = tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

val jar = tasks.getByName<Jar>("jar") {
    from("LICENSE")
}


// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
// if it is present.
// If you remove this task, sources will not be generated.
val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}