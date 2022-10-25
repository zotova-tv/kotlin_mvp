package ru.gb.kotlinmvp.main.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.kotlinmvp.main.view.MainView
import ru.gb.kotlinmvp.screens.IScreens

class MainPresenter(val router: Router, val screens: IScreens) :
    MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }
    fun backClicked() {
        router.exit()
    }
}
