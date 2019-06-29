package tuppersoft.com.domain.usescases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params> where Type : Any? {

    abstract suspend fun run(params: Params): Type

    operator fun invoke(params: Params, onResult: (Type) -> Unit = {}) : Boolean{
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { run(params) }
            onResult(result)
        }
        return true
    }
}