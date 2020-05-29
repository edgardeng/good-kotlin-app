package com.edgar.movie.demo_study.base_language

import org.junit.Test

/**
 * 基本的语法学习
 */
class FunctionInKotlin {
    @Test
    fun study_demo() {
        functioninkotlin_main();
    }

    // -- Default Parameter Values and Named Arguments

    fun printMessage(message: String): Unit {                               // 1
        println(message)
    }

    fun printMessageWithPrefix(message: String, prefix: String = "Info") {  // 2
        println("[$prefix] $message")
    }

    fun sum(x: Int, y: Int): Int {                                          // 3
        return x + y
    }

    fun multiply(x: Int, y: Int) = x * y                                    // 4

    fun functioninkotlin_main() {
        println("-- Default Parameter Values and Named Arguments")
        printMessage("Hello")                                               // 5
        printMessageWithPrefix("Hello", "Log")                              // 6
        printMessageWithPrefix("Hello")                                     // 7
        printMessageWithPrefix(prefix = "Log", message = "Hello")           // 8
        println(sum(1, 2))
        infix_functions_main()
        operator_functions_main()
        vararg_parameters_main()
// 9
    }

    fun infix_functions_main() {
        println("-- Infix Functions");
        infix fun Int.times(str: String) = str.repeat(this)        // 1
        println(2 times "Bye ")                                    // 2

        val pair = "Ferrari" to "Katrina"                          // 3
        println(pair)

        infix fun String.onto(other: String) = Pair(this, other)   // 4
        val myPair = "McLaren" onto "Lucas"
        println(myPair)

        val sophia = Person("Sophia")
        val claudia = Person("Claudia")
        sophia likes claudia                                       // 5
    }

    class Person(val name: String) {
        val likedPeople = mutableListOf<Person>()
        infix fun likes(other: Person) {
            likedPeople.add(other)
        }  // 6
    }

    fun operator_functions_main() {
        operator fun Int.times(str: String) = str.repeat(this)       // 1
        println(2 * "Bye ")                                          // 2

        operator fun String.get(range: IntRange) = substring(range)  // 3
        val str = "Always forgive your enemies; nothing annoys them so much."
        println(str[0..14])
    }
    fun vararg_parameters_main() {

        println("-- Functions with vararg Parameters")
        fun printAll(vararg messages: String) {                            // 1
            for (m in messages) println(m)
        }
        printAll("Hello", "Hallo", "Salut", "Hola", "你好")                 // 2

        fun printAllWithPrefix(vararg messages: String, prefix: String) {  // 3
            for (m in messages) println(prefix + m)
        }
        printAllWithPrefix(
            "Hello", "Hallo", "Salut", "Hola", "你好",
            prefix = "Greeting: "                                          // 4
        )

        fun log(vararg entries: String) {
            printAll(*entries)                                             // 5
        }

    }

}