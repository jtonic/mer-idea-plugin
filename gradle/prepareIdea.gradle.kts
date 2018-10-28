import de.undercouch.gradle.tasks.download.Download
import java.nio.file.Paths

// We have to use imperative plugin activation with buildscript and apply - because there is no other way yet to resolve imports in include gradle scripts (apply{from(file(..)})
buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("de.undercouch:gradle-download-task:3.4.3")
    }
}

apply(plugin = "de.undercouch.download")

// Use the  gradle-download-task plugin to download:
// 1. IntelliJ Idea CE from
// https://www.jetbrains.com/intellij-repository/releases/com/jetbrains/intellij/idea/ideaIC/173.3727.127/
// ideaIC-173.3727.127.pom
// ideaIC-173.3727.127-sources.jar
// ideaIC-173.3727.127.zip

// 2. all OS flavoured jbre version jbrex8u152b1136.29 from
// https://dl.bintray.com/jetbrains/intellij-jdk/

// gradle-download-task:
//      https://github.com/michel-kraemer/gradle-download-task
//      https://michelkraemer.com/recipes-for-gradle-download/

val offline = isOffline(project)
val ideaIC = "ideaIC-${Versions.ideaIC}"

val cacheDirectoryPath = Paths.get(project.gradle.gradleUserHomeDir.absolutePath,
        "caches/modules-2/files-2.1/com.jetbrains/jbre").toString()

tasks {
    val downloadIdeaSandbox by registering(Download::class) {
        group = "merak"
        src("https://www.jetbrains.com/intellij-repository/releases/com/jetbrains/intellij/idea/ideaIC/${Versions.ideaIC}/$ideaIC.zip")
        dest("$projectDir/lib/ideaIC/$ideaIC.zip")
        overwrite(false)
    }

    val unzipIdeaSandbox by registering(Copy::class) {
        group = "merak"
        dependsOn(downloadIdeaSandbox.get())
        from(zipTree(downloadIdeaSandbox.get().dest))
        into("$projectDir/lib/ideaIC/$ideaIC")
    }

    val downloadJbrex by registering(Download::class) {
        group = "merak"

        src(arrayOf(
            "https://dl.bintray.com/jetbrains/intellij-jdk/${Versions.jbrex}_osx_x64.tar.gz",
            "https://dl.bintray.com/jetbrains/intellij-jdk/${Versions.jbrex}_linux_x64.tar.gz",
            "https://dl.bintray.com/jetbrains/intellij-jdk/${Versions.jbrex}_linux_x86.tar.gz",
            "https://dl.bintray.com/jetbrains/intellij-jdk/${Versions.jbrex}_windows_x64.tar.gz",
            "https://dl.bintray.com/jetbrains/intellij-jdk/${Versions.jbrex}_windows_x86.tar.gz"
        ))

        dest("$projectDir/lib/jbre/${Versions.jbrex}")
        overwrite(false)
    }

    val copyJbrexToGradleCache by registering(Copy::class) {
        group = "merak"

        val jbrexName = "${Versions.jbrex}_${platform()}_${arch()}"
        val jbrexArchFile = downloadJbrex.get().dest.listFiles()?.first { it.endsWith("$jbrexName.tar.gz") }?.run {
            val dest = "$cacheDirectoryPath/$jbrexName"
            logger.quiet("Un-archive jbrex= '$absolutePath' to '$dest'")

            from(tarTree(this))
            into(dest)
            // unzip archive only if it isn't already and script runs in offline mode
            onlyIf {
                offline && !file(dest).exists()
            }
        }
    }

    // tasks dependencies
    // https://stackoverflow.com/questions/32907275/gradle-custom-task-which-runs-multiple-tasks
    task("prepareIdea") {
        group = "merak"

        dependsOn(downloadIdeaSandbox.get())
        dependsOn(unzipIdeaSandbox.get())
        dependsOn(downloadJbrex.get())
        dependsOn(copyJbrexToGradleCache.get())
        unzipIdeaSandbox.get().mustRunAfter(downloadIdeaSandbox.get())
        copyJbrexToGradleCache.get().mustRunAfter(downloadJbrex.get())
    }
}
