package com.example.myapplication.presenter.interfaces

import com.example.myapplication.models.getById.Root
import com.example.myapplication.view.interfaces.CharacterDetailView

interface CharacterDetailPresenter {
    fun getCharacterDetailInformation(id: Int)
    fun setCharacterDetailView(view: CharacterDetailView)
    fun onInformationReceived(information:  Root)
}