package org.sopt.dosopttemplate.data.entity.home

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
@Entity(tableName = "table_friend_profile")
data class FriendProfileEntity(
    val name: String,
    val birthday: LocalDate,
    val music: String?,
    val isTodayBirthday: Boolean,
    val isMusicRegist: Boolean,
    @ColumnInfo(name = "image_uri")
    val imageUri: Int,
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}