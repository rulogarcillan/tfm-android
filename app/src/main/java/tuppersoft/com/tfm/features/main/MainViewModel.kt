package tuppersoft.com.tfm.features.main

import androidx.lifecycle.MutableLiveData
import arrow.core.None
import tuppersoft.com.tfm.core.extension.log
import tuppersoft.com.tfm.core.platform.GlobalViewModel
import tuppersoft.com.domain.entities.CharacterDto
import tuppersoft.com.domain.usescases.GetCharacters
import javax.inject.Inject

class MainViewModel @Inject constructor(getCharacters: GetCharacters) : GlobalViewModel() {

    val listCharacter: MutableLiveData<MutableList<CharacterDto>> = MutableLiveData()

    init {
        getCharacters.invoke(None) {
            it.fold(::handleFailure, ::handleGetCharacters)
        }
    }

    fun handleGetCharacters(items: MutableList<CharacterDto>) {
        items.size.toString().log()
    }
}