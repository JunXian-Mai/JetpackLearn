package org.markensic.learn.jetpack.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddressLinkViewModel : ViewModel() {

    val provName = MutableLiveData("省份")

    val capName = MutableLiveData("省会")

    val emailName = MutableLiveData("电邮")
}