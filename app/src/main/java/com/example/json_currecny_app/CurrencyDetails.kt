package com.example.json_currecny_app

import com.google.gson.annotations.SerializedName

class CurrencyDetails {

    @SerializedName("date")
    var date: String? = null

    @SerializedName("eur")
    var eur: Currency? = null

    class Currency {
        @SerializedName("ada")
        var inr: String? = null

        @SerializedName("usd")
        var usd: String? = null

        @SerializedName("aud")
        var aud: String? = null

        @SerializedName("sar")
        var sar: String? = null

        @SerializedName("krw")
        var cny: String? = null

        @SerializedName("jpy")
        var jpy: String? = null
    }

}