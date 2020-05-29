package com.edgar.movie.demo_study.delegation

import org.junit.Test
import kotlin.reflect.KProperty

class DelegationPatternInKotlin {
    @Test
    fun demo() {



    }
}

// Delegation Pattern

interface SoundBehavior {                                                          // 1
    fun makeSound()
}

class ScreamBehavior(val n:String): SoundBehavior {                                // 2
    override fun makeSound() = println("${n.toUpperCase()} !!!")
}

class RockAndRollBehavior(val n:String): SoundBehavior {                           // 2
    override fun makeSound() = println("I'm The King of Rock 'N' Roll: $n")
}

// Tom Araya is the "singer" of Slayer
class TomAraya(n:String): SoundBehavior by ScreamBehavior(n)                       // 3

// You should know ;)
class ElvisPresley(n:String): SoundBehavior by RockAndRollBehavior(n)              // 3

//@Test
fun main() {
    val tomAraya = TomAraya("Thrash Metal")
    tomAraya.makeSound()                                                           // 4
    val elvisPresley = ElvisPresley("Dancin' to the Jailhouse Rock.")
    elvisPresley.makeSound()

    // Delegated Properties

    val e = Example()
    println(e.p)
    e.p = "NEW"
    println(e.p)
    // Standard Delegates

    val sample = LazySample()         // 1
    println("lazyStr = ${sample.lazyStr}")  // 2
    println(" = ${sample.lazyStr}")  // 3

    // Storing Properties in a Map

    val user = User(mapOf(
        "name" to "John Doe",
        "age"  to 25
    ))

    println("name = ${user.name}, age = ${user.age}")
}


// Delegated Properties


class Example {
    var p: String by Delegate()                                               // 1

    override fun toString() = "Example Class"
}

class Delegate() {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {        // 2
        return "$thisRef, thank you for delegating '${prop.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) { // 2
        println("$value has been assigned to ${prop.name} in $thisRef")
    }
}


// Standard Delegates

class LazySample {
    init {
        println("created!")            // 1
    }

    val lazyStr: String by lazy {
        println("computed!")          // 2
        "my lazy"
    }
}


// Storing Properties in a Map

class User(val map: Map<String, Any?>) {
    val name: String by map                // 1
    val age: Int     by map                // 1
}
