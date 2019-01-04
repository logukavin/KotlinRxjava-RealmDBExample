package com.example.orgware.kotlinrealmdb.base

/**
 * Interface representing a View that will use to load data.
 */
interface LoadDataView : BaseView {
    /**
     * Show a view with a progress bar indicating a loading process.
     */
    fun showLoading()

    /**
     * Hide a loading view.
     */
    fun hideLoading()

    fun showError(message: String)
}