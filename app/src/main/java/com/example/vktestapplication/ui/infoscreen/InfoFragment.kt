package com.example.vktestapplication.ui.infoscreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.vktestapplication.R
import com.example.vktestapplication.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    private val viewModel: InfoViewModel by viewModels()
    private lateinit var binding: FragmentInfoBinding

    private val args: InfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {
            binding.root.findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadInformation()
    }

    private fun loadInformation(){
        with(binding){
            Glide.with(root).load(args.imageUrl).placeholder(R.drawable._15612_document_file_gif_icon).into(infoImageView)
            gifTitle.text = if (!args.title.isNullOrEmpty()) args.title else getString(R.string.no_title)
            gifAuthor.text = if (!args.username.isNullOrEmpty()) args.username else getString(R.string.no_username)
            gifId.text = if (!args.id.isNullOrEmpty()) args.id else getString(com.example.vktestapplication.R.string.no_id)
            gifUrl.text = if (!args.utl.isNullOrEmpty()) args.utl else getString(R.string.no_url)
        }
    }

}