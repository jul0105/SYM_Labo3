/**
 * SYM : Labo 3 - Environnement I. Codes-barres, iBeacons et NFC
 * Auteurs : Julien Béguin, Robin Cuénoud & Gaëtan Daubresse
 * Date : 03.12.2020
 * Classe : B
 */

package ch.heigvd.iict.sym.labo3

import android.Manifest
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.heigvd.iict.sym.labo3.ibeacon.IBeaconAdapter
import org.altbeacon.beacon.BeaconConsumer
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.Region


private const val BEACON_FORMAT: String = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"
private const val SCAN_INTERVAL: Long = 1000
// Log's tag
private val TAG: String = IBeaconActivity::class.simpleName.toString()

class IBeaconActivity : AppCompatActivity(), BeaconConsumer {

    private lateinit var beaconManager: BeaconManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_i_beacon)

        // Check permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

        // Initialize BeaconManager
        beaconManager = BeaconManager.getInstanceForApplication(this)
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout(BEACON_FORMAT))

        // Set scan interval
        beaconManager.foregroundBetweenScanPeriod = SCAN_INTERVAL
        beaconManager.updateScanPeriods()

        // Lookup the recyclerview in activity layout
        val recyclerView: RecyclerView = findViewById(R.id.ibeacon_recycler_view)
        // Create adapter passing in the sample user data
        val adapter = IBeaconAdapter()
        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)
        // That's all!
    }

    override fun onResume() {
        super.onResume()
        beaconManager.bind(this)
    }

    override fun onPause() {
        super.onPause()
        beaconManager.unbind(this)
    }

    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)

        // Stop scanning
        try {
            beaconManager.stopRangingBeaconsInRegion(Region("myRangingUniqueId", null, null, null))
        } catch (e: RemoteException) {
            Log.e(TAG, e.stackTrace.toString())
        }
    }

    override fun onBeaconServiceConnect() {
        beaconManager.removeAllRangeNotifiers()
        beaconManager.addRangeNotifier { beacons, region ->
            if (beacons.isNotEmpty()) {

                for (beacon in beacons) {
                    Log.d(TAG, "UUID: " + beacon.id1
                            + ", Major: " + beacon.id2
                            + ", Minor: " + beacon.id3
                            + ", RSSI: " + beacon.rssi
                            + ", Dist: " + beacon.distance
                    )
                }

            }
        }

        try {
            beaconManager.startRangingBeaconsInRegion(Region("myRangingUniqueId", null, null, null))
        } catch (e: RemoteException) {
            Log.e(TAG, e.stackTrace.toString())
        }
    }
}