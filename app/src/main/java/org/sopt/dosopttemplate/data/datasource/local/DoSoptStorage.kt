package org.sopt.dosopttemplate.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import org.sopt.dosopttemplate.data.entity.sign.SignInfo

object DoSoptStorage {
    private lateinit var pref: SharedPreferences

    fun init(context: Context) {
        pref = context.getSharedPreferences(
            SIGN_PREFS,
            Context.MODE_PRIVATE,
        )
    }

    fun setUserInfo(user: SignInfo) {
        pref.edit().run {
            putString(USER_ID, user.id)
            putString(USER_PW, user.password)
            putString(USER_NAME, user.name)
            putString(USER_MBTI, user.mbti)
        }.apply()
    }

    fun getUserInfo(): SignInfo? {
        with(pref) {
            val id = getString(USER_ID, null)
            val password = getString(USER_PW, null)
            val name = getString(USER_NAME, null)
            val mbti = getString(USER_MBTI, null)

            if (name == null || id == null) return null

            return SignInfo(id, password.toString(), name, mbti.toString())
        }
    }

    fun userInfoClear() {
        pref.edit().clear().apply()
    }
}

const val SIGN_PREFS = "sign_prefs"
const val USER_ID = "userId"
const val USER_PW = "userPw"
const val USER_NAME = "userName"
const val USER_MBTI = "userMbti"
