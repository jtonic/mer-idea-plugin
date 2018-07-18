package ro.jtonic.handson.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * Created by Antonel Ernest Pazargic on 29/03/2018.
 *
 * @author Antonel Ernest Pazargic
 */
class GenerationTool : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {
        println("This is it!!!")
    }
}
