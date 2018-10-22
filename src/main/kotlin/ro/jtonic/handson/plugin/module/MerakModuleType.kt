package ro.jtonic.handson.plugin.module

import com.intellij.openapi.module.ModuleType
import ro.jtonic.handson.plugin.icon.Icons
import javax.swing.Icon

class MerakModuleType: ModuleType<MerakModuleBuilder>("Merak") {
    override fun createModuleBuilder(): MerakModuleBuilder  = MerakModuleBuilder()

    override fun getName() = "Merak"

    override fun getDescription() = "Merak module"

    override fun getNodeIcon(isOpened: Boolean): Icon = Icons.MERAK

    companion object {
        val INSTANCE: MerakModuleType = MerakModuleType()
    }
}
