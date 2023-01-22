package com.example.tugas_mobile.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas_mobile.data.model.TvShowResponse
import com.example.tugas_mobile.databinding.ItemMovieOrTvshowBinding
import com.example.tugas_mobile.utils.BASE_URL_API_IMAGE
import com.example.tugas_mobile.utils.DivTvShowCallback
import com.example.tugas_mobile.utils.POSTER_SIZE_W185
import com.example.tugas_mobile.utils.loadImageTvShow

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private var listener: ((TvShowResponse) -> Unit)? = null

    var tvshow = mutableListOf<TvShowResponse>()
    set(value) {
        val callback = DivTvShowCallback(field, value)
        val result = DiffUtil.calculateDiff(callback)
        field.clear()
        field.addAll(value)
        result.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ItemMovieOrTvshowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowResponse){
            binding.apply {
                tvShow.poster?.let {
                    poster.loadImageTvShow("$BASE_URL_API_IMAGE$POSTER_SIZE_W185$it")
                }
                tvTitle.text = tvShow.name
                tvDesc.text = if (tvShow.desc?.isNotBlank() as Boolean) tvShow.desc else "No Description"

                listener?.let { itemView.setOnClickListener { it(tvShow) } }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieOrTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvshow[position])
    }

    override fun getItemCount(): Int = tvshow.size

    fun onClick(listener: ((TvShowResponse) -> Unit)?){
        this.listener = listener
    }
}