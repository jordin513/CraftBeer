package com.nidroj.craftbeer.data.model

import androidx.room.Entity

/**
 * Beer object returned from PunkAPI
 */
@Entity(tableName = "beer")
data class Beer (
    var id: Int = 0,
    var name: String? = null,
    var tagline: String? = null,
    var first_brewed: String? = null,
    var description: String? = null,
    var image_url: String? = null,
    var abv: Double = 0.0,
    var ibu: Double = 0.0,
    var target_fg: Double = 0.0,
    var target_og: Double = 0.0,
    var ebc: Double = 0.0,
    var srm: Double = 0.0,
    var ph: Double = 0.0,
    var attenuation_level: Double = 0.0,
    var volume: Volume? = null,
    var boil_volume: Volume? = null,
    var method: Method? = null,
    var ingredients: Ingredients? = null,
    var food_pairing: ArrayList<String>? = null,
    var brewers_tips: String? = null,
    var contributed_by: String? = null

)
