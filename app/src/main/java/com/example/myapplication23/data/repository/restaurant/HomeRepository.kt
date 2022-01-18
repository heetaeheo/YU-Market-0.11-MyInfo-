package com.example.myapplication23.data.repository.restaurant

import com.example.myapplication23.model.homelist.HomeListModel
import com.example.myapplication23.screen.home.homelist.HomeCategory

interface HomeRepository {
    // TODO Entity?
    fun getList(homeCategory: HomeCategory) : List<HomeListModel>
}