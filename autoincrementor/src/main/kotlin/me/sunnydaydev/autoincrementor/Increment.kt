package me.sunnydaydev.autoincrementor

/**
 * Created by sunny on 04.10.2017.
 * mail: mail@sunnydaydev.me
 */

class Increment constructor(val name: String){

    var variants: Array<String> = arrayOf()
        private set

    var versionCodeIncrement = 1
        private set

    var versionNameIncrement = "0.0.0"
        private set

    fun versionCodeIncrement(versionCodeIncrement: Int) {
        this.versionCodeIncrement = versionCodeIncrement
    }

    fun versionNameIncrement(versionNameIncrement: String) {
        this.versionNameIncrement = versionNameIncrement
    }

    fun onVariants(variants: Array<String>) {
        this.variants = variants
    }

}