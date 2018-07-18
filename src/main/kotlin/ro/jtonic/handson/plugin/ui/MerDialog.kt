package ro.jtonic.handson.plugin.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper

/**
 * Created by Antonel Ernest Pazargic on 18/07/2018.
 * @author Antonel Ernest Pazargic
 */
class MerDialog(private val project: Project) : DialogWrapper(project) {

    private val form = MerForm()

    init {
        isModal = true
        title = "Mer..."
        form.setProjectName(project.name)
        init()
    }

    override fun createCenterPanel() = form.panel!!

    override fun doOKAction() {
        println("project = $project")
        println("Project name: ${form.txtPrjName.text}")
        close(OK_EXIT_CODE)
    }
}
