plugins {
    kotlin("jvm")
    `maven-publish`
}

subprojects {
    apply { plugin("kotlin") }
    apply { plugin("maven-publish") }
    sourceSets {
        main {
            java.srcDir("src")
        }
    }
    repositories {
        mavenCentral()
        maven(url = "https://www.jitpack.io")
    }
    dependencies {
        compileOnly("com.github.Anuken.Mindustry:core:v135")
    }

    tasks.withType(JavaCompile::class) {
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
        sourceCompatibility = JavaVersion.VERSION_16.toString()
        options.compilerArgs.addAll(listOf("--release", "8"))
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = rootProject.group.toString()
                artifactId = project.name
                version = rootProject.version.toString()

                from(components["kotlin"])
                artifact(tasks.getByName("kotlinSourcesJar"))
            }
        }
    }
}