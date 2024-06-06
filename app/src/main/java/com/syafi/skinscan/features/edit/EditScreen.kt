package com.syafi.skinscan.features.edit

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.syafi.skinscan.features.edit.component.ChangePassword
import com.syafi.skinscan.features.edit.component.EditProfile
import com.syafi.skinscan.util.Constant

@Composable
fun EditScreen(
    navController: NavController,
    changeType: String,
    name: String,
    email: String,
    viewModel: EditViewModel= hiltViewModel()
) {

    when (changeType) {
        Constant.EDIT_PROFILE -> EditProfile(navController, name, email, viewModel)
        Constant.CHANGE_PASSWORD -> ChangePassword(navController, viewModel)
    }
}