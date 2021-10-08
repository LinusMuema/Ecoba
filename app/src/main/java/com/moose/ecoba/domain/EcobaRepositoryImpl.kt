package com.moose.ecoba.domain

import com.moose.ecoba.data.Preferences
import com.moose.ecoba.data.local.EcobaDao
import com.moose.ecoba.data.remote.ApiEndpoints
import com.moose.ecoba.domain.models.Credentials
import com.moose.ecoba.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EcobaRepositoryImpl @Inject constructor(
    private val db: EcobaDao,
    private val api: ApiEndpoints,
    private val preferences: Preferences,
): EcobaRepository {

    override val user: Flow<User> get() = db.getUser()

    override suspend fun addUser(user: User) = db.addUser(user)

    /**
     * The whole login process
     * 1. Send the data to the endpoint
     * 2. Get the token from a successful response and save it in preferences
     * 3. Save the user in the local database
     * */
    override suspend fun login(credentials: Credentials) {
        val response = api.login(credentials)

        db.addUser(response.user)
        preferences.setToken(response.token)
    }
}