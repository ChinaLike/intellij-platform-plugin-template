package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation
import other.convertName
import other.moduleName

fun armsActivity(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsActivityKt(provider) else armsActivityJava(provider)

private fun armsActivityKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value}

import android.os.Bundle
import com.jess.arms.di.component.AppComponent
import ${provider.appPackageName.value}.base.activity.Base${moduleName(provider.appPackageName.value)}Activity
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter
import ${provider.appPackageName.value}.databinding.${convertName(provider)}

${commonAnnotation(provider)}
class ${provider.pageName.value}Activity : Base${moduleName(provider.appPackageName.value)}Activity<${provider.pageName.value}Presenter,${convertName(provider)}>() , ${provider.pageName.value}Contract.View {
    
    override fun setupActivityComponent(appComponent: AppComponent) {
        Dagger${provider.pageName.value}Component
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].toLowerCase()}${provider.pageName.value.substring(1, provider.pageName.value.length)}Module(${provider.pageName.value}Module(this))
                .build()
                .inject(this)
    }
    
    override fun initBindingView(savedInstanceState: Bundle?) {

    }
    
    override fun initBindingData(savedInstanceState: Bundle?) {

    }
    
}
    
"""

private fun armsActivityJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value};

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import com.jess.arms.di.component.AppComponent;
import ${provider.appPackageName.value}.base.activity.Base${moduleName(provider.appPackageName.value)}Activity;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;
import ${provider.appPackageName.value}.databinding.${convertName(provider)};

${commonAnnotation(provider)}
public class ${provider.pageName.value}Activity extends Base${moduleName(provider.appPackageName.value)}Activity<${provider.pageName.value}Presenter,${convertName(provider)}> implements ${provider.pageName.value}Contract.View {
    
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        Dagger${provider.pageName.value}Component
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }
    
    override fun initBindingView(savedInstanceState: Bundle?) {

    }
    
    override fun initBindingData(savedInstanceState: Bundle?) {

    }
}
    
"""