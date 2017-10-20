package me.sunnydaydev.autoincrementor

/**
 * Created by sunny on 04.10.2017.
 * mail: mail@sunnydaydev.me
 */

class Increment constructor(val name: String){

    var onVariants: Array<String> = arrayOf()
        private set

    var onBuildTypes: Array<String> = arrayOf()
        private set

    var onFlavors: Array<String> = arrayOf()
        private set

    var buildIncrement = 1
        private set

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