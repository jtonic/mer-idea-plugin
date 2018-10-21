package ro.jtonic.handson.plugin.facet

import com.intellij.facet.Facet
import com.intellij.facet.FacetType
import com.intellij.facet.FacetTypeId
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleType
import ro.jtonic.handson.plugin.icon.Icons
import javax.swing.Icon

/**
 * Created by Antonel Ernest Pazargic on 16/10/2018.
 *
 * @author Antonel Ernest Pazargic
 */
class MerakFacetType : FacetType<MerakFacet, MerakFacetConfiguration>(ID, "Merak", "Merak") {

    override fun createFacet(module: Module, name: String, configuration: MerakFacetConfiguration, underlyingFacet: Facet<*>?): MerakFacet =
        MerakFacet(this, module, name, configuration, underlyingFacet)

    override fun createDefaultConfiguration(): MerakFacetConfiguration =
            MerakFacetConfiguration()

    override fun isSuitableModuleType(moduleType: ModuleType<*>): Boolean =
        true

    override fun getIcon(): Icon? =
        Icons.MERAK

    companion object {
        val ID = FacetTypeId<MerakFacet>("Merak")
        val INSTANCE = MerakFacetType()
    }
}
