package ro.jtonic.handson.plugin.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ModuleRootManager
import org.jetbrains.kotlin.idea.util.projectStructure.sdk
import ro.jtonic.handson.plugin.ui.InfoDialog
import java.lang.StringBuilder

/**
 * Created by Antonel Ernest Pazargic on 24/10/2018.
 *
 * @author Antonel Ernest Pazargic
 */
class IdeInfoAction : AnAction() {

    override fun actionPerformed(evt: AnActionEvent) {
        val app = ApplicationManager.getApplication()
        evt.project?.let { prj: Project ->
            val moduleManager = ModuleManager.getInstance(prj)
            val strBuilder = StringBuilder()
            with(strBuilder) {
                appendln("Application")
                appendln("\tIs EAP: ${app.isEAP}, isRestartCapable: ${app.isRestartCapable}")
                appendln()
                appendln("Project: ${prj.name}")
                appendln("\t base path: ${prj.basePath}, opened: ${prj.isOpen}, workspace file: ${prj.workspaceFile}")
                app.isEAP
                appendln("Modules:")
                for (module in moduleManager.modules) {
                    appendln("\t${module!!.name}")
                    val sdk = module.sdk!!
                    appendln("\t\tSDK: name: ${sdk.name}, homePath: ${sdk.homePath}, type: ${sdk.sdkType.name}, version: ${sdk.versionString}")

                    // Dependencies
                    val dependencies = ModuleRootManager.getInstance(module).orderEntries().classes().roots
                    appendln("\t\tDependencies:")
                    for (dependency in dependencies) {
                        appendln("\t\t\t name: ${dependency.name}, presentableName: ${dependency.presentableName}")
                    }
                }
                this
            }.also {
                val dialog = InfoDialog(prj, it.toString())
                dialog.show()
            }
        }
    }
}
