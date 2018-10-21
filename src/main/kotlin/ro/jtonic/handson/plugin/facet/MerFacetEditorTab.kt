package ro.jtonic.handson.plugin.facet

import com.intellij.facet.ui.FacetEditorContext
import com.intellij.facet.ui.FacetEditorTab
import javax.swing.JComponent
import javax.swing.JLabel

class MerFacetEditorTab(editorContext: FacetEditorContext) : FacetEditorTab() {

    override fun isModified(): Boolean =
            false

    override fun getDisplayName(): String =
        "Merak"

    override fun createComponent(): JComponent =
        JLabel("No configuration yet!")
}
