package com.miftah.sifutsal.Response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ResponseFutsal(

    @field:SerializedName("data_warning")
    val dataWarning: String? = null,

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("message")
    val message: String? = null
)

@Parcelize
data class DataItem(

    @field:SerializedName("lapangan")
    val lapangan: List<LapanganItem?>? = null,

    @field:SerializedName("jam_tutup")
    val jamTutup: String? = null,

    @field:SerializedName("telepon")
    val telepon: String? = null,

    @field:SerializedName("admin")
    val admin: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("fasilitas")
    val fasilitas: String? = null,

    @field:SerializedName("id_user")
    val idUser: String? = null,

    @field:SerializedName("long")
    val jsonMemberLong: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null,

    @field:SerializedName("jam_buka")
    val jamBuka: String? = null,

    @field:SerializedName("id_futsal")
    val idFutsal: Int? = null,

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("foto")
    val foto: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("lat")
    val lat: String? = null
) : Parcelable


@Parcelize
data class LapanganItem(

    @field:SerializedName("id_futsal")
    val idFutsal: String? = null,

    @field:SerializedName("jenis_lantai")
    val jenisLantai: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("id_lapangan")
    val idLapangan: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("foto1")
    val foto1: String? = null,

    @field:SerializedName("harga_malam")
    val hargaMalam: String? = null,

    @field:SerializedName("nama_lapangan")
    val namaLapangan: String? = null,

    @field:SerializedName("harga_pagi")
    val hargaPagi: String? = null,

    @field:SerializedName("foto3")
    val foto3: String? = null,

    @field:SerializedName("foto2")
    val foto2: String? = null
) : Parcelable