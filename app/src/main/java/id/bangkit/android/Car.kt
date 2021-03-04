package id.bangkit.android

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car (
    var carName: String ? = null,
    var carPrice: String ? = null,
    var carModel: String ? = null,
    var carImage: Int = 0,
    var carTransmision: String ? = null,
    var carBodyType: String ? = null,
    var carDescription: String ? = null

) : Parcelable