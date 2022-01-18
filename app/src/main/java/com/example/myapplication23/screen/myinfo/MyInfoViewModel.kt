package com.example.myapplication23.screen.myinfo

import androidx.lifecycle.viewModelScope
import com.example.myapplication23.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyInfoViewModel : BaseViewModel() {
    override fun fetchData(): Job = viewModelScope.launch {  }
}