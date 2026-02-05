package com.example.todoapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todoapp.Model.State.ProfileField
import com.example.todoapp.Model.State.ProfileUiState
import com.example.todoapp.Repository.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel(
    private val repo: UserRepo = UserRepo()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadUser()
    }

    // Charge l'utilisateur connecté
    private fun loadUser() {
        repo.getCurrentUser { user ->
            user?.let {
                _uiState.update { state ->
                    state.copy(
                        firstname = it.firstName,
                        lastname = it.lastName,
                        email = it.email
                    )
                }
            }
        }
    }

    // Ouvre le dialogue pour éditer un champ
    fun openDialog(field: ProfileField) {
        _uiState.update {
            it.copy(
                showDialog = true,
                editingField = field,
                editingValue = when (field) {
                    ProfileField.FIRSTNAME -> it.firstname
                    ProfileField.LASTNAME -> it.lastname
                    ProfileField.EMAIL -> it.email
                    ProfileField.PASSWORD -> ""
                }
            )
        }
    }

    fun updateEditingValue(value: String) {
        _uiState.update { it.copy(editingValue = value) }
    }

    // Permet de sauvegarder les champs qui sont modifier par l'utilisateur
    fun saveField() {
        val field = _uiState.value.editingField ?: return
        val newValue = _uiState.value.editingValue

        when (field) {
            ProfileField.EMAIL -> {
                // Met à jour Auth
                repo.updateEmail(newValue) { success, error ->
                    if (success) {
                        // Met à jour Firestore seulement si Auth a réussi
                        repo.updateField("email", newValue) { _ ->
                            _uiState.update { state ->
                                state.copy(email = newValue, showDialog = false, editingField = null)
                            }
                        }
                    } else {
                        Log.e("ProfileViewModel", "Erreur updateEmail: $error")
                    }
                }
            }
            ProfileField.FIRSTNAME -> {
                repo.updateField("firstName", newValue) { _ ->
                    _uiState.update { state ->
                        state.copy(firstname = newValue, showDialog = false, editingField = null)
                    }
                }
            }
            ProfileField.LASTNAME -> {
                repo.updateField("lastName", newValue) { _ ->
                    _uiState.update { state ->
                        state.copy(lastname = newValue, showDialog = false, editingField = null)
                    }
                }
            }
            ProfileField.PASSWORD -> {
                // Gestion future si tu veux changer le mot de passe
            }
        }
    }

    // Ferme le dialogue
    fun closeDialog() {
        _uiState.update { it.copy(showDialog = false, editingField = null) }
    }

    // Déconnexion de l'utilisateur
    fun logout(onComplete: () -> Unit) {
        repo.logout {
            onComplete()
        }
    }
}




