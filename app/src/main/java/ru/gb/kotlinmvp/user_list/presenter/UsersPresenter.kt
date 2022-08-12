package ru.gb.kotlinmvp.user_list.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.gb.kotlinmvp.screens.AndroidScreens
import ru.gb.kotlinmvp.user_list.model.GithubUser
import ru.gb.kotlinmvp.user_list.model.repository.GithubUsersRepo
import ru.gb.kotlinmvp.user_list.view.UserItemView
import ru.gb.kotlinmvp.user_list.view.UsersView

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router, val screens: AndroidScreens) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }
    val usersListPresenter = UsersListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.user(itemView.getLogin()))
        }
    }
    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
