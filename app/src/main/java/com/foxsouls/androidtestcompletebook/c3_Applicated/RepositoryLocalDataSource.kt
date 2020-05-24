package com.foxsouls.androidtestcompletebook.c3_Applicated

class RepositoryLocalDataSource(val db: AppDatabase) {
    fun insertAll(vararg repositories: Repository) {
        db.repositoryDao().insertAll(*repositories)
    }

    fun findByOwner(owner: String): List<Repository> {
        return db.repositoryDao().findByOwner(owner)
    }
}