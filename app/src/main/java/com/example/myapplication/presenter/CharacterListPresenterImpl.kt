package com.example.myapplication.presenter

import com.example.myapplication.model.CharacterListModelImpl
import com.example.myapplication.model.interfaces.CharacterListModel
import com.example.myapplication.models.getAll.Root
import com.example.myapplication.presenter.interfaces.CharacterListPresenter
import com.example.myapplication.view.interfaces.CharacterListView

class CharacterListPresenterImpl: CharacterListPresenter {
    lateinit var view: CharacterListView
    lateinit var model: CharacterListModel

    init {
        model= CharacterListModelImpl()
        model.setCharacterListInformacionModel(this)
    }
    override fun getCharacterListInformation() {
        model.getCharacterListInformationModel()
    }

    override fun onInformationListReceived(information: Root) {
        view.showCharacterLisInformation(information)
    }

    override fun setCharacterListInformation(view: CharacterListView) {
        this.view= view
    }
}