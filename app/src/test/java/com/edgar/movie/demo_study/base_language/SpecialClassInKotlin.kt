package com.edgar.movie.demo_study.base_language

import org.junit.Test
import java.util.*

class SpecialClassInKotlin {
    @Test
    fun demo (){
        //Data Classes
//        Data classes make it easy to create classes that are used to store values
        // provided with methods for copying, getting a string representation, and using instances in collections

        val user = User("Alex", 1)
        println(user)                                          // 2

        val secondUser = User("Alex", 1)
        val thirdUser = User("Max", 2)

        println("user == secondUser: ${user == secondUser}")   // 3
        println("user == thirdUser: ${user == thirdUser}")

        println(user.hashCode())                               // 4
        println(thirdUser.hashCode())

        // copy() function
        println(user.copy())                                   // 5
        println(user.copy("Max"))                              // 6
        println(user.copy(id = 2)) // Use copy with named arguments to change the value despite of the properties order. // 7

        println("name = ${user.component1()}")                 // 8
        println("id = ${user.component2()}") // Auto-generated componentN functions let you get the values of properties in the order of declaration.

        println()
        println(" -- Enum Classes : represent a finite set of distinct values")

        val state = State.RUNNING                         // 2
        val message = when (state) {                      // 3
            State.IDLE -> "It's idle"
            State.RUNNING -> "It's running"
            State.FINISHED -> "It's finished"
        }
        println(message)
        println("---")

        val red = Color.RED
        println(red)                                      // 4
        println(red.containsRed())                        // 5
        println(Color.BLUE.containsRed())

        println()
        println(" -- 封闭类 Sealed classes let you restrict the use of inheritance")
        println(greetMammal(Cat("Snowy")))

        println(" --- Object Keyword ")
        val d1 = LuckDispatcher()             //3
        val d2 = LuckDispatcher()

        d1.getNumber()                        //4
        d2.getNumber()







    }
}

data class User(val name: String, val id: Int)  // Defines a data class with the data modifier. // 1

enum class State {
    IDLE, RUNNING, FINISHED                           // 1
}
// Enums may contain properties and methods like other classes, separated from the list of instances by a semicolon.

enum class Color(val rgb: Int) {                      // 1
    RED(0xFF0000),                                    // 2
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    YELLOW(0xFFFF00);

    fun containsRed() = (this.rgb and 0xFF0000 != 0)  // 3
}

// Defines a sealed class.
sealed class Mammal(val name: String)                                                   // 1

// Defines subclasses. Note that all subclasses must be in the same file.
class Cat(val catName: String) : Mammal(catName)                                        // 2
class Human(val humanName: String, val job: String) : Mammal(humanName)

fun greetMammal(mammal: Mammal): String {
    when (mammal) {
        // A smartcast is performed, casting Mammal to Human
        is Human -> return "Hello ${mammal.name}; You're working as a ${mammal.job}"    // 4
        is Cat -> return "Hello ${mammal.name}"                                         // 5
    }                                                                                   // 6
}

// Defines a blueprint.
class LuckDispatcher {                    //1
    fun getNumber() {                     //2
        var objRandom = Random()
        println(objRandom.nextInt(90))
    }
}

// object Expression

fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {  //1

    val dayRates = object {                                                     //2
        var standard: Int = 30 * standardDays
        var festivity: Int = 50 * festivityDays
        var special: Int = 100 * specialDays
    }

    val total = dayRates.standard + dayRates.festivity + dayRates.special       //3

    print("Total price: $$total")                                               //4

}
//rentPrice(10, 2, 1)

// object Declaration
// It isn't an expression, and can't be used in a variable assignment. You should use it to directly access its members:
object DoAuth {                                                 //1
    fun takeParams(username: String, password: String){         //2
        println("input Auth parameters = $username:$password")
    }
}
//    DoAuth.takeParams("foo", "qwerty")

// Companion Objects
// An object declaration inside a class defines another useful case: the companion object.

class BigBen {                                  //1
    companion object Bonger {                   //2
        fun getBongs(nTimes: Int) {             //3
            for (i in 1 .. nTimes) {
                print("BONG ")
            }
        }
    }
}

//    BigBen.getBongs(12)
