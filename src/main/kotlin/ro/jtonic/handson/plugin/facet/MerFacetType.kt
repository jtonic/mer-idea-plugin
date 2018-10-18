package ro.jtonic.handson.plugin.facet

import com.intellij.facet.Facet
import com.intellij.facet.FacetType
import com.intellij.facet.FacetTypeId
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleType

/**
 * Created by Antonel Ernest Pazargic on 16/10/2018.
 *
 * @author Antonel Ernest Pazargic
 */
class MerFacetType : FacetType<MerFacet, MerFacetConfiguration>(ID, "Mer", "Mer") {

    override fun createFacet(module: Module, name: String, configuration: MerFacetConfiguration, underlyingFacet: Facet<*>?): MerFacet =
        MerFacet(this, module, name, configuration, underlyingFacet)

    override fun createDefaultConfiguration(): MerFacetConfiguration =
            MerFacetConfiguration()

    override fun isSuitableModuleType(moduleType: ModuleType<*>): Boolean =
        true

    companion object {
        val ID = FacetTypeId<MerFacet>("Mer")
        val INSTANCE = MerFacetType()
    }
}
