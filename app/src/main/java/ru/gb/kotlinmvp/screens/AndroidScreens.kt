package ru.gb.kotlinmvp.screens

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.gb.kotlinmvp.user.view.UserFragment
import ru.gb.kotlinmvp.user_list.view.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(login: String) = FragmentScreen {
        UserFragment.newInstance(login)
    }

}
