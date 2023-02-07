package com.example.kyc_test.fragments
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.canhub.cropper.CropImageView
import com.example.kyc_test.R
import com.example.kyc_test.databinding.FragmentConfirmBinding

class ConfirmFragment : Fragment() {
    private lateinit var binding : FragmentConfirmBinding
    private lateinit var cropImageView : CropImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cropImageView = CropImageView(requireContext())
        val args = arguments
        val selfie = args?.getString("selfie")
        val front = args?.getString("front")
        val back = args?.getString("back")

        if (selfie != null){
            binding.cropView.setImageBitmap(BitmapFactory.decodeFile(selfie))
        }
//        if (front != null){
//            binding.cropView.setImageBitmap(BitmapFactory.decodeFile(front))
//        }
//        if (back != null){
//            binding.cropView.setImageBitmap(BitmapFactory.decodeFile(back))
//        }
        //        if(front != null){
//            binding.confirmImg.setImageBitmap(BitmapFactory.decodeFile(front))
//        }
//        if(back != null){
//            binding.confirmImg.setImageBitmap(BitmapFactory.decodeFile(back))
//        }
//        cropImageView.getCroppedImage(reqHeight = 100, reqWidth = 100)

        binding.cropBtn.setOnClickListener {
            binding.cropView.isVisible = false
            binding.confirmImg.isVisible = true
            val cropped: Bitmap? = binding.cropView.getCroppedImage()
            Log.d("cropped", cropped.toString())
            if (cropped != null) {
                Glide.with(this)
                    .asBitmap()
                    .load(cropped)
                    .into(binding.confirmImg)
            }
            binding.cropBtn.isEnabled = false
            binding.cropBtn.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.deep_grey
                )
            )
            val confirmSharedPref =
                requireContext().getSharedPreferences("ConfirmSharedPref", Context.MODE_PRIVATE)
            val confirmEditor = confirmSharedPref.edit()
            confirmEditor.apply {
                if (cropped != null) {
                    putString("selfie", cropped.toString())
                }
                findNavController().navigate(R.id.uploadFragment)
            }
        }
        binding.confirmBtn.setOnClickListener {
//            val cropped : Bitmap? = binding.cropView.getCroppedImage()

//                if(front != null){
//                    putString("front",front)
//                }
//                if(back != null){
//                    putString("back",back)
//                }
//                apply()
            //}
//            val bundle = Bundle()
//            bundle.putString("selfie",cropped.toString())
//            Handler(Looper.getMainLooper()).postDelayed({
//                findNavController().navigate(R.id.uploadFragment,bundle)
//            },100)
        }

        binding.closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
//        val sharedPref = requireContext().getSharedPreferences("Profile", Context.MODE_PRIVATE)
//        sharedPref.edit().clear().apply()
    }
}