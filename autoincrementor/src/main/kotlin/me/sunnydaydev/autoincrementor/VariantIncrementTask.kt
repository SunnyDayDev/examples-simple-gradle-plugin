package me.sunnydaydev.autoincrementor

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import kotlin.reflect.full.declaredFunctions

/**
 * Created by sunny on 04.10.2017.
 * mail: mail@sunnydaydev.me
 */

open class VariantIncrementTask: DefaultTask() {

    internal lateinit var variant: ApplicationVariant
    internal lateinit var increment: Increment
    internal lateinit var store: AutoIncrementStore
    internal lateinit var appExtension: AppExtension

    private val willAssemble get() = project.gradle.taskGraph.allTasks
            .map { it.name.split(":").last() }
            .contains("assemble${variant.name.capitalize()}")

    private val singleIncrementTask get() = project.gradle.taskGraph.allTasks
            .lastOrNull()?.name == name

    @TaskAction
    fun increment() {

        if (willAssemble || singleIncrementTask) {

            store.versionCode += increment.versionCodeIncrement

            val base = store.versionName
                    .split(".")
                    .map { it.toIntOrNull() ?: 0 }
            val increments = increment.versionNameIncrement
                    .split(".")
                    .map { it.toIntOrNull() ?: 0 }

            store.versionName = (0 until maxOf(base.size, increments.size))
                    .map { base.getOrElse(it, { 0 }) + increments.getOrElse(it, { 0 }) }
                    .joinToString { it.toString() }

            appExtension.applicationVariants
                    .forEach {
                        it.setVersionCode(store.versionCode)
                        it.setVersionName(store.versionName)
                    }


        }

    }

    private fun ApplicationVariant.setVersionCode(code: Int) {

        mergedFlavor::class.declaredFunctions
                .find { it.name == "setVersionCode" }
                ?.call(mergedFlavor, code)

    }

    private fun ApplicationVariant.setVersionName(name: String) {

        mergedFlavor::class.declaredFunctions
                .find { it.name == "setVersionName" }
                ?.call(mergedFlavor, name)

    }

}
