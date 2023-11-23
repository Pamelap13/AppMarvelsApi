package com.example.myapplication.view.interfaces

import com.example.myapplication.models.getById.Root

interface CharacterDetailView {
    fun showLoading()
    fun showCharacterDetailInformation(information: Root)
    fun hideLoading()
}