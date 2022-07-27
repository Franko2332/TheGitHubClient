package ru.gb.dil

import java.lang.IllegalStateException
import kotlin.reflect.KClass

object Di {
    val dependencies: MutableMap<KClass<*>, Any> = HashMap()

    inline fun <reified T> add(dependency: T) {
        dependencies[T::class] = dependency!!
    }

     inline fun <reified T: Any> get(): T {
         dependencies.forEach {
             if (it.value is T) return it.value as T
         }
         throw IllegalStateException("Dependency missing")
     }
}
