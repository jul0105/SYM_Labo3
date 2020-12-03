package ch.heigvd.iict.sym.labo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIBeacon.setOnClickListener {
            val intent = Intent(this@MainActivity, IBeaconActivity::class.java)
            startActivity(intent)
        }

        btnNfc.setOnClickListener {
            val intent = Intent(this@MainActivity, NfcActivity::class.java)
            startActivity(intent)
        }

        btnBarCode.setOnClickListener {
            val intent = Intent(this@MainActivity, BarCodeActivity::class.java)
            startActivity(intent)
        }
    }
}