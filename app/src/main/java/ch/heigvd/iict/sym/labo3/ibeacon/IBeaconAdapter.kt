/**
 * SYM : Labo 3 - Environnement I. Codes-barres, iBeacons et NFC
 * Auteurs : Julien Béguin, Robin Cuénoud & Gaëtan Daubresse
 * Date : 03.12.2020
 * Classe : B
 */

package ch.heigvd.iict.sym.labo3.ibeacon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.heigvd.iict.sym.labo3.R
import org.altbeacon.beacon.Beacon

class IBeaconAdapter : RecyclerView.Adapter<IBeaconAdapter.ViewHolder>() {

    /**
     * View's item
     */
    private var beacons: List<Beacon> = ArrayList()

    /**
     * Provide a direct reference to each of the views within a data item
     * Used to cache the views within the item layout for fast access
     */
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        // Any view that will be set while rendering row
        val uuid: TextView = itemView.findViewById(R.id.uuid_content)
        val major: TextView = itemView.findViewById(R.id.major_content)
        val minor: TextView = itemView.findViewById(R.id.minor_content)
        val rssi: TextView = itemView.findViewById(R.id.rssi_content)
    }

    /**
     * Inflating a layout from XML and returning the holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IBeaconAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_ibeacon, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    /**
     * Populating data into the item through holder
     */
    override fun onBindViewHolder(viewHolder: IBeaconAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val beacon: Beacon = beacons[position]
        // Set item views based on views and data model
        viewHolder.uuid.text = beacon.id1.toString()
        viewHolder.major.text = beacon.id2.toString()
        viewHolder.minor.text = beacon.id3.toString()
        viewHolder.rssi.text = beacon.rssi.toString()
    }

    /**
     * Returns the total count of items in the list
     */
    override fun getItemCount(): Int {
        return beacons.size
    }

    /**
     * Set items
     */
    fun setBeacons(value: Collection<Beacon>) {
        beacons = value.toList()
    }
}
