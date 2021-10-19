package demo.app.paintball.map.sensors

import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.util.toast

class Locator(val listener: LocatorListener) : LocationListener {

    private var locationManager: LocationManager? = null

    init {
        locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager?
        try {
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, this)
        } catch (ex: SecurityException) {
            toast("Location access not enabled")
        }
    }

    override fun onLocationChanged(location: Location) {
        listener.onLocationChanged(location)
    }

    fun cancel() {
        locationManager?.removeUpdates(this)
    }

    interface LocatorListener {
        fun onLocationChanged(location: Location)
    }
}