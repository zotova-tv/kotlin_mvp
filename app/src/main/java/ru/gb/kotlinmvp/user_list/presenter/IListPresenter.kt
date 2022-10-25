package ru.gb.kotlinmvp.user_list.presenter

import ru.gb.kotlinmvp.user_list.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}