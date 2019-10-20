package com.example.giffy.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giffy.coroutine.MainCoroutineScope
import com.example.giffy.repository.GiffyDataRepository
import com.example.giffy.repository.GiffyResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * The presentation layer that acquires the data from the lower repository layer and emmits the processed ui data
 * to the Views with the help of an event bus. Now its called a view model as its the architecture is more representative
 * of MVVVM as opposed to MVP. Just like MVVP this presentation layer hold no hard reference to the Ui layer or the botton networking layer
 * This layer interact with network layer via a repository.
 */
class BlockChainViewModel : ViewModel() {

    protected val uiScope = MainCoroutineScope()
    /**
     * The only reference this class hols is that of the repository.
     */
    private val repository by lazy { GiffyDataRepository.get() }

    private val _uiData = MutableLiveData<GiffyResult>()
    val uiData: LiveData<GiffyResult> = _uiData

    fun getData(searchParam: String = "popular") =
        uiScope.launch {
            var result: GiffyResult? = null
            withContext(Dispatchers.Default) {
                result = repository.getData(searchParam)
            }

            //TODO Do i need a mapping in here of types. Coroutine error handling
            _uiData.value = result
//                when(result){
//                    is OnSuccessGiffyResult -> _uiData.value = result
//                    is OnFailurGiffyResult -> TODO()
//                }
        }


}
