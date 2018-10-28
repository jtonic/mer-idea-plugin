/**
 * Created by Antonel Ernest Pazargic on 28/10/2018.
 * @author Antonel Ernest Pazargic
 */

@file:JvmName("Utils")

import org.gradle.api.Project
import org.gradle.internal.os.OperatingSystem


fun arch(): String {
    val arch = System.getProperty("os.arch")!!
    return if ("x86" == arch) "x86" else "x64"
}

fun platform(): String {
    val current = OperatingSystem.current()!!
    return when {
        current.isWindows() -> "windows"
        current.isMacOsX() -> "osx"
        else -> "linux"
    }
}

fun isOffline(project: Project): Boolean  =
    project.findProperty("offline")?.run { (project.property("offline") as String).toBoolean() } ?: false
