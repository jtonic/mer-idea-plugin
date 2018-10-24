package ro.jtonic.handson.plugin.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JComponent

/**
 * Created by Antonel Ernest Pazargic on 24/10/2018.
 * @author Antonel Ernest Pazargic
 */
class InfoDialog(project: Project, txt: String) : DialogWrapper(project) {

    private val form: InfoForm

    init {
        isModal = true
        title = "Ide info"
        setSize(400, 300)
        form = InfoForm()
        form.text = txt
        init()
    }

    override fun createCenterPanel(): JComponent = form.root
}
