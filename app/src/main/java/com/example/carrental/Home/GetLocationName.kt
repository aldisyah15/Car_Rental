package com.example.carrental.Home

import android.Manifest
import android.content.Context
import android.location.Geocoder
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import java.util.Locale

@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
fun getUserLocationName(context: Context, onLocationFetched: (String) -> Unit) {
val fusedLocation = LocationServices.getFusedLocationProviderClient(context)

    fusedLocation.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
        .addOnSuccessListener { location ->
            if (location != null) {
                val geocoder = Geocoder(context, Locale.getDefault())

                val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                if (!addresses.isNullOrEmpty()) {
                    val address = addresses[0]
                    val kecamatan = address.subAdminArea
                    onLocationFetched("$kecamatan")
                }
            } else {
                onLocationFetched("GPS Tidak Aktif")
            }
        }
        .addOnFailureListener {
            onLocationFetched("Izinkan lokasi")
        }
}
