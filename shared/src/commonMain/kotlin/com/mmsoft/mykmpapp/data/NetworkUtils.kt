package com.mmsoft.mykmpapp.data

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException

suspend fun <T> safeApiCall(call: suspend () -> T): Result<T> {
    return try {
        Result.success(call())
    }catch (e: RedirectResponseException){
        //Errors 3xx
        Result.failure(Exception("Redirection error: ${e.response.status.description}"))
    } catch (e: ClientRequestException) {
        // Errors 4xx
        Result.failure(Exception("Client error: ${e.response.status.description}"))
    } catch (e: ServerResponseException) {
        // Errors 5xx
        Result.failure(Exception("Server error: ${e.response.status.description}"))
    } catch (e: IOException) {
        // Connection errors (No internet, timeout)
        Result.failure(Exception("No internet connection or timeout"))
    } catch (e: Exception) {
        // Any other error
        Result.failure(e)
    }
}