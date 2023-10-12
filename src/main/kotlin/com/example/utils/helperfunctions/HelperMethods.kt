package com.example.utils.helperfunctions


import com.example.exceptions.InvalidEmailException
import com.example.exceptions.InvalidEmailFormatException
import com.example.exceptions.InvalidNameException
import com.example.exceptions.InvalidPasswordException
import com.example.model.Symbol
import com.example.model.UpdateWatchList
import com.example.model.UserRegistration
import com.example.model.WatchlistData
import io.ktor.http.*
import io.ktor.server.plugins.requestvalidation.*
import org.koin.core.component.KoinComponent


class HelperMethods: KoinComponent {
    private fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"))
    }
    fun validateUserRegistration(userRegistration: UserRegistration):ValidationResult{
        with(userRegistration) {
            return when {
                name.isNullOrBlank() -> ValidationResult.Invalid("Name Field must not be blank ")
                password.isNullOrBlank() -> ValidationResult.Invalid("password Field must not be blank ")
                email.isNullOrBlank() -> ValidationResult.Invalid("Email Field must not be blank ")
                !isValidEmail(email) -> ValidationResult.Invalid("Invalid Email Format ")
                else -> ValidationResult.Valid
            }
        }
    }
    fun validateWatchlistCreation(watchlistData: WatchlistData): ValidationResult {
        with(watchlistData) {
            return when {
                userId.isNullOrBlank() -> ValidationResult.Invalid("User ID must not be blank")
                else -> validateSymbols(symbols)
            }
        }
    }
    private fun validateSymbols(symbols: List<Symbol>): ValidationResult {
        return when {
            symbols.isEmpty() -> ValidationResult.Invalid("Symbols list must not be empty")
            symbols.any { it.asset.isNullOrBlank() } -> ValidationResult.Invalid("Asset in symbols must not be blank")
            symbols.any { it.strike.toString().isBlank() } -> ValidationResult.Invalid("Strike in symbols must not be blank")
            symbols.any { it.lotSize.toString().isBlank() } -> ValidationResult.Invalid("Lot in symbols must not be blank")
            symbols.any { it.tickSize.toString().isBlank() } -> ValidationResult.Invalid("Tick in symbols must not be blank")
            symbols.any { it.streamSym.isNullOrBlank() } -> ValidationResult.Invalid("Stream  in symbols must not be blank")
            symbols.any { it.instrument.isNullOrBlank() } -> ValidationResult.Invalid("Instrument in symbols must not be blank")
            symbols.any { it.multiplier.toString().isBlank() } -> ValidationResult.Invalid("Multiplier in symbols must not be blank")
            symbols.any { it.displayName.isNullOrBlank() } -> ValidationResult.Invalid("Display name in symbols must not be blank")
            symbols.any { it.companyName.isNullOrBlank() } -> ValidationResult.Invalid("Company name in symbols must not be blank")
            symbols.any { it.expiry.isNullOrBlank() } -> ValidationResult.Invalid("Expiry in symbols must not be blank")
            symbols.any { it.symbolTag.isNullOrBlank() } -> ValidationResult.Invalid("Symbol tag in symbols must not be blank")
            symbols.any { it.sector.isNullOrBlank() } -> ValidationResult.Invalid("Sector in symbols must not be blank")
            symbols.any { it.exchange.isNullOrBlank() } -> ValidationResult.Invalid("Exchange in symbols must not be blank")
            symbols.any { it.isIn.isNullOrBlank() } -> ValidationResult.Invalid("ISIN in symbols must not be blank")
            symbols.any { it.baseSymbol.isNullOrBlank() } -> ValidationResult.Invalid("Base symbol in symbols must not be blank")
            else -> ValidationResult.Valid
        }
    }

    fun validateUpdateWatchlistCreation(details: UpdateWatchList): ValidationResult {
        with(details) {
            return when {
                userId.isNullOrBlank() -> ValidationResult.Invalid("User ID must not be blank")
                watchListId.isNullOrBlank()->ValidationResult.Invalid("Watch ID must not be blank")
                else -> validateSymbols(symbols)
            }
        }

    }
}