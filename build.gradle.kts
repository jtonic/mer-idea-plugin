import org.gradle.api.JavaVersion.VERSION_1_8
import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.intellij.tasks.RunIdeTask
import org.jetbrains.kotlin.contracts.model.structure.UNKNOWN_COMPUTATION.type
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    java
    maven
    idea
    kotlin("jvm") version(Versions.kotlin)
    kotlin("plugin.allopen") version("1.2.71")
    kotlin("plugin.noarg") version("1.2.71")
    id("org.jetbrains.intellij") version("0.3.12")
}

group = "ro.jtonic.plugins"
version = Versions.projectVersion

val offline = isOffline(project)
logger.quiet("offline = $offline")
val ideaIC: String = "ideaIC-${Versions.ideaIC}"

apply {
    from(file("gradle/prepareIdea.gradle.kts"))
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}") {
        isForce = true
    }
    compile("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}") {
        isForce = true
    }
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Versions.kotlinCoroutines}")

    testCompile("io.kotlintest:kotlintest-core:${Versions.kotlinTest}")
    testCompile("io.kotlintest:kotlintest-runner-junit5:${Versions.kotlinTest}")
    testCompile("org.testng:testng:${Versions.testNg}")
}

configure<JavaPluginConvention> {
    sourceCompatibility = VERSION_1_8
    targetCompatibility = VERSION_1_8
}

intellij {
    type = "IC" // IU | JPS | JPS | RS | RD   - IC is the default
    pluginName = "Merak"
    // The kotlin idea plugin versions -> https://plugins.jetbrains.com/plugin/6954-kotlin
    downloadSources = true
    // plugins = ["Kotlin", "maven", "gradle"]
    setPlugins("org.jetbrains.kotlin:${Versions.kotlin}-release-IJ2018.2-1", "maven", "gradle")

    // for IntelliJ Idea build numbers see
    // https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/build_number_ranges.html
    // Jetbrains product releases -> https://www.jetbrains.com/intellij-repository/releases
    // 181.4203.550 -> 2018.1
    if (offline) {
        localPath = "offline/ideaIC/$ideaIC"
    } else {
        version = Versions.ideaIC
    }
    updateSinceUntilBuild = false
}

tasks {
    withType<Wrapper> {
        gradleVersion = Versions.gradle
        distributionType = Wrapper.DistributionType.ALL
        //    distributionUrl = "gradle-$gradleVersion-all.zip"
        distributionUrl = "http://services.gradle.org/distributions/gradle-$gradleVersion-all.zip"
    }

    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = Versions.java
            incremental = true
            suppressWarnings = true
            apiVersion = Versions.kotlinApi
            languageVersion = Versions.kotlinLanguage
            javaParameters = true
            freeCompilerArgs = listOf("-Xdisable-default-scripting-plugin", "-Xjsr305=strict", "-Xjvm-default=enable")
        }
    }

    // https://github.com/JetBrains/gradle-intellij-plugin/blob/master/src/main/groovy/org/jetbrains/intellij/tasks/RunIdeTask.groovy
    //noinspection GroovyAssignabilityCheck
    withType<RunIdeTask> {
        // where jbre can be downloaded from
        // https://dl.bintray.com/jetbrains/intellij-jdk/
        // e.g.: https://dl.bintray.com/jetbrains/intellij-jdk/jbrex8u152b1136.29_osx_x64.tar.gz
        // jbreVersion "jbrex8u152b1136.29"
        // ideaDirectory "/Users/antonelpazargic/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-0/173.4548.28"
        setJbreVersion(Versions.jbrex)
    }

    withType<PatchPluginXmlTask> {
        changeNotes("""
        <h4>${project.version}</h4>
        <b>Implemented features:</b>
        <ul>
            <li><div><i>Framework detector</i></div></li>
            <li><div><i>Plugin information</i></div></li>
        </ul>
        <b>Fixed bugs:</b>
        <ul></ul>
        <b>Refactoring:</b>
        <ul>
            <li>Mer -> Merak</li>
        </ul>
        <b>Documentation:</b>
        <ul>
            <li>More structured md files</li>
        </ul>
""")
    }
}

idea {
    project {
        jdkName = Versions.java
        module {
            generatedSourceDirs.add(file("src/gen"))
        }
    }
}
