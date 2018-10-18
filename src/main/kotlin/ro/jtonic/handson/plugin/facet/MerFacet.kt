package ro.jtonic.handson.plugin.facet

import com.intellij.facet.Facet
import com.intellij.openapi.module.Module
import com.intellij.openapi.util.Disposer

class MerFacet(
        facetType: MerFacetType,
        module: Module,
        name: String,
        configuration: MerFacetConfiguration,
        underlyingFacet: Facet<*>?
) : Facet<MerFacetConfiguration>(
        facetType,
        module,
        name,
        configuration,
        underlyingFacet) {

    init {
        Disposer.register(this, configuration)
    }
}
