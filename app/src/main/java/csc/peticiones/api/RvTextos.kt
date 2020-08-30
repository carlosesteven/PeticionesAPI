package csc.peticiones.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvTextos (
    private var lista: List<Actividad>,
    private var listener: INTERFACE_click
) : RecyclerView.Adapter<RvTextos.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val texto: TextView = itemView.findViewById(R.id.tTextoGenerico)
        val cupo: TextView = itemView.findViewById(R.id.tTextoCupo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_textos, viewGroup, false)
        val pvh = ViewHolder(v)
        v.setOnClickListener { v1 -> listener.onItemClick(v1, pvh.layoutPosition) }
        return pvh
    }

    override fun onBindViewHolder(view: ViewHolder, i: Int) {
        view.texto.text = lista[i].direccion
        view.cupo.text = lista[i].cupo
    }

    override fun getItemCount(): Int {
        return lista.size
    }

}