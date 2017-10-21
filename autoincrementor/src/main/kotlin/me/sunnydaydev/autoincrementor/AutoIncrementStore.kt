package me.sunnydaydev.autoincrementor

import org.gradle.api.Project
import java.io.*
import java.util.*

/**
 * Created by sunny on 21.10.2017.
 * mail: mail@sunnydaydev.me
 */
class AutoIncrementStore(private val project: Project) {

    var versionCode: Int
        get() = properties.versionCode ?: 1
        internal set(value) {
            properties.versionCode = value
            commit()
        }

    private val properties = getProps()

    private fun commit() {
        properties.store(getPropsFile().newWriter(), null)
    }

    private fun getProps(): Properties {
        return Properties().apply {
            load(FileInputStream(getPropsFile()))
        }
    }

    private fun getPropsFile(): File {
        return project.file("autoIncrementor.properties").apply {
            if (!exists()) {
                parentFile.mkdirs()
                createNewFile()
            }
        }
    }

    private fun File.newWriter(): Writer {
        return BufferedWriter(FileWriter(this))
    }

    private var Properties.versionCode: Int?
        get() = this["VERSION_CODE"]?.toString()?.toIntOrNull()
        set(value) { this["VERSION_CODE"] = value.toString() }

}