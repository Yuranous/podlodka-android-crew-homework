package com.example.dota.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dota.repository.DotaRepository
import com.example.dota.ui.widgets.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


sealed class DotaScreenState(
    val isLoading: Boolean,
)

object DotaScreenLoadingState : DotaScreenState(isLoading = true)

data class DotaScreenLoadedState(
    val widgets: List<Widget>,
) : DotaScreenState(isLoading = false)

class DotaViewModel : ViewModel() {

    private val repository by lazy { DotaRepository() }

    private val viewModelState = MutableStateFlow<DotaScreenState>(DotaScreenLoadingState)
    private val states = mutableMapOf<Widget, MutableStateFlow<WidgetState>>(
        Widget.DESCRIPTION to MutableStateFlow(DescriptionBlockLoadingState),
        Widget.COMMENTS to MutableStateFlow(CommentsBlockLoadingState),
        Widget.SCREENSHOTS to MutableStateFlow(ScreenshotWidgetLoadingState),
        Widget.RATING to MutableStateFlow(RatingBlockLoadingState),
    )

    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value
        )

    init {
        viewModelState.update { DotaScreenLoadingState }

        viewModelScope.launch {
            val widgets = repository.getWidgets()

            viewModelState.update { DotaScreenLoadedState(widgets) }

            widgets.forEach { widget ->
                when (widget) {
                    Widget.DESCRIPTION -> launch {
                        val description = repository.getDescription()
                        states[widget]?.update { description }
                    }
                    Widget.COMMENTS -> launch {
                        val comments = repository.getComments()
                        states[widget]?.update { comments }
                    }
                    Widget.SCREENSHOTS -> launch {
                        val screenshots = repository.getScreenshots()
                        states[widget]?.update { screenshots }
                    }
                    Widget.RATING -> launch {
                        val rating = repository.getRating()
                        states[widget]?.update { rating }
                    }
                }
            }
        }
    }

    fun getWidgetState(widget: Widget): StateFlow<WidgetState> {
        require(states.contains(widget))
        val state = states[widget]
        if (state != null) {
            return state.stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                state.value
            )
        }
        throw IllegalStateException()
    }
}