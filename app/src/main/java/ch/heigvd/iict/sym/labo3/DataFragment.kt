package ch.heigvd.iict.sym.labo3

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.data_fragment.*

class DataFragment : Fragment() {
    var AUTHENTICATE_LOW = 1;
    var AUTHENTICATE_MEDIUM = 5;
    var AUTHENTICATE_MAX = 10;
    companion object {
        fun newInstance() = DataFragment()
    }

    private lateinit var viewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.data_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        minSecurityButton.setOnClickListener {
           if((activity as NfcActivity).getAuthLevel() >= AUTHENTICATE_LOW) {
               Toast.makeText(context,"low auth ok",Toast.LENGTH_SHORT).show()
           }
        }
        mediumSecurityButton.setOnClickListener {
            if((activity as NfcActivity).getAuthLevel() >= AUTHENTICATE_MEDIUM) {
                Toast.makeText(context,"medium auth ok",Toast.LENGTH_SHORT).show()

            }
        }
        maxSecurityButton.setOnClickListener {
            if((activity as NfcActivity).getAuthLevel() >= AUTHENTICATE_MAX) {
                Toast.makeText(context,"max auth ok",Toast.LENGTH_SHORT).show()

            }
        }
    }

}