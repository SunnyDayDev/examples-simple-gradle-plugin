package me.sunnydaydev.autoincrementor

import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by sunny on 04.10.2017.
 * mail: mail@sunnydaydev.me
 */

open class VariantIncrementTask: DefaultTask() {

    lateinit var variant: ApplicationVariant
    lateinit var increment: Increment

    @TaskAction
    fun increment() {

        println("Start $name")

        project.gradle.taskGraph.allTasks
                .find { it.name == "assemble${variant.name.capitalize()}" }
                ?: return

        println("Variant class: ${variant::class.java.name}")

        println("Variant: ${variant.name}")

    }

}
