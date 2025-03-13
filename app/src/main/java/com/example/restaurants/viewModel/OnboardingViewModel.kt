package com.example.restaurants.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurants.data.OnboardingUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    @ApplicationContext context: Context, private val onboardingUser: OnboardingUser
) : ViewModel() {

    private var loginStatusFlow: MutableSharedFlow<Boolean> = MutableSharedFlow()
    fun getLoginStatusFlow(): MutableSharedFlow<Boolean> = loginStatusFlow

    private var registrationStatusFlow: MutableSharedFlow<Boolean?> = MutableSharedFlow()
    fun getRegistrationStatusFlow(): MutableSharedFlow<Boolean?> = registrationStatusFlow

    fun onClickNext(userName: String, password: String, isForLogin: Boolean) {
        viewModelScope.launch {
            if (isForLogin) {
                val user = onboardingUser.getUserDetails(userName, password)
                loginStatusFlow.emit(user != null)
            } else {
                val user = onboardingUser.getUserDetails(userName)
                if (user != null) {
                    registrationStatusFlow.emit(false)
                } else {
                    onboardingUser.registerUser(userName, password)
                    registrationStatusFlow.emit(true)
                }
            }
        }
    }

}