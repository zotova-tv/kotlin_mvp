package ru.gb.kotlinmvp.user_list.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.gb.kotlinmvp.screens.AndroidScreens
import ru.gb.kotlinmvp.user_list.model.GithubUser
import ru.gb.kotlinmvp.user_list.model.repository.GithubUsersRepo
import ru.gb.kotlinmvp.user_list.view.UserItemView
import ru.gb.kotlinmvp.user_list.view.UsersView
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router, val screens: AndroidScreens) :
    MvpPresenter<UsersView>() {

    private var disposable: Disposable? = null

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
        // testMap()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.user(itemView.getLogin()))
        }
    }
    fun loadData() {
        val users = usersRepo.getUsers()
        disposable = Observable
            .fromIterable(users)
            .subscribe {
                viewState.updateList()
            }
        usersListPresenter.users.addAll(users)
    }

    fun testMap(){
        var strings = listOf(
            "String 1",
            "String 2",
            "String 3",
            "String 4",
            "String 5",
            "String 6",
            "String 7",
            "String 8",
            "String 9"
        )
        val disposable2: Disposable = Observable
            .fromIterable(
                strings
            )
            .flatMap {
                val delay = Random.nextInt(1000).toLong()
                return@flatMap Observable.just(it + "x").delay(delay, TimeUnit.MILLISECONDS)
            }
            .subscribe{
                println("Flat Map $it")
            }

        val disposable3: Disposable = Observable
            .fromIterable(
                strings
            )
            .switchMap {
                val delay = Random.nextInt(1000).toLong()
                return@switchMap Observable.just(it + "x").delay(delay, TimeUnit.MILLISECONDS)
            }
            .subscribe{
                println("Switch Map $it")
            }

//        I/System.out: Flat Map String 3x
//        I/System.out: Flat Map String 7x
//        I/System.out: Flat Map String 1x
//        I/System.out: Switch Map String 9x
//        I/System.out: Flat Map String 5x
//        I/System.out: Flat Map String 6x
//        I/System.out: Flat Map String 2x
//        I/System.out: Flat Map String 8x
//        I/System.out: Flat Map String 4x
//        I/System.out: Flat Map String 9x

    }


    fun backPressed(): Boolean {
        router.exit()
        disposable?.dispose()
        return true
    }
}
