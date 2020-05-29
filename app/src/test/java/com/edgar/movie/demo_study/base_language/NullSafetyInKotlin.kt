import org.junit.Test

class NullSafetyInKotlin {
    @Test
    fun study_demo() {
        // to rid the world of NullPointerException, variable types in Kotlin don't allow the assignment of null
        // declare it nullable by adding ? at the end of its type.
        var neverNull: String = "This can't be null"            // 1

//        neverNull = null                                      // 2 trying to assign null to non-nullable variable, a compilation error

        var nullable: String? = "You can keep a null here"      // 3

        nullable = null                                         // 4

        var inferredNonNull = "The compiler assumes non-null"   // 5

//        inferredNonNull = null                                  // 6

        fun strLength(notNull: String): Int {                   // 7
            return notNull.length
        }

        strLength(neverNull)                                    // 8
//        strLength(nullable) // calling the function with a String? (nullable) argument

    }

    // . Kotlin provides null tracking to elegantly deal with such situations.
    fun describeString(maybeString: String?): String {              // 1
        if (maybeString != null && maybeString.length > 0) {        // 2
            return "String of length ${maybeString.length}"
        } else {
            return "Empty or null string"                           // 3
        }
    }
}