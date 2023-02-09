package com.example.challengeroom1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeroom1.room.Tbsiswa
import kotlinx.android.synthetic.main.activity_siswa_adapter.view.*


class SiswaAdapter(private val siswa: ArrayList<Tbsiswa>, private val listener:OnAdapterListener)
    : RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder>() {

    class SiswaViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaViewHolder {
        return SiswaViewHolder(
       LayoutInflater.from(parent.context).inflate(R.layout.activity_siswa_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: SiswaViewHolder, position: Int) {
       val Tbsis= siswa[position]
        holder.view.T_nama.text = Tbsis.nama
        holder.view.T_nama.setOnClickListener {
            listener.onClick(Tbsis)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(Tbsis)
        }

        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(Tbsis)
        }

    }
    override fun getItemCount()= siswa.size

    fun setData(list: List<Tbsiswa>){
        siswa.clear()
        siswa.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(tbsiswa: Tbsiswa)
        fun onUpdate(tbsiswa: Tbsiswa)
        fun onDelete(tbsiswa: Tbsiswa)


    }


}
