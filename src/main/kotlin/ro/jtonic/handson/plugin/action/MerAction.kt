package ro.jtonic.handson.plugin.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ro.jtonic.handson.plugin.ui.MerDialog


/**
 * Created by Antonel Ernest Pazargic on 29/03/2018.
 *
 * @author Antonel Ernest Pazargic
 */
class MerAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {
        e?.project?.let {
            MerDialog(it).show()
        }
    }
}
