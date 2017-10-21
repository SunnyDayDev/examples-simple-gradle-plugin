package me.sunnydaydev.autoincrementor

import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import kotlin.reflect.full.declaredMemberFunctions

/**
 * Created by sunny on 04.10.2017.
 * mail: mail@sunnydaydev.me
 */

open class VariantIncrementTask: DefaultTask() {

    lateinit var variant: ApplicationVariant
    lateinit var increment: Increment
    lateinit var store: AutoIncrementStore

    @TaskAction
    fun increment() {

        //Increment only if assemble
        if (isAssemble()) {
            store.versionCode += increment.buildIncrement
            variant.setVersionCode(store.versionCode)
        }

    }

    private fun isAssemble(): Boolean {
        return project.gradle.taskGraph.allTasks
                .map { it.name.split(":").last() }
                .contains("assemble${variant.name.capitalize()}")
    }

}
