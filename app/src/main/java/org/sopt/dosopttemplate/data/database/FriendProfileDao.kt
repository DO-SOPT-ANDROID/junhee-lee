package org.sopt.dosopttemplate.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.sopt.dosopttemplate.data.entity.home.FriendProfileEntity

@Dao
interface FriendProfileDao {
    @Query("SELECT * from table_friend_profile")
    suspend fun getAll(): List<FriendProfileEntity>

    @Insert
    suspend fun add(entity: FriendProfileEntity)

    @Insert
    suspend fun insertAll(entities: List<FriendProfileEntity>)

    @Query("DELETE FROM table_friend_profile WHERE name = :name")
    suspend fun deleteProfileById(name: String)
}