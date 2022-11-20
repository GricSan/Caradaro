package com.gricsan.caradaro.base.utils

import android.content.Context
import android.location.Geocoder
import android.os.Build
import com.google.android.gms.maps.model.LatLng

fun LatLng.getAddressLine(
    context: Context,
    onAddressReceived: (addressLine: String) -> Unit
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Geocoder(context).getFromLocation(latitude, longitude, 1) {
            onAddressReceived(it.first().getAddressLine(0))
        }
    } else {
        Geocoder(context).getFromLocation(latitude, longitude, 1)?.let {
            onAddressReceived(it.first().getAddressLine(0))
        }
    }
}