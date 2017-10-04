package me.sunnydaydev.autoincrementor

import com.android.build.gradle.*
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.internal.api.ApplicationVariantImpl
import org.gradle.api.Plugin
import org.gradle.api.Project

class AutoIncrementorPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        val extension = project.extensions.create(
                "autoIncrementor",
                AutoIncrementExtension::class.java,
                project.container(Increment::class.java)
        )

        if (project.plugins.hasPlugin(AppPlugin::class.java)) {

            val app = project.extensions.getByType(AppExtension::class.java)

            app.applicationVariants.all { variant ->

                val increment = extension.increments
                        .find { canIncrement(it, variant) }
                        ?: return@all

                val task = project.tasks.create(
                        "incrementVersion${variant.name.capitalize()}",
                        VariantIncrementTask::class.java
                ) {

                    it.group = "Auto incrementor"

                    it.increment = increment
                    it.variant = variant

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
