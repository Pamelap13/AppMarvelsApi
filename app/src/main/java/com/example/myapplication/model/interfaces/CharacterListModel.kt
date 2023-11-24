package com.example.myapplication.model.interfaces

import com.example.myapplication.presenter.interfaces.CharacterListPresenter

interface CharacterListModel {
    fun getCharacterListInformationModel()
    fun setCharacterListInformacionModel(presenter: CharacterListPresenter)
}