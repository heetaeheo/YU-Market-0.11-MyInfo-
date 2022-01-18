package com.example.myapplication23.di

import com.example.myapplication23.data.repository.map.DefaultMapRepository
import com.example.myapplication23.data.repository.map.MapRepository
import com.example.myapplication23.data.repository.restaurant.DefaultHomeRepository
import com.example.myapplication23.data.repository.restaurant.HomeRepository
import com.example.myapplication23.screen.MainViewModel
import com.example.myapplication23.screen.home.homelist.HomeCategory
import com.example.myapplication23.screen.home.homelist.HomeListViewModel
import com.example.myapplication23.screen.home.HomeViewModel
import com.example.myapplication23.screen.like.LikeViewModel
import com.example.myapplication23.screen.map.MapViewModel
import com.example.myapplication23.screen.myinfo.MyInfoViewModel
import com.example.myapplication23.screen.orderlist.OrderListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel() }

    factory { (homeCategory: HomeCategory) ->
        HomeListViewModel(homeCategory, get())
    }

    viewModel { MainViewModel(get()) }
    viewModel { LikeViewModel() }
    viewModel { MapViewModel() }
    viewModel { MyInfoViewModel() }
    viewModel { OrderListViewModel() }

    single<HomeRepository> { DefaultHomeRepository() }

    single { buildOkHttpClient() }
    single { provideGsonConverterFactory() }

    single(named("map")) { provideMapRetrofit(get(), get()) }
    single { provideMapApiService(get(qualifier = named("map"))) }

    single<MapRepository> { DefaultMapRepository(get(), get()) }

    single { Dispatchers.IO }

}