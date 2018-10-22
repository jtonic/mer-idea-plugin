package ro.jtonic.handson.plugin.module

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.roots.ModifiableRootModel

class MerakModuleBuilder: ModuleBuilder() {
    override fun getModuleType(): ModuleType<*>  = MerakModuleType.INSTANCE

    override fun setupRootModel(p0: ModifiableRootModel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
