package eu.bschmidt.devicepublisher.bak

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import eu.bschmidt.devicepublisher.model.celldata.CellDataViewModel
import eu.bschmidt.devicepublisher.R

class CellDataFragment : Fragment() {
    private val viewModel: CellDataViewModel by activityViewModels()

    private lateinit var cellIdTextView: TextView
    private lateinit var cellTypeTextView: TextView
    private lateinit var arfcnTextView: TextView
    private lateinit var bandTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_cell, container, false)

        cellIdTextView = view.findViewById(R.id.text_cell_id)
        cellTypeTextView = view.findViewById(R.id.text_cell_type)
        arfcnTextView = view.findViewById(R.id.text_arfcn)
        bandTextView = view.findViewById(R.id.text_band)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Observer data from ViewModel here
        // viewModel.selectedCell.observe(viewLifecycleOwner) { cell ->
        //     cellIdTextView.text = cell.id.toString()
        //     cellTypeTextView.text = cell.type.toString()
        //     arfcnTextView.text = cell.arfcn.toString()
        //     bandTextView.text = cell.band.toString()
        // }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CellDataFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
