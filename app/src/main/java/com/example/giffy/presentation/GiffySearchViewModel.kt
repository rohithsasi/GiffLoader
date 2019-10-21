package com.example.giffy.presentation

import android.app.Application
import androidx.lifecycle.*
import com.example.giffy.repository.GiffyDataRepository
import com.example.giffy.repository.GiffyResult
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BlockChainViewModel(application :Application) : AndroidViewModel(application) {
    /**
     * The only reference this class hols is that of the repository.
     */
    private val repository by lazy { GiffyDataRepository.get() }
    private val _searchResult = MutableLiveData<GiffyResult>()

    val searchResult: LiveData<GiffyResult>
    get()=_searchResult

    fun getGifs(searchParam: String = "popular") =viewModelScope.launch {
            var result: GiffyResult? = null
            withContext(Dispatchers.Default) {
                result = repository.getGifs(searchParam)
            }
            _searchResult.value = result
        }

}
