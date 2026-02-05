package com.example.todoapp.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.Model.State.ProfileField
import com.example.todoapp.R
import com.example.todoapp.View.Components.EditFieldProfilComponents
import com.example.todoapp.View.Components.ProfileRow
import com.example.todoapp.ViewModel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                            contentDescription = "retour",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(12.dp))

            // Image profil
            Image(
                painter = painterResource(R.drawable.profil),
                contentDescription = "Image profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.height(36.dp))

            ProfileRow(stringResource(R.string.profile_row_firstname_label), uiState.firstname) {
                viewModel.openDialog(ProfileField.FIRSTNAME)
            }

            ProfileRow(stringResource(R.string.profile_row_lastname_label), uiState.lastname) {
                viewModel.openDialog(ProfileField.LASTNAME)
            }

            ProfileRow(stringResource(R.string.profile_row_email_label), uiState.email) {
                viewModel.openDialog(ProfileField.EMAIL)
            }

            ProfileRow(stringResource(R.string.profile_row_password_label), "••••••••") {
                viewModel.openDialog(ProfileField.PASSWORD)
            }

            Spacer(Modifier.height(40.dp))

            // Bouton Log out via ViewModel
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.logout {
                            onLogout()
                        }
                    }
                    .padding(vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.logout),
                    contentDescription = "Logout",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = stringResource(R.string.profile_button_logout),
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 16.sp
                )
            }
        }
    }

    // Dialog pour éditer les champs
    if (uiState.showDialog) {
        EditFieldProfilComponents(
            title = "${stringResource(R.string.profile_dialog_title)} ${uiState.editingField?.name?.lowercase()?.replaceFirstChar { it.uppercase() }}",
            value = uiState.editingValue,
            onValueChange = { viewModel.updateEditingValue(it) },
            onDismiss = { viewModel.closeDialog() },
            onSave = { viewModel.saveField() }
        )
    }
}