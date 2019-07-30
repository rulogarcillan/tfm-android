package tuppersoft.com.adoptme.features.add

import androidx.lifecycle.MutableLiveData
import tuppersoft.com.adoptme.core.platform.GlobalViewModel
import tuppersoft.com.domain.entities.Animal
import tuppersoft.com.domain.entities.ImgDto
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.entities.Sex
import tuppersoft.com.domain.usescases.SaveRecord
import tuppersoft.com.domain.usescases.UploadImage
import java.io.File
import javax.inject.Inject

class AddViewModel @Inject constructor(private val uploadImage: UploadImage, private val saveRecord: SaveRecord) : GlobalViewModel() {

    val recordDto: MutableLiveData<RecordDto> = MutableLiveData(RecordDto())
    val urlPhoto1: MutableLiveData<String> = MutableLiveData()
    val urlPhoto2: MutableLiveData<String> = MutableLiveData()
    val urlPhoto3: MutableLiveData<String> = MutableLiveData()
    val urlPhoto4: MutableLiveData<String> = MutableLiveData()
    val isOk: MutableLiveData<Boolean> = MutableLiveData()

    fun setUid(uid: String) {
        recordDto.value = recordDto.value?.copy(uid = uid)
    }

    fun addAnimal(animal: Animal) {
        recordDto.value = recordDto.value?.copy(animal = animal)
    }

    fun addName(name: String) {
        recordDto.value = recordDto.value?.copy(name = name)
    }

    fun addAge(age: Int) {
        recordDto.value = recordDto.value?.copy(age = age)
    }

    fun addSex(sex: Sex) {
        recordDto.value = recordDto.value?.copy(sex = sex)
    }

    fun uploadPhoto1(file: File) {
        val params = UploadImage.Params(file, true)
        uploadImage.invoke(params) {
            it.fold(::handleFailure, ::handleUploadPhoto1)
        }
    }

    fun uploadPhoto2(file: File) {
        val params = UploadImage.Params(file, true)
        uploadImage.invoke(params) {
            it.fold(::handleFailure, ::handleUploadPhoto2)
        }
    }

    fun uploadPhoto3(file: File) {
        val params = UploadImage.Params(file, true)
        uploadImage.invoke(params) {
            it.fold(::handleFailure, ::handleUploadPhoto3)
        }
    }

    fun uploadPhoto4(file: File) {
        val params = UploadImage.Params(file, true)
        uploadImage.invoke(params) {
            it.fold(::handleFailure, ::handleUploadPhoto4)
        }
    }

    private fun handleUploadPhoto1(imgDto: ImgDto) {
        if (imgDto.success) {
            urlPhoto1.value = imgDto.data.link
            recordDto.value = recordDto.value?.copy(imageUrl = genArrayPhotos())
        }
    }

    private fun handleUploadPhoto2(imgDto: ImgDto) {
        if (imgDto.success) {
            urlPhoto2.value = imgDto.data.link
            recordDto.value = recordDto.value?.copy(imageUrl = genArrayPhotos())
        }
    }

    private fun handleUploadPhoto3(imgDto: ImgDto) {
        if (imgDto.success) {
            urlPhoto3.value = imgDto.data.link
            recordDto.value = recordDto.value?.copy(imageUrl = genArrayPhotos())
        }
    }

    private fun handleUploadPhoto4(imgDto: ImgDto) {
        if (imgDto.success) {
            urlPhoto4.value = imgDto.data.link
            recordDto.value = recordDto.value?.copy(imageUrl = genArrayPhotos())
        }
    }

    private fun genArrayPhotos(): MutableList<String> {
        val list: MutableList<String> = mutableListOf()

        urlPhoto1.value?.let {
            if (it.isNotEmpty()) {
                list.add(it)
            }
        }
        urlPhoto2.value?.let {
            if (it.isNotEmpty()) {
                list.add(it)
            }
        }
        urlPhoto3.value?.let {
            if (it.isNotEmpty()) {
                list.add(it)
            }
        }
        urlPhoto4.value?.let {
            if (it.isNotEmpty()) {
                list.add(it)
            }
        }
        return list
    }

    fun saveRecord(recordDto: RecordDto) {
        saveRecord.invoke(SaveRecord.Params(recordDto)) {
            isOk.value = true
        }
    }
}