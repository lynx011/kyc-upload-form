package com.example.kyc_test

interface PhotoChosenListener {
    fun getPhotoFromGallery(type:String, data : String)
    fun getPhotoFromCamera(type: String, data : String)
}