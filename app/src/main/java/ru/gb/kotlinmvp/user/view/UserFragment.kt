package ru.gb.kotlinmvp.user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.kotlinmvp.App
import ru.gb.kotlinmvp.BackButtonListener
import ru.gb.kotlinmvp.databinding.FragmentUserBinding
import ru.gb.kotlinmvp.user.presenter.UserPresenter
import ru.gb.kotlinmvp.user_list.view.UsersFragment

class UserFragment: MvpAppCompatFragment(), UserView, BackButtonListener {

    private var vb: FragmentUserBinding? = null
    val presenter: UserPresenter by moxyPresenter { UserPresenter(App.instance.router) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentUserBinding.inflate(inflater, container, false)
        arguments?.getString(LOGIN_KEY).let {
            vb?.userLogin?.text = it
        }
        return vb?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    companion object {

        const val LOGIN_KEY = "LOGIN_KEY"

        fun newInstance(login: String): UserFragment {
            return UserFragment().also {
                it.arguments = Bundle().apply {
                    putString(LOGIN_KEY, login)
                }
            }
        }
    }

    override fun backPressed() = presenter.backPressed()
}