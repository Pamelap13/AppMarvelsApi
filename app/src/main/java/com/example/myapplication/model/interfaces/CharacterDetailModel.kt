package com.example.myapplication.model.interfaces

import com.example.myapplication.presenter.interfaces.CharacterDetailPresenter

interface CharacterDetailModel {
    fun getCharacterDetailInformation(id: Int)
    fun setCharacterDetailPresenter(presenter: CharacterDetailPresenter)
}