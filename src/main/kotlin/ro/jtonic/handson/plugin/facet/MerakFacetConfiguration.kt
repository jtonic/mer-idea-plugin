package ro.jtonic.handson.plugin.facet

import com.intellij.facet.FacetConfiguration
import com.intellij.facet.ui.FacetEditorContext
import com.intellij.facet.ui.FacetEditorTab
import com.intellij.facet.ui.FacetValidatorsManager
import com.intellij.openapi.Disposable

class MerakFacetConfiguration : FacetConfiguration, Disposable {

    override fun dispose() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createEditorTabs(editorContext: FacetEditorContext, validatorsManager: FacetValidatorsManager?): Array<FacetEditorTab> {
        return arrayOf(MerakFacetEditorTab(editorContext))
    }
}
