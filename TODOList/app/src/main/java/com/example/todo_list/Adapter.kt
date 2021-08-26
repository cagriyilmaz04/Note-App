package com.example.todo_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.databinding.RecyclerRowBinding

class Adapter():RecyclerView.Adapter<Adapter.TutucuVH>() {
    var emptyList= emptyList<Model>()
    class TutucuVH(val binding:RecyclerRowBinding):RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutucuVH {
     return TutucuVH(RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TutucuVH, position: Int) {
            holder.binding.textViewPosition.text=(position+1).toString()
            holder.binding.textViewBaslik.text=emptyList.get(position).baslik
        if(emptyList.get(position).flag==0){
            holder.binding.textOlusturulmaTarihi.text="Oluşturulma Tarihi : ${emptyList.get(position).olusturulma_tarihi}"
        }else if(emptyList.get(position).flag==1){
            holder.binding.textOlusturulmaTarihi.text="Son Düzenlenme Tarihi : ${emptyList.get(position).olusturulma_tarihi}"
        }

        holder.itemView.setOnClickListener {
            val action= MenuFragmentDirections.actionMenuFragmentToEditFragment(emptyList.get(position))
            Navigation.findNavController(it).navigate(action)
        }

    }
    override fun getItemCount(): Int {
       return emptyList.size
    }
    fun setData(note:List<Model>){
        this.emptyList=note
        notifyDataSetChanged()

    }

}