package dev.gamd.dimondgame.ui.recyclers_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.gamd.dimondgame.R
import dev.gamd.dimondgame.databinding.TileItemBinding
import dev.gamd.dimondgame.models.Tile

class TileRV(
    private val context: Context,
    private  val onClickTile: OnClickTile) : RecyclerView.Adapter<TileRV.RViewTileHolder>()
{
    private lateinit var imageList : List<Tile>

    class RViewTileHolder(
        private val context: Context,
        item: View,
        private val onClickTile: OnClickTile) : RecyclerView.ViewHolder(item)
    {
        private val binding = TileItemBinding.bind(item)
        fun bind(t : Tile){
            binding.apply {
                if(t.isPairFound){
                    binding.imageView2.setImageDrawable(ContextCompat.getDrawable(context, t.image))
                }
                else{
                    if (t.isSelected){
                        binding.imageView2.setImageDrawable(ContextCompat.getDrawable(context, t.image))
                    }
                    else{
                        binding.imageView2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.placeholder))
                    }
                }
                binding.cardView.setOnClickListener {
                    binding.imageView2.setImageDrawable(ContextCompat.getDrawable(context, t.image))
                    onClickTile.onClickTile(t)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TileRV.RViewTileHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tile_item, parent, false)
        return RViewTileHolder(context,view, onClickTile)
    }

    override fun onBindViewHolder(holder: TileRV.RViewTileHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun setTils(images: List<Tile>) {
        imageList = images
        notifyDataSetChanged()
    }

    interface OnClickTile{
        fun onClickTile(t : Tile)
    }

}