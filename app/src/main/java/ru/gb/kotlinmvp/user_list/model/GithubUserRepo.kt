package ru.gb.kotlinmvp.user_list.model.repository

import ru.gb.kotlinmvp.user_list.model.GithubUser

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )
    fun getUsers() : List<GithubUser> {
        return repositories
    }
}
