package com.example

import com.example.route.RecentWatchListRouteTest
import com.example.route.UserRouteTesting
import com.example.route.WatchListRouteTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    UserRouteTesting::class,
    RecentWatchListRouteTest::class,
    WatchListRouteTest::class
)

class TestRouteSuite