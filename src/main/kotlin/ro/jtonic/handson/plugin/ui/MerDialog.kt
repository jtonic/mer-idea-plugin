package ro.jtonic.handson.plugin.ui

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper


/**
 * Created by Antonel Ernest Pazargic on 18/07/2018.
 * @author Antonel Ernest Pazargic
 */
class MerDialog(private val project: Project) : DialogWrapper(project) {

    private val form = MerakForm()

    init {
        isModal = true
        title = "Merak"
        form.setProjectName(project.name)
        init()
    }

    override fun createCenterPanel() = form.panel!!

    override fun doOKAction() {
        LOG.info("project = $project")
        LOG.info("Project name: ${form.txtPrjName.text}")

        NotificationGroup("Merak", NotificationDisplayType.STICKY_BALLOON, true).run {
            createNotification("Merak", "Success", "Successfully created project '${project.name}'.", NotificationType.INFORMATION)
        }.also {
            Notifications.Bus.notify(it, project)
            close(OK_EXIT_CODE)
        }
    }

    companion object {
        val LOG: Logger = logger<MerDialog>()
    }
}
