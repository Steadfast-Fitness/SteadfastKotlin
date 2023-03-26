package com.example.steadfast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.steadfast.Supplement



class SupplementAdapter(private val supplements: List<Supplement>) : RecyclerView.Adapter<SupplementAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val dosageTextView: TextView = view.findViewById(R.id.dosageTextView)
        val amountTextView: TextView = view.findViewById(R.id.amountTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.supplementlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val supplement = supplements[position]

        holder.nameTextView.text = supplement.supplement
        holder.dosageTextView.text = supplement.dosage
        holder.amountTextView.text = supplement.amount
    }

    override fun getItemCount(): Int {
        return supplements.size
    }
}
