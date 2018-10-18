package ro.jtonic.handson.plugin.framework

import com.intellij.facet.FacetType
import com.intellij.framework.detection.DetectedFrameworkDescription
import com.intellij.framework.detection.FacetBasedFrameworkDetector
import com.intellij.framework.detection.FileContentPattern
import com.intellij.framework.detection.FrameworkDetectionContext
import com.intellij.ide.highlighter.XmlFileType
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.patterns.ElementPattern
import com.intellij.patterns.StandardPatterns
import com.intellij.psi.PsiManager
import com.intellij.psi.xml.XmlFile
import com.intellij.util.indexing.FileContent
import com.intellij.util.xml.DomManager
import org.jetbrains.idea.maven.dom.model.MavenDomProjectModel
import ro.jtonic.handson.plugin.facet.MerFacet
import ro.jtonic.handson.plugin.facet.MerFacetConfiguration
import ro.jtonic.handson.plugin.facet.MerFacetType
import java.util.*

/**
 * Created by Antonel Ernest Pazargic on 18/10/2018.
 *
 * @author Antonel Ernest Pazargic
 */
class MerFrameworkDetector : FacetBasedFrameworkDetector<MerFacet, MerFacetConfiguration>("Mer", 1) {

    override fun createSuitableFilePattern(): ElementPattern<FileContent> =
            FileContentPattern.fileContent().withName(StandardPatterns.string().matches("pom.xml"))

    override fun getFacetType(): FacetType<MerFacet, MerFacetConfiguration> =
            MerFacetType.INSTANCE

    override fun getFileType(): FileType =
            XmlFileType.INSTANCE

    override fun detect(
            newFiles: MutableCollection<VirtualFile>,
            context: FrameworkDetectionContext
        ): MutableList<out DetectedFrameworkDescription> {

        val project = context.project ?: return mutableListOf()
        val psiManager = PsiManager.getInstance(project)
        for (newFile in newFiles) {
            val psiFile = psiManager.findFile(newFile)
            return psiFile
                    ?.let { pomXmlFile ->
                        val domManager = DomManager.getDomManager(project)
                        val domModel = domManager.getFileElement(pomXmlFile as XmlFile?, MavenDomProjectModel::class.java)?.rootElement
                        domModel
                    }?.let {
                        it.xmlTag?.findFirstSubTag("parent")
                                ?.findFirstSubTag("artifactId")?.value?.text
                    }?.let {
                        context.createDetectedFacetDescriptions(this, mutableListOf(newFile))
                    } ?: mutableListOf()
        }
        return mutableListOf()
    }
}
