package org.ireader.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import org.ireader.components.components.SearchToolbar
import org.ireader.settings.setting.font_screens.FontScreen
import org.ireader.settings.setting.font_screens.FontScreenViewModel
import org.ireader.ui_settings.R

@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterialApi::class)
object FontScreenSpec : ScreenSpec {
    override val navHostRoute: String = "font_screen_spec"

    @Composable
    override fun TopBar(
        controller: ScreenSpec.Controller
    ) {
        val vm: FontScreenViewModel = hiltViewModel(   controller.navBackStackEntry)
        SearchToolbar(
            title = stringResource(R.string.font),
            onPopBackStack = {
                controller.navController.popBackStack()
            },
            onValueChange = {
                vm.searchQuery = it
            },
            onSearch = {
                vm.searchQuery = it
            }
        )
    }

    @Composable
    override fun Content(
        controller: ScreenSpec.Controller
    ) {
        val vm: FontScreenViewModel = hiltViewModel(   controller.navBackStackEntry)

        Box(modifier = Modifier.padding(controller.scaffoldPadding)) {
            FontScreen(
                vm
            )
        }

    }
}

