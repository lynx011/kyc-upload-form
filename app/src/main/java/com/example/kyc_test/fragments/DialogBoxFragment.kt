package com.example.kyc_test.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kyc_test.PhotoChosenListener
import com.example.kyc_test.R
import com.example.kyc_test.databinding.FragmentDialogBoxBinding
import com.github.dhaval2404.imagepicker.ImagePicker

private const val ARG_PARAM1 = "param1"
class DialogBoxFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogBoxBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoType = it.getString(ARG_PARAM1).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogBoxBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectGallery.setOnClickListener {
            ImagePicker.with(this)
                .galleryOnly()
                .galleryMimeTypes(arrayOf("image/*"))
                .maxResultSize(400,300)
                .crop()
                .start(101)
        }

        binding.takePhoto.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .maxResultSize(400,300)
                .crop()
                .start(102)
        }

    }

    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onActivityResult(requestCode, resultCode, data)",
        "androidx.fragment.app.DialogFragment"
    )
    )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK){
            listener?.getPhotoFromGallery(photoType,data?.data.toString())
            dismiss()
        }
        if (requestCode == 102 && resultCode == Activity.RESULT_OK){
            listener?.getPhotoFromCamera(photoType,data?.data.toString())
            dismiss()
        }
    }

    companion object {
        private var listener: PhotoChosenListener? = null
        private var photoType: String = ""
        @JvmStatic
        fun newInstance(type: String, callback : PhotoChosenListener) =
            DialogBoxFragment().apply {
                listener = callback
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, type)
                }
            }
    }
}

//interface PhotoChosenListener {
//    fun getPhotoFromGallery(type:String, data : String)
//    fun getPhotoFromCamera(type: String, data : String)
//}