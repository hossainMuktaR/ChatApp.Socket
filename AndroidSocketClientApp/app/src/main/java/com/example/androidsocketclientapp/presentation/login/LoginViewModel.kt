package com.example.androidsocketclientapp.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel(){

    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName
    
    private val _onJoinChat = MutableSharedFlow<String>()
    val onJoinChat: SharedFlow<String> = _onJoinChat

    fun onUserNameChange(userName: String) {
        _userName.value = userName
    }

    fun onJoin() {
        viewModelScope.launch {
            if(userName.value.isNotBlank()){
                _onJoinChat.emit(userName.value)
            }
        }
    }
    
}
