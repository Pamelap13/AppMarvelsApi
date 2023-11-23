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
import com.example.myapplication.presenter.CharacterDetailPresenterImpl
import com.example.myapplication.presenter.interfaces.CharacterDetailPresenter
import com.example.myapplication.view.interfaces.CharacterDetailView
import com.squareup.picasso.Picasso


class CharacterDetailFragment : Fragment(), CharacterDetailView {

    lateinit var presenter: CharacterDetailPresenter

    lateinit var imageView: ImageView
    lateinit var textName: TextView
    lateinit var textDescription: TextView
    lateinit var progressDetail: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter= CharacterDetailPresenterImpl()
        presenter.setCharacterDetailView(this)
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
        val id = arguments?.getInt("id")
        id?.let {
            presenter.getCharacterDetailInformation(it)
        }
    }

    override fun showLoading() {
        progressDetail.visibility= View.VISIBLE
    }

    override fun showCharacterDetailInformation(information: Root) {
        hideLoading()
        textName.text= information.data.results.first().name
        textDescription.text= information.data.results.first().description

        val url= information.data.results.first().thumbnail.path.replace("http","https") +
                "." + information.data.results.first().thumbnail.extension

        Picasso
            .get()
            .load(url)
            .into(imageView)
    }

    override fun hideLoading() {
        progressDetail.visibility= View.GONE
    }
}