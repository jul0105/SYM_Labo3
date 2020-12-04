package ch.heigvd.iict.sym.labo3.ibeacon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.heigvd.iict.sym.labo3.R

class IBeaconAdapter : RecyclerView.Adapter<IBeaconAdapter.ViewHolder>() {

    private val iBeacons: List<IBeacon> = ArrayList()

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val uuid: TextView = itemView.findViewById(R.id.uuid_content)
        val major: TextView = itemView.findViewById(R.id.major_content)
        val minor: TextView = itemView.findViewById(R.id.minor_content)
        val rssi: TextView = itemView.findViewById(R.id.rssi_content)
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IBeaconAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_ibeacon, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: IBeaconAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val iBeacon: IBeacon = iBeacons[position]
        // Set item views based on your views and data model
        viewHolder.uuid.text = iBeacon.uuid
        viewHolder.major.text = iBeacon.major
        viewHolder.minor.text = iBeacon.minor
        viewHolder.rssi.text = iBeacon.rssi
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return iBeacons.size
    }
}
