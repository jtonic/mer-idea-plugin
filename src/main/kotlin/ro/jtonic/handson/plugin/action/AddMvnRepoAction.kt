package ro.jtonic.handson.plugin.action

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.jetbrains.idea.maven.model.MavenRemoteRepository
import org.jetbrains.plugins.gradle.integrations.maven.MavenRepositoriesHolder


/**
 * Created by Antonel Ernest Pazargic on 29/03/2018.
 *
 * @author Antonel Ernest Pazargic
 */
class AddMvnRepoAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        val mavenRepositoryHolder = MavenRepositoriesHolder.getInstance(e?.project)

        mavenRepositoryHolder.remoteRepositories.forEach {
            Notifications.Bus.notify(Notification("Mer", "Maven repo", "id: ${it.id}, url: ${it.url}", NotificationType.INFORMATION))
        }

        val mavenRemoteRepository = MavenRemoteRepository("central", "bintray", "http://jcenter.bintray.com", "", MavenRemoteRepository.Policy(true, "", ""), MavenRemoteRepository.Policy(false, "", ""))
        mavenRepositoryHolder.update(setOf(mavenRemoteRepository))

        mavenRepositoryHolder.remoteRepositories.forEach {
            Notifications.Bus.notify(Notification("Mer", "Maven repo", "id: ${it.id}, url: ${it.url}", NotificationType.INFORMATION))
        }
    }
}
