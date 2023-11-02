package org.sopt.dosopttemplate.data.datasource.local

import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.entity.home.Profile
import java.time.LocalDate
import javax.inject.Inject


interface ProfileDataSource {
    fun getProfileList(): List<Profile>
}

class DummyProfile @Inject constructor() : ProfileDataSource {
    override fun getProfileList(): List<Profile> {
        return listOf(
            Profile.MyProfile(
                profileImage = R.drawable.img_my_profile,
                name = "이준희",
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_cat,
                name = "고양이",
                birthday = LocalDate.of(2023, 11, 1),
                music = null,
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_dog,
                name = "강아지",
                birthday = LocalDate.of(2023, 11, 16),
                music = "\"Shape of You\" - Ed Sheeran",
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_eagle,
                name = "독수리",
                birthday = LocalDate.of(2023, 11, 15),
                music = "\"Bad Guy\" - Billie Eilish",
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_elephant,
                name = "코끼리",
                birthday = LocalDate.of(2023, 11, 14),
                music = null,
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_fish,
                name = "블롭피시",
                birthday = LocalDate.of(2023, 7, 1),
                music = "\"Dance Monke\" - Tones and I",
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_fox,
                name = "여우",
                birthday = LocalDate.of(2001, 11, 1),
                music = "\"Rolling in the Deep\" - Adele",
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_giraffe,
                name = "기린",
                birthday = LocalDate.of(2023, 12, 2),
                music = null,
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_horse,
                name = "말",
                birthday = LocalDate.of(2023, 11, 19),
                music = null,
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_koala,
                name = "코알라",
                birthday = LocalDate.of(2023, 1, 10),
                music = null,
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_mongus,
                name = "몽구스",
                birthday = LocalDate.of(2023, 11, 3),
                music = null,
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_monkey,
                name = "원숭이",
                birthday = LocalDate.of(2023, 11, 9),
                music = "\"Havana\" - Camila Cabello",
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_panda,
                name = "판다",
                birthday = LocalDate.of(2023, 11, 11),
                music = "\"Old Town Road\" - Lil Nas X ft. Billy Ray Cyrus",
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_tiger,
                name = "호랑이",
                birthday = LocalDate.of(2023, 11, 2),
                music = null,
                isTodayBirthday = false,
                isMusicRegist = false,
            ),
        )
    }
}
