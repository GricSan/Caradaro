package com.gricsan.caradaro.base.utils

import android.content.Context
import android.location.Geocoder
import android.os.Build

fun getAddressName(
    context: Context,
    latitude: Double?,
    longitude: Double?,
    onAddressReceived: (addressLine: String?) -> Unit
) {
    if (latitude != null && longitude != null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Geocoder(context).getFromLocation(latitude, longitude, 1) {
                onAddressReceived(it.firstOrNull()?.getAddressLine(0))
            }
        } else {
            onAddressReceived(
                Geocoder(context).getFromLocation(latitude, longitude, 1)
                    ?.first()?.getAddressLine(0)
            )
        }
    }
}