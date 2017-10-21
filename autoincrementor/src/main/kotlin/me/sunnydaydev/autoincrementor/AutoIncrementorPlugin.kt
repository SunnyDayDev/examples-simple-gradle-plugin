package me.sunnydaydev.autoincrementor

import com.android.build.gradle.*
import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.Plugin
import org.gradle.api.Project

class AutoIncrementorPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        val store = AutoIncrementStore(project)

        val extension = project.extensions.create(
                "autoIncrementor",
                AutoIncrementExtension::class.java,
                project.container(Increment::class.java),
                store
        )

        project.afterEvaluate {

            println("AutoIncrement ${ if (extension.enabled) { "enabled" } else { "disabled" } }.")

            if (extension.enabled && extension.increments.isNotEmpty()) {

                println("Increments:")

                extension.increments.forEach {
                    println("""
                        - ${it.name}:
                            Variants: ${it.onVariants.joinToString()}
                            Build increment: ${it.buildIncrement}
                    """.trimIndent())
                }

            }

        }

        if (project.plugins.hasPlugin(AppPlugin::class.java)) {

            val app = project.extensions.getByType(AppExtension::class.java)

            app.applicationVariants.all { variant ->

                val increment = extension.increments
                        .find { canIncrement(it, variant) }
                        ?: return@all

                val task = project.tasks.create(
                        "increment${increment.name.capitalize()}On${variant.name.capitalize()}",
                        VariantIncrementTask::class.java
                ) {

                    it.group = "Auto incrementor"

                    it.increment = increment
                    it.variant = variant
                    it.store = store

                }

                variant.preBuild.dependsOn(task)

            }

        }

    }

    private fun canIncrement(increment: Increment, variant: ApplicationVariant): Boolean {
        return increment.onVariants.contains(variant.name) ||
                increment.onBuildTypes.contains(variant.buildType.name) ||
                increment.onFlavors.any { variant.productFlavors.map { it.name }.contains(it) }
    }

}
