package com.example.bottomnavigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bottomnavigation.databinding.FragmentProfileBinding
import com.example.bottomnavigation.ui.Prefs


class ProfileFragment : Fragment() {
    private lateinit var prefs: Prefs
    private lateinit var binding: FragmentProfileBinding
    var launchForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode === AppCompatActivity.RESULT_OK) {
            val image = result.data?.data
            prefs.saveAvatar(image.toString())
            binding.ivImage.GlideYu(image.toString())
            binding.ivImage.setImageURI(image)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    fun ImageView.GlideYu(uri: String){
        Glide.with(this).load(uri).circleCrop().into(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = Prefs(requireContext())
        binding.ivImage.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            launchForResult.launch(intent)
        }
        binding.editText.setText(prefs.isEdit());
        if (prefs.getAvatar() != null){
            binding.ivImage.GlideYu(prefs.getAvatar()!!)
            prefs.saveEdit(binding.editText.text.toString());
        }


    }
}