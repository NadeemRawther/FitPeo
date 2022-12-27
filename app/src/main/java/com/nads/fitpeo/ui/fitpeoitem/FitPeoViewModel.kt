package com.nads.fitpeo.ui.fitpeoitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nads.fitpeo.data.repo.FitPeoDefaultRepo.Companion.fitpeoitemList

import com.nads.fitpeo.data.repo.FitPeoRepository
import com.nads.fitpeo.model.FitPeoResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FitPeoViewModel @Inject constructor(private val fitPeoRepository: FitPeoRepository):ViewModel() {
    init {

    }
    private val _cards = MutableStateFlow(listOf<FitPeoResponseItem>())
    val cards: StateFlow<List<FitPeoResponseItem>> get() = _cards
    private val _loading = MutableStateFlow(false)
    val loading: MutableStateFlow<Boolean> get() = _loading
    private val _online = MutableStateFlow(true)
    val online: MutableStateFlow<Boolean> get() = _online
    private val _error = MutableStateFlow(false)
    val error: MutableStateFlow<Boolean> get() = _error
    private val _fitPeoCardItem = MutableStateFlow(fitpeoitemList.get(0))
    val fitPeoCardItem: StateFlow<FitPeoResponseItem> get() = _fitPeoCardItem

    fun getFitPeoList(){
         _loading.value = true
        viewModelScope.launch {

            val fitlist = fitPeoRepository.getFitPeoList()
            if (fitlist.isSuccess){
                fitlist.map {
                    it-> it.let {
                    _cards.emit(it)
                    }
                }
            }else {
                _error.value = true
            }
          _loading.value = false
        }

    }
    fun getFitPeoItem(id:String){
        _loading.value = true
        viewModelScope.launch {
            val fitItem = fitPeoRepository.getFitPeoListItem(id)
            if (fitItem.isSuccess){
               fitItem.map {
                   _fitPeoCardItem.value= it
               }
            }
         _loading.value = false
        }
    }
}