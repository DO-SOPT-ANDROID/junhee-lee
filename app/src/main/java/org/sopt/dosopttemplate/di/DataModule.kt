package org.sopt.dosopttemplate.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.database.FriendProfileDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideFriendProfileDataBase(@ApplicationContext context: Context): FriendProfileDataBase {
        return Room.databaseBuilder(context, FriendProfileDataBase::class.java, "friend_profile_db")
            .build()
    }

    @Provides
    @Singleton
    fun provideFriendDao(friendDataBase: FriendProfileDataBase) = friendDataBase.dao()
}
