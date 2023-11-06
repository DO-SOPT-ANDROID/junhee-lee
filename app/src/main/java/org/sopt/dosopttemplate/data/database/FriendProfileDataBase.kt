package org.sopt.dosopttemplate.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.sopt.dosopttemplate.data.entity.home.FriendProfileEntity

@Database(entities = [FriendProfileEntity::class], version = 2, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class FriendProfileDataBase : RoomDatabase() {

    abstract fun dao(): FriendProfileDao

}
