package ch.heigvd.iict.sym.labo3

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.data_fragment.*

// authenticates constants
const val AUTHENTICATE_LOW = 1;
const val AUTHENTICATE_MEDIUM = 5;
const val AUTHENTICATE_MAX = 10;

// this class is responsible to show data if access are suffisant. NFC is done on NfcActivity
class DataFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.data_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        minSecurityButton.setOnClickListener {
           if((activity as NfcActivity).getAuthLevel() >= AUTHENTICATE_LOW) {
               Toast.makeText(context,"low auth ok",Toast.LENGTH_SHORT).show()
           } else {
               Toast.makeText(context,"low auth NOT ok",Toast.LENGTH_SHORT).show()
           }
        }
        mediumSecurityButton.setOnClickListener {
            if((activity as NfcActivity).getAuthLevel() >= AUTHENTICATE_MEDIUM) {
                Toast.makeText(context,"medium auth ok",Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context,"medium auth NOT ok",Toast.LENGTH_SHORT).show()

            }
        }
        maxSecurityButton.setOnClickListener {
            if((activity as NfcActivity).getAuthLevel() >= AUTHENTICATE_MAX) {
                Toast.makeText(context,"max auth ok",Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context,"max auth NOT ok",Toast.LENGTH_SHORT).show()

            }
        }
    }

}