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
import com.example.myapplication.presenter.CharacterListPresenterImpl
import com.example.myapplication.presenter.interfaces.CharacterListPresenter
import com.example.myapplication.view.interfaces.CharacterListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest

class CharactersListFragment : Fragment(), com.example.myapplication.Callback, CharacterListView {

    lateinit var presenter: CharacterListPresenter
    val apiClient = ApiClient()
    lateinit var progress: ProgressBar
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter= CharacterListPresenterImpl()
        presenter.setCharacterListInformation(this)
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
        presenter.getCharacterListInformation()
    }

    override fun onItemClick(id: Int) {
        val b: Bundle= Bundle()
        b.putInt("id",id)
        view?.let { Navigation.findNavController(it).navigate(R.id.action_charactersListFragment_to_characterDetailFragment,b) }
    }

    override fun showCharacterLisInformation(information: Root) {
        recyclerView.adapter= information.data.results?.let { ListaAdapter(it,this@CharactersListFragment) }
        recyclerView.layoutManager= LinearLayoutManager(context)
        progress.visibility = View.GONE
        recyclerView.visibility= View.VISIBLE
    }
}