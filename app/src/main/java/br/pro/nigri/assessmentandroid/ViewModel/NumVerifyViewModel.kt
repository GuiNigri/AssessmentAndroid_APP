package br.pro.nigri.assessmentandroid.ViewModel

import androidx.lifecycle.ViewModel

class NumVerifyViewModel(
    var valid:Boolean?=null,
    var country_name:String?=null,
    var location:String?=null,
    var carrier:String?=null
):ViewModel()
