package ru.gb.kotlinmvp.user_list.view

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun getLogin(): String
}
