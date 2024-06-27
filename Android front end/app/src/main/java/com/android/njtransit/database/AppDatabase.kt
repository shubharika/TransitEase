package com.android.njtransit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.njtransit.database.dao.UserDao
import com.android.njtransit.database.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    val DATABASE_NAME: String = "app_database"

    //List of DAOs used by the DB
    abstract fun userDao(): UserDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        //Used to get an instance of the database
        fun getDatabase(context: Context):AppDatabase {
            return INSTANCE ?: synchronized(this){
                return Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    AppDatabase::DATABASE_NAME.name
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}