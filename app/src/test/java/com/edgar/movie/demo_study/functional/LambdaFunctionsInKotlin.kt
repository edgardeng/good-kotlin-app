import org.junit.Test

/**
 * Lambda functions ("lambdas") are a simple way to create functions ad-hoc. Lambdas
 */
class LambdaFunctionsInKotlin {
    @Test
    fun demo (){
        // All examples create a function object that performs upper-casing.

        //  (String) -> String (a function type).
        val upperCase1: (String) -> String = { str: String -> str.toUpperCase() } // 1

        val upperCase2: (String) -> String = { str -> str.toUpperCase() }         // 2

        val upperCase3 = { str: String -> str.toUpperCase() }                     // 3

        // You cannot do both together, the compiler has no chance to infer the type that way.
// val upperCase4 = { str -> str.toUpperCase() }                          // 4

        //For lambdas with a single parameter , can use the implicit it variable.
        val upperCase5: (String) -> String = { it.toUpperCase() }                 // 5

        // function pointers (::) .
        val upperCase6: (String) -> String = String::toUpperCase                  // 6

        println(upperCase1("hello"))
        println(upperCase2("hello"))
        println(upperCase3("hello"))
        println(upperCase5("hello"))
        println(upperCase6("hello"))
    }
}