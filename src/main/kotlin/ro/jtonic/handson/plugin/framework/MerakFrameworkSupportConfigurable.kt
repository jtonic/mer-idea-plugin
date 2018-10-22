package ro.jtonic.handson.plugin.framework

import com.intellij.facet.FacetManager
import com.intellij.ide.util.frameworkSupport.FrameworkSupportConfigurable
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel
import com.intellij.openapi.module.Module
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.roots.libraries.Library
import ro.jtonic.handson.plugin.facet.MerakFacet
import ro.jtonic.handson.plugin.facet.MerakFacetConfiguration
import ro.jtonic.handson.plugin.facet.MerakFacetType
import javax.swing.JComponent
import javax.swing.JLabel

class MerakFrameworkSupportConfigurable(model: FrameworkSupportModel) : FrameworkSupportConfigurable() {

    init {
        model.setFrameworkComponentEnabled("Merak", true)
    }

    override fun getComponent(): JComponent = JLabel("Merak framework support configuration")

    override fun addSupport(module: Module, modifiableRootModel: ModifiableRootModel, library: Library?) {
        val facetManager = FacetManager.getInstance(module)
        val facetModel = facetManager.createModifiableModel()
        val facet = facetManager.addFacet<MerakFacet, MerakFacetConfiguration>(MerakFacetType.INSTANCE, "Merak", null)
        facetModel.addFacet(facet)
        facetModel.commit()
    }
}
