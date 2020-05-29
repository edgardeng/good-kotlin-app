package com.edgar.movie.demo_study.productivity_boosters

import org.junit.Test
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate

class ProductivityBoostersInKotlin {
    @Test
    fun demo() {
        // Named Arguments o allow clearer invocations and avoid mistakes with the order of arguments.

//        println(format("mario", "example.com"))                         // 1
//        println(format("domain.com", "username"))                       // 2
//        println(format(userName = "foo", domain = "bar.com"))           // 3
//        println(format(domain = "frog.com", userName = "pepe"))

        // String Templates: include variable references and expressions into strings.

        val greeting = "Kotliner"

        println("Hello $greeting")                  // 1
        println("Hello ${greeting.toUpperCase()}")

        // Destructuring Declarations
        val (x, y, z) = arrayOf(5, 10, 15) // 1
        print(x)
        print(" ")
        print(y)
        print(" ")
        print(z)
        println(" ")


        val map = mapOf("Alice" to 21, "Bob" to 25)
        for ((name, age) in map) {                                      // 2
            println("$name is $age years old")
        }
        // Built-in Pair and Triple types support destructuring too, even as return values from functions.
//        val (min, max) = findMinMax(listOf(100, 90, 50, 98, 76, 83))
        data class User(val username: String, val email: String)    // 1

        fun getUser() = User("Mary", "mary@somewhere.com")

        val user = getUser()
        val (username, email) = user                            // 2  Declared values are mapped to the instance fields.
        println(username == user.component1())                  // 3

        val (_, emailAddress) = getUser()
        println(emailAddress)



        class Pair<K, V>(val first: K, val second: V) {  // 1
            operator fun component1(): K {
                return first
            }

            operator fun component2(): V {
                return second
            }
        }

        val (num, name) = Pair(
            1,
            "one"
        )             // 2Destructures an instance of this class the same way as for built-in Pair.

        println("num = $num, name = $name")

        // Smart Casts

        //  perform type casts automatically in most cases, including:
        //Casts from nullable types to their non-nullable counterparts.
        //Casts from a supertype to a subtype.

        val date: ChronoLocalDate? = LocalDate.now()    // 1

        if (date != null) {
            println(date.isLeapYear)                    // 2
        }

        if (date != null && date.isLeapYear) {          // 3
            println("It's a leap year!")
        }

        if (date == null || !date.isLeapYear) {         // 4
            println("There's no Feb 29 this year...")
        }

        if (date is LocalDate) {
            val month = date.monthValue                 // 5
            println(month)
        }

    }
}