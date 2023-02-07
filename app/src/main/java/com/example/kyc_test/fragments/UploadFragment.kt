package com.example.kyc_test.fragments
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.kyc_test.PhotoChosenListener
import com.example.kyc_test.R
import com.example.kyc_test.databinding.FragmentUploadBinding

class UploadFragment : Fragment(), PhotoChosenListener {
    private var selfiePhotoLD = MutableLiveData("")
    private var nrcFrontLD = MutableLiveData("")
    private var nrcBackLD = MutableLiveData("")
    private lateinit var binding: FragmentUploadBinding
    private lateinit var dialogBox: DialogBoxFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ResourceType", "CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selfieCard.setBackgroundResource(R.drawable.card_bg)
        binding.frontCard.setBackgroundResource(R.drawable.card_bg)
        binding.backCard.setBackgroundResource(R.drawable.card_bg)
        binding.dropDownCard.setBackgroundResource(R.drawable.card_bg)
        binding.documentFront.setBackgroundResource(R.drawable.card_bg)
        binding.documentBack.setBackgroundResource(R.drawable.card_bg)

        binding.selfieCard.setOnClickListener {
            openDialog("selfie")
        }

        binding.frontCard.setOnClickListener {
            openDialog("nrcFront")
        }

        binding.backCard.setOnClickListener {
            openDialog("nrcBack")
        }

        binding.selfieClose.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.selfiePhotoLayout.isVisible = false
                binding.selfieCard.isVisible = true
                binding.selfieClose.isVisible = false
                Toast.makeText(requireContext(), "removed Selfie Photo", Toast.LENGTH_SHORT).show()
                binding.view1.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.grey))
            }, 200)
        }

        binding.frontClose.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.frontPhotoLayout.isVisible = false
                binding.frontCard.isVisible = true
                binding.frontClose.isVisible = false
                Toast.makeText(requireContext(), "removed NRC Front", Toast.LENGTH_SHORT).show()
                binding.view2.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.grey))
            }, 200)
        }

        binding.backClose.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.backPhotoLayout.isVisible = false
                binding.backCard.isVisible = true
                binding.backClose.isVisible = false
                Toast.makeText(requireContext(), "removed NRC Back", Toast.LENGTH_SHORT).show()
                binding.view3.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.grey))
            }, 200)
        }

        selfiePhotoLD.observe(viewLifecycleOwner) { selfie ->
            if (selfie.isNotEmpty()) {
                binding.apply {
                    selfieCard.isVisible = false
                    selfiePhotoLayout.isVisible = true
                    selfieClose.isVisible = true
                    selfieImg.setImageURI(Uri.parse(selfie))
                }
                binding.view1.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.red))
            }
        }

        nrcFrontLD.observe(viewLifecycleOwner) { front ->
            if (front.isNotEmpty()) {
                binding.apply {
                    frontCard.isVisible = false
                    frontPhotoLayout.isVisible = true
                    frontClose.isVisible = true
                    frontImg.setImageURI(Uri.parse(front))
                }
                binding.view2.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.red))
            }

        }

        nrcBackLD.observe(viewLifecycleOwner) { back ->
            if (back.isNotEmpty()) {
                binding.apply {
                    backCard.isVisible = false
                    backPhotoLayout.isVisible = true
                    backClose.isVisible = true
                    backImg.setImageURI(Uri.parse(back))
                }
                binding.view3.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.red))
            }
        }

    }

    private fun openDialog(mType: String) {
        dialogBox = DialogBoxFragment.newInstance(mType, this)
        dialogBox.show(parentFragmentManager, "Dialog")
    }

    override fun getPhotoFromGallery(type: String, data: String) {
        when (type) {
            "selfie" -> selfiePhotoLD.value = data
            "nrcFront" -> nrcFrontLD.value = data
            "nrcBack" -> nrcBackLD.value = data
        }
    }

    override fun getPhotoFromCamera(type: String, data: String) {
        when (type) {
            "selfie" -> selfiePhotoLD.value = data
            "nrcFront" -> nrcFrontLD.value = data
            "nrcBack" -> nrcBackLD.value = data
        }

    }

}