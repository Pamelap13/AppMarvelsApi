package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


class CharacterDetailFragment : Fragment() {

    val apiClient = ApiClient()
    lateinit var imageView: ImageView
    lateinit var textName: TextView
    lateinit var textDescription: TextView
    lateinit var progressDetail: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_character_detail, container, false)
        imageView= view.findViewById(R.id.image_detail)
        textName= view.findViewById(R.id.text_detail)
        textDescription= view.findViewById(R.id.text_detail_description)
        progressDetail= view.findViewById(R.id.progres_detail)
        return view
    }

    override fun onResume() {
        super.onResume()
        val ts = System.currentTimeMillis().toString()
        val hash = md5Hash(ts + "b4a78d558a13ee1c267304f39908d68b002b840e" +"5851f6999387039a3ea907434bca6d5c")
       // val b: Bundle?= intent.extras


        val characterById = arguments?.let { apiClient.getClient().getById(it.getInt("id"),ts, hash) }
        //val characterById = b?.let { apiClient.getClient().getById(it.getInt("id"),ts, hash ) }
        characterById?.enqueue(object: Callback<Root> {
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