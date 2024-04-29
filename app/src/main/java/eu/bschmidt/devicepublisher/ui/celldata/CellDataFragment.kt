package eu.bschmidt.devicepublisher.ui.celldata

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import eu.bschmidt.devicepublisher.model.celldata.CellData
import eu.bschmidt.devicepublisher.model.celldata.CellDataViewModel
import eu.bschmidt.devicepublisher.R

/**
 * A fragment representing a list of Items.
 */
class CellDataFragment : Fragment() {

    private val viewModel: CellDataViewModel = CellDataViewModel.getInstance()

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_cell_data_list_list, container, false)
        val viewConnected = layout.findViewById<RecyclerView>(R.id.listConnected)
        val viewAvailable = layout.findViewById<RecyclerView>(R.id.listAvailable)
        val connectedCellDataList: MutableList<CellData> = mutableListOf()
        val availableCellDataList: MutableList<CellData> = mutableListOf()
        val connectedListAdapter = CellDataRecyclerViewAdapter(connectedCellDataList)
        val availableListAdapter = CellDataRecyclerViewAdapter(availableCellDataList)

        viewModel.connectedCellDataList.observe(viewLifecycleOwner) { modelList ->
            connectedCellDataList.clear()
            connectedCellDataList.addAll(modelList)
            connectedListAdapter.notifyDataSetChanged()
        }
        viewModel.availableCellDataList.observe(viewLifecycleOwner) { modelList ->
            availableCellDataList.clear()
            availableCellDataList.addAll(modelList)
            availableListAdapter.notifyDataSetChanged()
        }

        // Set the adapter
        if (viewConnected is RecyclerView) {
            with(viewConnected) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = connectedListAdapter
            }
        }
        if (viewAvailable is RecyclerView) {
            with(viewAvailable) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = availableListAdapter 
            }
        }
        return layout
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            CellDataFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
