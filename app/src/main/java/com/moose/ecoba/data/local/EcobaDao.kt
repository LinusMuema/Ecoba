package com.moose.ecoba.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moose.ecoba.domain.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface EcobaDao {
    /**
     * Add the user details to the local db
     * Set [OnConflictStrategy] to replace the user in case, data is updated from the backend
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    /**
     * Get the user profile.
     * Limit is set to one as there will always be one user in th e database.
     * Used a flow so as to observe the changes in the UI module.
     * */
    @Query("select * from user limit 1")
    fun getUser(): Flow<User>
}