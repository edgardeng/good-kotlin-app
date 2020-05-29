import org.junit.Test

class FunctionInKotlin {
    @Test
    fun study_demo() {
        // Kotlin has powerful type inference
        var a: String = "initial"  // 1 Declares a mutable variable and initializing it.
        println(a)
        val b: Int = 1             // 2 Declares an immutable variable and initializing it.
        val c = 3 // 3 Declares an immutable variable and initializing it without specifying the type. The compiler infers the type Int.
        var e: Int  // 1 Declares a variable without initialization
//        println(e) // e causes a compiler error: Variable 'e' must be initialized
        //  it must be initialized before the first read.
        val d: Int  // 1
        if (2>1) {
            d = 1   // 2
        } else {
            d = 2   // 2
        }
        println(d)
    }
}