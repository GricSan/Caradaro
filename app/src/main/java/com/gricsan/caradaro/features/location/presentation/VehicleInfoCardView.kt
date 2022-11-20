package com.gricsan.caradaro.features.location.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gricsan.caradaro.R
import com.gricsan.caradaro.base.domain.models.Vehicle
import com.gricsan.caradaro.base.utils.dp
import com.gricsan.caradaro.base.utils.loadImageFromUrl
import com.gricsan.caradaro.databinding.ViewVehicleInfoCardBinding

class VehicleInfoCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val imageTransformationOptions = RequestOptions().transform(
        CenterCrop(),
        RoundedCorners(6.dp)
    )

    private val _binding = ViewVehicleInfoCardBinding.inflate(
        LayoutInflater.from(context), this, true
    )


    init {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.VehicleInfoCardView,
            defStyleAttr,
            0
        ).let { it.recycle() }
    }


    fun setVehicleData(vehicle: Vehicle, addressName: String) {
        _binding.apply {
            val vehicleName = "${vehicle.manufacturerName} ${vehicle.modelName}"
            tvVehicleName.text = vehicleName
            tvVehicleAddress.text = addressName
            tvVehicleColor.text = vehicle.colorHexValue
            ivVehiclePhoto.loadImageFromUrl(
                url = vehicle.photoUrl,
                thumbnailResId = R.drawable.img_car_photo_placeholder,
                options = imageTransformationOptions
            )
        }
    }

}