package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.armsAnnotation
import other.commonAnnotation

fun armsContract(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsContractKt(provider) else armsContractJava(provider)

private fun armsContractKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.contractPackageName.value}
${
    when {
        provider.needActivity.value -> {
            "import android.app.Activity"
        }
        provider.needFragment.value -> {
            "import androidx.fragment.app.Fragment"
        }
        else -> ""
    }
}
import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel

${commonAnnotation(provider)}
interface ${provider.pageName.value}Contract {
    
    interface View : IView
    
    interface Model : IModel
}    
"""

fun armsContractJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.contractPackageName.value};
${
    when {
        provider.needActivity.value -> {
            "import android.app.Activity;"
        }
        provider.needFragment.value -> {
            "import androidx.fragment.app.Fragment;"
        }
        else -> ""
    }
}
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

${commonAnnotation(provider)}
public interface ${provider.pageName.value}Contract {
    interface View extends IView{
    
    }
   
    interface Model extends IModel{
    
    }
}    
"""