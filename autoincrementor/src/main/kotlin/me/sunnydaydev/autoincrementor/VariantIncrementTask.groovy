package me.sunnydaydev.autoincrementor

import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.DefaultTask
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.tasks.TaskAction

/*
class VariantIncrementTask extends DefaultTask {

    private final ApplicationVariant variant
    private final NamedDomainObjectContainer<Increment> increments

    VariantIncrementTask(ApplicationVariant variant,
                         NamedDomainObjectContainer<Increment> increments) {

        super()

        this.variant = variant
        this.increments = increments

    }

    @TaskAction
    def increment() {

        println("Variant: ${variant.name}, version: ${variant.versionCode}")


        def increment  = increments.find {
            it.onVariants.contains(variant.toString()) ||
                    it.onBuildTypes.contains(variant.buildType.name) ||
                    it.onFlavors.contains(variant.mergedFlavor.name)
        }

        if (increment != null) {
            variant.mergedFlavor.versionCode = variant.mergedFlavor.versionCode + increment.buildIncrement
        }

        println("After version: ${variant.versionCode}")

    }

}
*/