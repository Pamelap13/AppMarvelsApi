package com.example.myapplication.presenter.interfaces

import com.example.myapplication.models.getAll.Root
import com.example.myapplication.view.interfaces.CharacterListView

interface CharacterListPresenter {
    fun getCharacterListInformation()
    fun onInformationListReceived(information: Root)
    fun setCharacterListInformation(view: CharacterListView)
}
