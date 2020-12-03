/**
 * SYM : Labo 3 - Environnement I. Codes-barres, iBeacons et NFC
 * Auteurs : Julien Béguin, Robin Cuénoud & Gaëtan Daubresse
 * Date : 03.12.2020
 * Classe : B
 */

package ch.heigvd.iict.sym.labo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_ibeacon.setOnClickListener {
            val intent = Intent(this@MainActivity, IBeaconActivity::class.java)
            startActivity(intent)
        }

        btn_nfc.setOnClickListener {
            val intent = Intent(this@MainActivity, NfcActivity::class.java)
            startActivity(intent)
        }

        btn_barcode.setOnClickListener {
            val intent = Intent(this@MainActivity, BarCodeActivity::class.java)
            startActivity(intent)
        }
    }
}