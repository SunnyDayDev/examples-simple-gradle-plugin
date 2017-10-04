package me.sunnydaydev.autoincrementor

/**
 * Created by sunny on 04.10.2017.
 * mail: mail@sunnydaydev.me
 */

class Increment constructor(val name: String){

    var onVariants: Array<String> = arrayOf()

    var onBuildTypes: Array<String> = arrayOf()

    var onFlavors: Array<String> = arrayOf()

    var buildIncrement = 1

    fun buildIncrement(buildIncrement: Int) {
        this.buildIncrement = buildIncrement
    }

    fun onBuildTypes(buildTypes: Array<String>) {
        onBuildTypes = buildTypes
    }

    fun onVariants(variants: Array<String>) {
        onVariants = variants
    }

    fun onFlavors(flavors: Array<String>) {
        onFlavors = flavors
    }

}