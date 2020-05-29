import org.junit.Test

// 高阶函数
class HigherOrderFunctionsInKotlin {
    // Taking Functions as Parameters

    fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {  // 1
        return operation(x, y)                                          // 2
    }

    fun sum(x: Int, y: Int) = x + y

    // Returning Functions
    fun operation(): (Int) -> Int {                                     // 1
        return ::square
    }

    fun square(x: Int) = x * x


    @Test
    fun demo () {
        val sumResult = calculate(4, 5, ::sum)
        // :: is the notation that references a function by name in Kotlin.
        val mulResult = calculate(4, 5) { a, b -> a * b }               // 5
        println("sumResult $sumResult, mulResult $mulResult")

        val func = operation()                                          // 3
        println(func(2))

    }

}