package com.example.di



import com.example.dao.RecentWatchListDao
import com.example.dao.UserDao
import com.example.dao.WatchListDao
import com.example.repository.UserDaoImpl
import com.example.repository.WatchListDaoImpl
import com.example.repository.RecentWatchListDaoImpl

import com.example.service.WatchListService
import com.example.service.UserServices
import com.example.service.RecentWatchListService


import com.example.utils.helperfunctions.HelperMethods
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.bind
import org.koin.dsl.module


val myModule = module {
    singleOf(::UserDaoImpl) {bind<UserDao>()}
    singleOf(::WatchListDaoImpl) {bind<WatchListDao>()}
    singleOf(::RecentWatchListDaoImpl) {bind<RecentWatchListDao>()}

    singleOf(::WatchListService)
    singleOf(::RecentWatchListService)
    singleOf(::UserServices)

    singleOf(::HelperMethods)



}
