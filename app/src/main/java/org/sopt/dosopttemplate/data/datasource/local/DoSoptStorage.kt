package org.sopt.dosopttemplate.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import org.sopt.dosopttemplate.data.entity.auth.AuthInfo

object DoSoptStorage {
    private lateinit var pref: SharedPreferences

    fun init(context: Context) {
        pref = context.getSharedPreferences(
            SIGN_PREFS,
            Context.MODE_PRIVATE,
        )
    }

    fun setUserInfo(user: AuthInfo) {
        pref.edit().run {
            userId = user.id
            putString(USER_NAME, user.username)
            putString(USER_NICKNAME, user.name)
        }.apply()
    }

    fun getUserInfo(): AuthInfo {
        with(pref) {
            val userName = getString(USER_NAME, null)
            val name = getString(USER_NICKNAME, null)

            return AuthInfo(userId, userName.toString(), "", name.toString(), mbti.toString())
        }
    }

    var isLogin: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)
        set(value) = pref.edit { putBoolean(IS_LOGIN, value).apply() }
    var userId: Int
        get() = pref.getInt(USER_ID, 0)
        set(value) = pref.edit { putInt(USER_ID, value).apply() }

    var mbti: String?
        get() = pref.getString(USER_MBTI, null)
        set(value) = pref.edit { putString(USER_MBTI, value).apply() }

    fun userInfoClear() {
        pref.edit().clear().apply()
    }
}

const val SIGN_PREFS = "sign_prefs"
const val USER_ID = "userId"
const val USER_NAME = "userName"
const val USER_NICKNAME = "userNickName"
const val IS_LOGIN = "isLogin"
const val USER_MBTI = "userMbti"
