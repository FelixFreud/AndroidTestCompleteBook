package com.foxsouls.androidtestcompletebook.c3_Applicated

import androidx.room.*

@Dao
interface RepositoryDAO {
    @Insert
    fun insertAll(vararg repositories: Repository)

    @Query("SELECT * FROM repository WHERE owner = owner")
    fun findByOwner(owner: String): List<Repository>
}

@Database(entities = [Repository::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDAO
}