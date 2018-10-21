package ro.jtonic.handson.plugin.facet

import com.intellij.facet.Facet
import com.intellij.openapi.module.Module
import com.intellij.openapi.util.Disposer

class MerakFacet(
        facetType: MerakFacetType,
        module: Module,
        name: String,
        configuration: MerakFacetConfiguration,
        underlyingFacet: Facet<*>?
) : Facet<MerakFacetConfiguration>(
        facetType,
        module,
        name,
        configuration,
        underlyingFacet) {

    init {
        Disposer.register(this, configuration)
    }
}
