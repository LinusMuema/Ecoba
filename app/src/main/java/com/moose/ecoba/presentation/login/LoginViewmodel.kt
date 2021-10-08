package com.moose.ecoba.presentation.login

import android.util.Log
import com.moose.ecoba.utils.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moose.ecoba.domain.EcobaRepository
import com.moose.ecoba.domain.models.Credentials
import com.moose.ecoba.utils.parse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(private val repository: EcobaRepository): ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _result = MutableLiveData<Result<String>>()
    val result: LiveData<Result<String>> = _result

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Exception", "Exception: $exception")
        _loading.value = false
        _result.value = Result.Error(exception.parse())
    }

    fun login(email: String, password: String) {
        viewModelScope.launch(handler) {
            _loading.value = true
            repository.login(Credentials(email, password))

            _result.postValue(Result.Success("success"))
            _loading.value = false
        }
    }
}