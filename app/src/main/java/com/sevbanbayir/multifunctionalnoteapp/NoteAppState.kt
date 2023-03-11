package com.sevbanbayir.multifunctionalnoteapp

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import com.sevbanbayir.multifunctionalnoteapp.common.presentation.snackbar.SnackbarManager
import com.sevbanbayir.multifunctionalnoteapp.common.presentation.snackbar.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Stable
class NoteAppState(
    val navController: NavController,
    val scaffoldState: ScaffoldState,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                scaffoldState.snackbarHostState.showSnackbar(text)
            }
        }
    }

    fun navigate(route:String) = navController.navigate(route) { launchSingleTop = true }

    fun popUp() = navController.popBackStack()

    fun navigateAndPopUp(route: String, popUp: String) = navController.navigate(route) {
        launchSingleTop = true
        popUpTo(popUp) { inclusive = true}
    }

    fun clearAndNavigate(route: String) = navController.navigate(route) {
        launchSingleTop = true
        popUpTo(0) { inclusive = true }
    }
}