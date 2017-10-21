package me.sunnydaydev.autoincrementor

import com.android.build.gradle.api.ApplicationVariant
import kotlin.reflect.full.declaredMemberFunctions

/**
 * Created by sunny on 21.10.2017.
 * mail: mail@sunnydaydev.me
 */

fun ApplicationVariant.setVersionCode(code: Int) {
    mergedFlavor::class.declaredMemberFunctions
            .find { it.name == "setVersionCode" }
            ?.call(code) ?: println("Method ApplicationVariant.setVersionCode(Integer) not found.")
}