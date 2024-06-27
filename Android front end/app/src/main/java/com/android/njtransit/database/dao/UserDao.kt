package com.android.njtransit.database.dao

import androidx.room.*
import com.android.njtransit.database.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getUserDetails(): Flow<List<User>>

}
