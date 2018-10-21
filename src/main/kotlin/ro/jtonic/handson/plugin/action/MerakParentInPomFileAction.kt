package ro.jtonic.handson.plugin.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.ui.Messages
import com.intellij.psi.xml.XmlFile
import com.intellij.util.xml.DomManager
import org.jetbrains.idea.maven.dom.model.MavenDomProjectModel
import org.jetbrains.idea.maven.model.MavenConstants
import org.jetbrains.kotlin.idea.refactoring.toPsiFile


/**
 * Created by Antonel Ernest Pazargic on 29/03/2018.
 *
 * @author Antonel Ernest Pazargic
 */
class MerakParentInPomFileAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {
        e!!
        val project = e.project!!
        val rootDirectory = e.getData(PlatformDataKeys.PROJECT_FILE_DIRECTORY)!!
        val pomXml = rootDirectory.findChild(MavenConstants.POM_XML)
        val merakProject = pomXml?.run {
            val pomXmlFile = pomXml.toPsiFile(project) as XmlFile
            val domManager = DomManager.getDomManager(project)
            val domModel = domManager.getFileElement(pomXmlFile, MavenDomProjectModel::class.java)!!.rootElement
            domModel
        }.let {
            it?.xmlTag?.findFirstSubTag("parent")?.findFirstSubTag("artifactId")?.value?.text
        }?.let { true } ?: false

        logger<MerakParentInPomFileAction>().info("Is merak project: $merakProject")

        Messages.showMessageDialog("Yes. It's a merak project.", "Information", Messages.getErrorIcon())
    }
}
