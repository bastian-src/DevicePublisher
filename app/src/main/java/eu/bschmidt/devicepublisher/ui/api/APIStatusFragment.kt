package eu.bschmidt.devicepublisher.ui.api

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import eu.bschmidt.devicepublisher.R
import eu.bschmidt.devicepublisher.model.api.APIStatusViewModel

class APIStatusFragment : Fragment() {

    companion object {
        fun newInstance() = APIStatusFragment()
        private const val TAG = "APIStatusFragment"
    }

    private val viewModel: APIStatusViewModel = APIStatusViewModel.getInstance()
    private lateinit var context: Context
    private lateinit var runningCard: CardView
    private lateinit var runningTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var requestsTextView: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_api_status, container, false)
        runningCard = view.findViewById(R.id.constraintFirst)
        runningTextView = view.findViewById(R.id.running)
        addressTextView = view.findViewById(R.id.address)
        requestsTextView = view.findViewById(R.id.requests)
        return view
    }

    private fun setRunningTextView(runStatus: Boolean) {
        if (runStatus) {
            runningCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.on_green))
            runningTextView.setTextAppearance(R.style.on)
            runningTextView.text = "🐢💨"
        } else {
            runningCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.off_red))
            runningTextView.setTextAppearance(R.style.off)
            runningTextView.text = "🐢"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apiStatus.observe(viewLifecycleOwner) { status ->
            Log.d(TAG, "changed APIStatusViewmodel: $status")
            setRunningTextView(status.running)
            addressTextView.text = status.address
            requestsTextView.text = "${status.requests} 📬 "
        }
    }
}
