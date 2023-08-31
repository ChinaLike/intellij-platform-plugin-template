package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation
import other.convertName
import other.moduleName

fun armsFragment(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsFragmentKt(provider) else armsFragmentJava(provider)

private fun armsFragmentKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value}

import android.os.Bundle
import ${provider.appPackageName.value}.base.Base${moduleName(provider.appPackageName.value)}Fragment
import com.jess.arms.di.component.AppComponent
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter
import ${provider.appPackageName.value}.databinding.${convertName(provider,false)}

${commonAnnotation(provider)}
class ${provider.pageName.value}Fragment : Base${moduleName(provider.appPackageName.value)}Fragment<${provider.pageName.value}Presenter,${convertName(provider,false)}>() , ${provider.pageName.value}Contract.View{
 
    override fun setupFragmentComponent(appComponent:AppComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].toLowerCase()}${provider.pageName.value.substring(1,provider.pageName.value.length)}Module(${provider.pageName.value}Module(this))
                .build()
                .inject(this)
    }
    
    /**
     * 初始化视图
     */
    override fun initBindingView(savedInstanceState: Bundle?) {

    }
    
    override fun initBindingData(savedInstanceState: Bundle?) {

    }
    
    override fun getUmengPageName(): String? {
        return "${provider.pageName.value}Fragment"
    }
    
}
    
"""


fun armsFragmentJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value};

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ${provider.appPackageName.value}.base.Base${moduleName(provider.appPackageName.value)}Fragment;
import com.jess.arms.di.component.AppComponent;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;
import ${provider.appPackageName.value}.databinding.${convertName(provider,false)}

${commonAnnotation(provider)}
class ${provider.pageName.value}Fragment extends Base${moduleName(provider.appPackageName.value)}Fragment<${provider.pageName.value}Presenter,${convertName(provider,false)}> implements ${provider.pageName.value}Contract.View{
    
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }
    
    /**
     * 初始化视图
     */
    override void initBindingView(Bundle savedInstanceState) {

    }
    
    override void initBindingData(Bundle savedInstanceState) {

    }
    
    override String getUmengPageName() {
        return "${provider.pageName.value}Fragment"
    }
    
}
    
"""