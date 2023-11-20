package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.getAll.Root
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest

class MainActivity : AppCompatActivity(){
    val apiClient = ApiClient()
    lateinit var progress: ProgressBar
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  progress= findViewById(R.id.progressbar)
        //recyclerView= findViewById(R.id.recycler_view)
    }

   /* override fun onResume() {
        super.onResume()
        val ts = System.currentTimeMillis().toString()
        val hash = md5Hash(ts + "b4a78d558a13ee1c267304f39908d68b002b840e" +"5851f6999387039a3ea907434bca6d5c")
        val all = apiClient.getClient().getAll(ts,hash)
        all.enqueue(object : Callback<Root>{
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                Log.i("MainActivity",response.body().toString())
                Log.i("MainActivity","total: " + response.body()?.data?.total.toString())
                Log.i("MainActivity","count: " + response.body()?.data?.count.toString())
                val lista = response.body()?.data?.results

                recyclerView.adapter= lista?.let { ListaAdapter(it,this@MainActivity) }
                recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
                progress.visibility = View.GONE
                recyclerView.visibility= View.VISIBLE

            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                Log.i("MainActivity", "onFailure: Todo mal")
            }
        })
    }

    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

    override fun onItemClick(id: Int) {
        val intent: Intent= Intent(this,DetailActivity::class.java)
        val b: Bundle= Bundle()
        b.putInt("id",id)
        intent.putExtras(b)
        startActivity(intent)
    }*/

}