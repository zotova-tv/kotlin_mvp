package ru.gb.kotlinmvp.user.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.kotlinmvp.user.view.UserView

class UserPresenter(val router: Router): MvpPresenter<UserView>() {
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}