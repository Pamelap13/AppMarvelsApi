package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.getAll.Root
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest

class CharactersListFragment : Fragment(), com.example.myapplication.Callback {


    val apiClient = ApiClient()
    lateinit var progress: ProgressBar
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View= inflater.inflate(R.layout.fragment_characters_list, container, false)
        progress= view.findViewById(R.id.progressbar)
        recyclerView= view.findViewById(R.id.recycler_view)
        return view
    }

    override fun onResume() {
        super.onResume()
        val ts = System.currentTimeMillis().toString()
        val hash = md5Hash(ts + "b4a78d558a13ee1c267304f39908d68b002b840e" +"5851f6999387039a3ea907434bca6d5c")
        val all = apiClient.getClient().getAll(ts,hash)
        all.enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                Log.i("MainActivity",response.body().toString())
                Log.i("MainActivity","total: " + response.body()?.data?.total.toString())
                Log.i("MainActivity","count: " + response.body()?.data?.count.toString())
                val lista = response.body()?.data?.results

                recyclerView.adapter= lista?.let { ListaAdapter(it,this@CharactersListFragment) }
                recyclerView.layoutManager= LinearLayoutManager(context)
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
        //val intent: Intent = Intent(context,CharacterDetailFragment::class.java)
        val b: Bundle= Bundle()
        b.putInt("id",id)
        view?.let { Navigation.findNavController(it).navigate(R.id.action_charactersListFragment_to_characterDetailFragment,b) }
        //intent.putExtras(b)
        //startActivity(intent)


    }
}