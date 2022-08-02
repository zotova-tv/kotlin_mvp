package ru.gb.kotlinmvp

class CountersPresenter(
    private val view: MainView
) {
    private val model = CountersModel()

    fun onCounterClick(position: Int) {
        val newValue = model.next(position)
        view.setCounterText(newValue.toString(), position)
    }
}
