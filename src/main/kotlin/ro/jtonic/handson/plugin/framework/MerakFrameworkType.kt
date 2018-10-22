package ro.jtonic.handson.plugin.framework

import com.intellij.facet.FacetManager
import com.intellij.framework.FrameworkTypeEx
import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.roots.ModifiableModelsProvider
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.roots.ui.configuration.FacetsProvider
import ro.jtonic.handson.plugin.facet.MerakFacet
import ro.jtonic.handson.plugin.facet.MerakFacetConfiguration
import ro.jtonic.handson.plugin.facet.MerakFacetType
import ro.jtonic.handson.plugin.icon.Icons
import javax.swing.Icon
import javax.swing.JComponent

/**
 * Created by Antonel Ernest Pazargic on 22/10/2018.
 * @author Antonel Ernest Pazargic
 */
class MerakFrameworkType: FrameworkTypeEx("Merak") {

    override fun getIcon(): Icon = Icons.MERAK

    override fun getPresentableName(): String = id

    override fun createProvider() = object: FrameworkSupportInModuleProvider() {
        override fun getFrameworkType(): FrameworkTypeEx =
            this@MerakFrameworkType

        override fun isEnabledForModuleType(moduleType: ModuleType<*>): Boolean = true

        override fun isSupportAlreadyAdded(module: Module, facetsProvider: FacetsProvider): Boolean {
            return facetsProvider.getAllFacets(module).find { it is MerakFacet  } != null
        }

        override fun canAddSupport(module: Module, facetsProvider: FacetsProvider): Boolean {
            return !isSupportAlreadyAdded(module, facetsProvider);
        }

        override fun createConfigurable(model: FrameworkSupportModel): FrameworkSupportInModuleConfigurable = object: FrameworkSupportInModuleConfigurable() {
            override fun addSupport(module: Module, rootModel: ModifiableRootModel, modifiableModelsProvider: ModifiableModelsProvider) {
                val facetManager = FacetManager.getInstance(module)
                val facetModel = facetManager.createModifiableModel()
                val facet = facetManager.addFacet<MerakFacet, MerakFacetConfiguration>(MerakFacetType.INSTANCE, "Merak", null)
                facetModel.addFacet(facet)
                facetModel.commit()
            }

            override fun createComponent(): JComponent? = null
        }
    }
}
