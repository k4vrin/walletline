package com.walletline.android.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UnidirectionalViewModel<STATE, EFFECT, EVENT> {
    val state: StateFlow<STATE>
    val effect: Flow<EFFECT>
    fun onEvent(event: EVENT)
}

data class StateEffectDispatch<STATE, EFFECT, EVENT>(
    val state: STATE,
    val effect: Flow<EFFECT>,
    val dispatch: (EVENT) -> Unit
)

@Composable
inline fun <reified STATE, EFFECT, EVENT> use(
    viewModel: UnidirectionalViewModel<STATE, EFFECT, EVENT>
): StateEffectDispatch<STATE, EFFECT, EVENT> {

    val state: STATE by viewModel.state.collectAsStateWithLifecycle()

    val dispatch: (EVENT) -> Unit = { event ->
        viewModel.onEvent(event)
    }

    return StateEffectDispatch(
        state = state,
        effect = viewModel.effect,
        dispatch = dispatch
    )
}