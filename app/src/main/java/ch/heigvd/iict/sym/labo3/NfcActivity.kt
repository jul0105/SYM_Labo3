/**
 * SYM : Labo 3 - Environnement I. Codes-barres, iBeacons et NFC
 * Auteurs : Julien Béguin, Robin Cuénoud & Gaëtan Daubresse
 * Date : 03.12.2020
 * Classe : B
 */

package ch.heigvd.iict.sym.labo3

import android.R.attr.mimeType
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import java.nio.charset.Charset


var authLevel = 0;
const val TAG = "NfcDemo"
const val MIME_TEXT_PLAIN = "text/plain";
var loggedIn: Boolean = false;

private var pendingIntent: PendingIntent? = null

class NfcActivity : FragmentActivity() {

    private var mNfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if (!mNfcAdapter!!.isEnabled) {
            Toast.makeText(this, "NFC is disabled", Toast.LENGTH_LONG).show();

        }

        if (intent != null) {
            processIntent(intent)
        }





        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.root_container, NfcLogin()).commit()
        }
        setContentView(R.layout.activity_nfc)

    }

    override fun onResume() {
        super.onResume()
        if(loggedIn)
            loggedIn();

       // mNfcAdapter?.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray)
    }



    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        processIntent(intent);


    }
    fun processIntent(intent: Intent) {
       if(intent.action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
           val rawMessage = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
           if (rawMessage != null) {
               if(deserializeData(rawMessage).contains("test")) {
                   authLevel = 15;
                   val timer = object: CountDownTimer(20000, 2000) {
                       override fun onTick(millisUntilFinished: Long) {
                           authLevel -= 1;
                       }

                       override fun onFinish() {
                           authLevel = 0
                       }
                   }
                   timer.start()
               }
           }

       }
    }
        // swap to the data fragment
    fun loggedIn() {

                loggedIn = true;
                supportFragmentManager.findFragmentById(R.id.root_container)?.let {
                    supportFragmentManager.beginTransaction().remove(
                        it
                    ).add(R.id.root_container, DataFragment()).commit()
                };


    }
    fun deserializeData(rawMessage: Array<Parcelable>): String {

        val ndefMessage = rawMessage[0] as NdefMessage;
        val ndefRecord = ndefMessage.records[0];
        val out = String(ndefRecord.payload);

        return out;
    }
    fun  getAuthLevel(): Int {
        return authLevel;
    }



}


