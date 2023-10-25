package org.sopt.dosopttemplate.data.datasource.local

import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.entity.home.Profile

class DummyProfile {
    val mockProfileList = listOf(
        Profile.MyProfile(
            profileImage = R.drawable.img_my_profile,
            name = "이준희",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_cat,
            name = "고양이",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_dog,
            name = "강아지",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_eagle,
            name = "독수리",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_elephant,
            name = "코끼리",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_fish,
            name = "블롭피시",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_fox,
            name = "여우",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_giraffe,
            name = "기린",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_horse,
            name = "말",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_koala,
            name = "코알라",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_mongus,
            name = "몽구스",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_monkey,
            name = "원숭이",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_panda,
            name = "판다",
        ),
        Profile.FriendProfile(
            profileImage = R.drawable.img_tiger,
            name = "호랑이",
        ),
    )
}
