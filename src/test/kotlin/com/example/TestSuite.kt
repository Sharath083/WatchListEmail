package com.example

//import com.example.repository.UserDaoImplTest
//import com.example.repository.WatchListDaoImplTest
import com.example.route.RecentWatchListRouteTest
import com.example.route.UserRouteTesting
import com.example.route.WatchListRouteTest
import com.example.service.RecentWatchListServiceTest
//import com.example.service.UserServiceTest
import com.example.service.WatchListService
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
//    UserDaoImplTest::class,
//    WatchListDaoImplTest::class,
//    RecentWatchListDaoImplTest::class,
//    UserServiceTest::class,
    WatchListService::class,
    RecentWatchListServiceTest::class,
    UserRouteTesting::class,
    WatchListRouteTest::class,
    RecentWatchListRouteTest::class
)

class TestSuite