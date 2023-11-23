package com.example.myapplication.model

import com.example.myapplication.ApiClient
import com.example.myapplication.model.interfaces.CharacterDetailModel
import com.example.myapplication.models.getById.Root
import com.example.myapplication.presenter.interfaces.CharacterDetailPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest

class CharacterDetailModelImpl: CharacterDetailModel {
    lateinit var presenter: CharacterDetailPresenter
    val apiClient = ApiClient()
    override fun getCharacterDetailInformation(id: Int) {
        val ts = System.currentTimeMillis().toString()
        val hash = md5Hash(ts + "b4a78d558a13ee1c267304f39908d68b002b840e" +"5851f6999387039a3ea907434bca6d5c")
        val characterById = apiClient.getClient().getById(id,ts, hash)

        characterById?.enqueue(object: Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {

                response.body()?.let { presenter.onInformationReceived(it) }
            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun setCharacterDetailPresenter(presenter: CharacterDetailPresenter) {
        this.presenter= presenter
    }

    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }


}