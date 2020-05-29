package com.edgar.movie.demo_study

import org.junit.Assert
import org.junit.Test

/**
 * 基本的语法学习
 */
class KotlinBaseLanguage {
    @Test
    fun addition_isCorrect() {
        func_main();
    }
}

/** kotlin 中的函数 **/
// Default Parameter Values and Named Arguments

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

fun func_main() {
    printMessage("Hello")                                               // 5
    printMessageWithPrefix("Hello", "Log")                              // 6
    printMessageWithPrefix("Hello")                                     // 7
    printMessageWithPrefix(prefix = "Log", message = "Hello")           // 8
    println(sum(1, 2))
    println(multiply(2, 2))  // 9
}

// Infix Functions