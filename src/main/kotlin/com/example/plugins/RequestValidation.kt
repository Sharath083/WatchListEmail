package com.example.plugins

import com.example.exceptions.InvalidEmailException
import com.example.exceptions.InvalidEmailFormatException
import com.example.exceptions.InvalidNameException
import com.example.exceptions.InvalidPasswordException
import com.example.model.*
import com.example.utils.helperfunctions.HelperMethods
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import org.koin.ktor.ext.inject

fun Application.configureRequestValidation(){
    install(RequestValidation){
        val helperFunction by inject<HelperMethods>()
        validate<UserRegistration> {
            when(val result=helperFunction.validateUserRegistration(it)){
                is ValidationResult.Invalid -> result
                else->ValidationResult.Valid
            }
        }
//        validate<WatchlistData>{
//            when(val result=helperFunction.validateWatchlistCreation(it)){
//                is ValidationResult.Invalid -> result
//                else -> ValidationResult.Valid
//            }
//        }
//        validate<UpdateWatchList> {
//            when(val result=helperFunction.validateUpdateWatchlistCreation(it)){
//                is ValidationResult.Invalid -> result
//                else->ValidationResult.Valid
//            }
//        }
        validate<RecentWatchlistData> {
            when{
                it.userId.isNullOrBlank()->ValidationResult.Invalid("UserId must not be blank")
                else->ValidationResult.Valid
            }
        }
        validate<DeleteWatchlist> {
            when{
                it.watchlistName.isNullOrBlank()->ValidationResult.Invalid("WatchListId must not be blank")
                else->ValidationResult.Valid
            }
        }
    }

}