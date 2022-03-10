package com.app.gifvfeed.presentation.ui.feed

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.gifvfeed.Interactors
import com.app.gifvfeed.domain.entity.SetParams
import com.app.gifvfeed.domain.entity.TimeLineItem
import com.app.gifvfeed.presentation.ui.feed.components.FeedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FeedScreenViewModel @Inject constructor(
    private val interactors: Interactors
) : ViewModel() {

    private val setParams = mutableStateOf(SetParams())

    private val _feedItemsState = mutableStateOf<FeedState>(FeedState.InitLoading)
    val feedItemsState: State<FeedState> = _feedItemsState

    private var _lastItemsLoaded = mutableListOf<TimeLineItem>()

    private val loadingMore = mutableStateOf(false)
    val getLoadingState get() = loadingMore


    fun getTimeLineItems() {
        val lastId = setParams.value.lastId
        val lastSortingValue = setParams.value.lastSortingValue

        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){loadingMore.value = true}
            interactors.getTimeLineUseCase(lastId, lastSortingValue).also {
                when {
                    it.isSuccess -> {
                        val newSetParams = it.getOrNull()!!.second
                        if(newSetParams.lastId != 0 && lastSortingValue != 0){
                            setParams.value = newSetParams
                        }

                        _lastItemsLoaded += it.getOrNull()!!.first
                        _feedItemsState.value = FeedState.Success(_lastItemsLoaded)
                    }

                    it.isFailure -> {
                        _lastItemsLoaded.clear()
                        _feedItemsState.value = FeedState.Error(
                            it.exceptionOrNull()!!.message ?: "Error while loading content"
                        )
                    }
                }
                loadingMore.value = false
            }
        }
    }

}