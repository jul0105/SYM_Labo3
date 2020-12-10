/**
 * SYM : Labo 3 - Environnement I. Codes-barres, iBeacons et NFC
 * Auteurs : Julien Béguin, Robin Cuénoud & Gaëtan Daubresse
 * Date : 03.12.2020
 * Classe : B
 */

package ch.heigvd.iict.sym.labo3

import android.os.Bundle
import androidx.fragment.app.FragmentActivity


var authLevel = 5;

class NfcActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.root_container, NfcLogin()).commit()
        }
        setContentView(R.layout.activity_nfc)

    }

    // swap to the data fragment
    fun loggedIn() {
        supportFragmentManager.findFragmentById(R.id.root_container)?.let {
            supportFragmentManager.beginTransaction().remove(
                it
            ).add(R.id.root_container,DataFragment()).commit()
        };

    }

    fun  getAuthLevel(): Int {
        return authLevel;
    }

}