package com.example.myapplication23.screen.home

import androidx.lifecycle.viewModelScope
import com.example.myapplication23.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    override fun fetchData(): Job = viewModelScope.launch {
        // TODO get from repository
    }

}