package com.example.bottomnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.bottomnavigation.databinding.FragmentBoardBinding
import androidx.navigation.fragment.findNavController
import me.relex.circleindicator.CircleIndicator3


class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    private var data = arrayListOf<Board>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBoardBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData(data)

        var adapter = BoardAdapter(findNavController(), data)
        binding.viewPager.adapter = adapter

        var indicatorCircle: CircleIndicator3 = binding.circleIndicator
        indicatorCircle.setViewPager(binding.viewPager)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }

    private fun loadData(data: ArrayList<Board>) {
        data.add(Board(R.drawable.earth, "Welcome", "Digitally Yours "))
        data.add(Board(R.drawable.men, "Welcome", "Digitally Yours "))
        data.add(Board(R.drawable.tourist, "Welcome", "Digitally Yours "))
    }
}