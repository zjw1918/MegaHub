package io.mega.megahub.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "first_name") var firstName: String,
    @ColumnInfo(name = "last_name") var lastName: String,
    @ColumnInfo(defaultValue = "China") var address: String,
    @Ignore var sex: Boolean
)

@Dao
interface UserDao {
    @Query("select * from user")
    fun getAll(): LiveData<List<User>>

    @Query("select * from user where uid in (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<User>

    @Query("select * from user where first_name like :first and last_name like :last limit 1")
    suspend fun findByName(first: String, last: String): User

    @Insert
    suspend fun insertAll(vararg users: User)

    @Delete
    suspend fun delete(user: User)
}