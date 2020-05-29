package com.edgar.movie.demo_study.control_flow

import org.junit.Test

class RangesInKotlin {
    @Test
    fun demo () {
        // from 0 up to 3 (inclusive). 都是闭区间
        for(i in 0..3) {             // 1
            print(i)
        }
        print(" ")

        for(i in 2..8 step 2) {      // 2
            print(i)
        }
        print(" ")

        for (i in 3 downTo 0) {      // 3
            print(i)
        }
        print(" ")
        println(" ")
        println(" ")

        // Char ranges are also supported:

        for (c in 'a'..'d') {        // 1
            print(c)
        }
        print(" ")

        for (c in 'z' downTo 's' step 2) { // 2
            print(c)
        }
        print(" ")
        println(" ")
        println(" ")

        // Ranges are also useful in if statements:

        val x = 2
        if (x in 1..5) {            // 1
            print("${x} is in range from 1 to 5")
        }
        println()

        if (x !in 6..10) {          // 2
            print("${x}  is not in range from 6 to 10")
        }

        // Equality Checks

        val authors = setOf("Shakespeare", "Hemingway", "Twain")
        val writers = setOf("Twain", "Shakespeare", "Hemingway")

        println(authors == writers)   // 1 Returns true because it calls authors.equals(writers) and sets ignore element order
        println(authors === writers)  // 2 false because authors and writers are distinct references.

        // Conditional Expression

        // There is no ternary operator condition ? then : else in Kotlin. Instead, if may be used as an expression: 没有二元表达式

        fun max(a: Int, b: Int) = if (a > b) a else b         // 1

        println(max(99, -42))


    }
}