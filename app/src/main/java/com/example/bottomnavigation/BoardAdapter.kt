package com.example.bottomnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation.databinding.BoardItemBinding

class BoardAdapter(var findNavController: NavController, private var data: ArrayList<Board> ): RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: BoardItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){
            binding.imageView.setImageResource(data[position].image)
            binding.textTitle.text = data[position].title
            binding.textDescription.text = data[position].description

            binding.buttonStart.setOnClickListener{
                findNavController.navigateUp()
            }

            binding.buttonSkip.setOnClickListener{
                findNavController.navigateUp()
            }
            if (position == data.lastIndex){
                binding.buttonStart.visibility = View.VISIBLE

            } else{
                binding.buttonStart.visibility = View.INVISIBLE
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(BoardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size
}