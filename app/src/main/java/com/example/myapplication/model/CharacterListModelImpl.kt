package com.example.myapplication.model

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ApiClient
import com.example.myapplication.ListaAdapter
import com.example.myapplication.model.interfaces.CharacterListModel
import com.example.myapplication.models.getAll.Root
import com.example.myapplication.presenter.interfaces.CharacterListPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest

class CharacterListModelImpl: CharacterListModel {
    lateinit var presenter: CharacterListPresenter
    val apiClient= ApiClient()
    override fun getCharacterListInformationModel() {
        val ts = System.currentTimeMillis().toString()
        val hash = md5Hash(ts + "b4a78d558a13ee1c267304f39908d68b002b840e" +"5851f6999387039a3ea907434bca6d5c")
        val all = apiClient.getClient().getAll(ts,hash)
        all.enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {

                response.body()?.let { presenter.onInformationListReceived(it) }

            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                Log.i("MainActivity", "onFailure: Todo mal")
            }
        })
    }

    override fun setCharacterListInformacionModel(presenter: CharacterListPresenter) {
        this.presenter= presenter
    }

    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }
}