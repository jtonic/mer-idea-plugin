package ro.jtonic.handson.plugin.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.project.Project
import ro.jtonic.handson.plugin.ui.InfoDialog
import java.lang.StringBuilder

/**
 * Created by Antonel Ernest Pazargic on 24/10/2018.
 *
 * @author Antonel Ernest Pazargic
 */
class IdeInfoAction : AnAction() {

    override fun actionPerformed(evt: AnActionEvent) {
        evt.project?.let { prj: Project ->
            val moduleManager = ModuleManager.getInstance(prj)
            with(StringBuilder()) {
                append("Project: ${prj.name}")
                append("\nModules:\n")
                moduleManager.modules.asSequence().joinTo(this) { module ->
                    "\t${module!!.name}"
                }
            }.also {
                val dialog = InfoDialog(prj, it.toString())
                dialog.show()
            }
        }
    }
}
