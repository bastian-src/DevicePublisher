package eu.bschmidt.devicepublisher.ui.celldata

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import eu.bschmidt.devicepublisher.model.celldata.CellData

import eu.bschmidt.devicepublisher.databinding.FragmentCellDataListBinding

class CellDataRecyclerViewAdapter(
    private val values: List<CellData>
) : RecyclerView.Adapter<CellDataRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentCellDataListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val mostLikelyId = item.cid ?: item.pci ?: item.nodeB
        holder.cellIdTextView.text = mostLikelyId.toString()
        holder.cellTypeTextView.text = item.type.toString()
        holder.arfcnTextView.text = item.arfcn.toString()
        holder.bandTextView.text = item.band
        holder.rssiTextView.text = item.rssi.toString()
        holder.rsrpTextView.text = item.rsrq.toString()
        holder.rsrqTextView.text = item.rsrp.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCellDataListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var cellIdTextView = binding.textCellId
        var cellTypeTextView = binding.textCellType
        var arfcnTextView = binding.textArfcn
        var bandTextView = binding.textBand
        var rssiTextView = binding.textRssi
        var rsrpTextView = binding.textRsrp
        var rsrqTextView = binding.textRsrq
    }

}
