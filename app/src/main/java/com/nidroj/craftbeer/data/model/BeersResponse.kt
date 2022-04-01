package com.nidroj.craftbeer.data.model

class BeersResponse {
    var id: Int = 0
    var name: String? = null
    var tagline: String? = null
    var first_brewed: String? = null
    var description: String? = null
    var image_url: String? = null
    var abv: Double = 0.0
    var ibu: Int = 0
    var target_fg: Int = 0
    var target_og: Int = 0
    var ebc: Int = 0
    var srm: Double = 0.0
    var ph: Double = 0.0
    var attenuation_level: Double = 0.0
    var volume: Volume? = null
    var boil_volume: BoilVolume? = null
    var method: Method? = null
    var ingredients: Ingredients? = null
    var food_pairing: ArrayList<String>? = null
    var brewers_tips: String? = null
    var contributed_by: String? = null

    inner class Ingredients {
        var malt: ArrayList<Malt>? = null
        var hops: ArrayList<Hop>? = null
        var yeast: String? = null

        inner class Amount {
            var value: Double = 0.0
            var unit: String? = null
        }

        inner class Malt {
            var name: String? = null
            var amount: Amount? = null
        }

        inner class Hop {
            var name: String? = null
            var amount: Amount? = null
            var add: String? = null
            var attribute: String? = null
        }
    }

    inner class Volume {
        var value: Int = 0
        var unit: String? = null
    }

    inner class BoilVolume {
        var value: Int = 0
        var unit: String? = null
    }

    inner class Method {
        var mash_temp: ArrayList<MashTemp>? = null
        var fermentation: Fermentation? = null
        var twist: String? = null
        inner class Temp {
            var value: Int = 0
            var unit: String? = null
        }

        inner class Fermentation {
            var temp: Temp? = null
        }
        inner class MashTemp {
            var temp: Temp? = null
            var duration: Int = 0
        }

    }
}
