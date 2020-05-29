import org.junit.Test

class ClassesInKotlin {
    @Test
    fun study_demo(){

    }
}


// The class declaration consists of the class name, the class header (specifying its type parameters, the primary constructor etc.) and the class body,

// Both the header and the body are optional; if the class has no body, curly braces can be omitted.

class Customer                                  // 1

class Contact(val id: Int, var email: String)   // 2

fun main() {

    val customer = Customer()                   // 3

    val contact = Contact(1, "mary@gmail.com")  // 4

    println(contact.id)                         // 5
    contact.email = "jane@gmail.com"            // 6
}
