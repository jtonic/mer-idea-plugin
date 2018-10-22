package ro.jtonic.handson.plugin.framework

import com.intellij.ide.util.frameworkSupport.FrameworkSupportConfigurable
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel
import com.intellij.ide.util.frameworkSupport.FrameworkSupportProvider
import com.intellij.openapi.module.ModuleType
import ro.jtonic.handson.plugin.facet.MerakFacetType
import ro.jtonic.handson.plugin.icon.Icons
import ro.jtonic.handson.plugin.module.MerakModuleType
import javax.swing.Icon

/**
 * Created by Antonel Ernest Pazargic on 22/10/2018.
 * @author Antonel Ernest Pazargic
 */
class MerakFrameworkSupportProvider: FrameworkSupportProvider("Merak", MerakFacetType.INSTANCE.presentableName) {

    override fun getIcon(): Icon = Icons.MERAK

    override fun isEnabledForModuleType(moduleType: ModuleType<*>): Boolean {
        return moduleType is MerakModuleType
    }

    override fun createConfigurable(model: FrameworkSupportModel): FrameworkSupportConfigurable {
        return MerakFrameworkSupportConfigurable(model)
    }
}
