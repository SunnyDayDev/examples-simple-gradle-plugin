package me.sunnydaydev.autoincrementor

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer

/**
 * Created by sunny on 03.10.2017.
 * mail: mail@sunnydaydev.me
 */

open class AutoIncrementExtension(val increments: NamedDomainObjectContainer<Increment>) {

    var enabled = true

    fun increments(action: Action<in NamedDomainObjectContainer<Increment>>) {
        action.execute(increments)
    }

}
