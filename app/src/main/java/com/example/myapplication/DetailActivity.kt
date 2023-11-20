package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.myapplication.models.getById.Root
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest

class DetailActivity : AppCompatActivity() {
    val apiClient = ApiClient()
    lateinit var imageView: ImageView
    lateinit var textName: TextView
    lateinit var textDescription: TextView
    lateinit var progressDetail: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        imageView= findViewById(R.id.image_detail)
        textName= findViewById(R.id.text_detail)
        textDescription= findViewById(R.id.text_detail_description)
        progressDetail= findViewById(R.id.progres_detail)
    }

    override fun onResume() {
        super.onResume()
        val ts = System.currentTimeMillis().toString()
        val hash = md5Hash(ts + "b4a78d558a13ee1c267304f39908d68b002b840e" +"5851f6999387039a3ea907434bca6d5c")
        val b: Bundle?= intent.extras
        

        val characterById = b?.let { apiClient.getClient().getById(it.getInt("id"),ts, hash ) }
        characterById?.enqueue(object: Callback<Root>{
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                textName.text= response.body()?.data?.results?.first()?.name
                textDescription.text= response.body()?.data?.results?.first()?.description

                val url= response.body()?.data?.results?.first()?.thumbnail?.path?.replace("http","https") +
                        "." + response.body()?.data?.results?.first()?.thumbnail?.extension

                Picasso
                    .get()
                    .load(url)
                    .into(imageView)
                progressDetail.visibility= View.GONE
            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }
}