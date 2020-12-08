package br.pro.nigri.assessmentandroid.ViewModel

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.nigri.assessmentandroid.api.RetroFitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiViewModel():ViewModel() {
    var detailsNumber = MutableLiveData<NumVerifyViewModel>()

    fun chamarApi(numero:String, context:Context){
        var call = RetroFitClient.getNumVerifyService().getNumVerify("de9a37465178f32050626277776ac38d","55${numero}","1")

        call.enqueue(
            object : Callback<NumVerifyViewModel> {

                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(
                    call: Call<NumVerifyViewModel>,
                    response: Response<NumVerifyViewModel>
                ) {

                    var numResult = response.body()

                    var numVerifyViewModel = NumVerifyViewModel(numResult!!.valid,numResult.country_name,numResult.location,numResult.carrier)

                    detailsNumber.value = numVerifyViewModel


                }

                override fun onFailure(call: Call<NumVerifyViewModel>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

            }
        )
    }
}