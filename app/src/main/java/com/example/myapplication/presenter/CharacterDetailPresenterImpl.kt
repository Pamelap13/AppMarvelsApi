package com.example.myapplication.presenter

import com.example.myapplication.model.CharacterDetailModelImpl
import com.example.myapplication.model.interfaces.CharacterDetailModel
import com.example.myapplication.models.getById.Root
import com.example.myapplication.presenter.interfaces.CharacterDetailPresenter
import com.example.myapplication.view.interfaces.CharacterDetailView

class CharacterDetailPresenterImpl: CharacterDetailPresenter {
    lateinit var view: CharacterDetailView
    lateinit var model: CharacterDetailModel

    init {
        model= CharacterDetailModelImpl()
        model.setCharacterDetailPresenter(this)
    }
    override fun getCharacterDetailInformation(id: Int) {
        model.getCharacterDetailInformation(id)

    }

    override fun setCharacterDetailView(view: CharacterDetailView) {
        this.view = view
    }

    override fun onInformationReceived(information: Root) {
        view.showCharacterDetailInformation(information)
    }
}