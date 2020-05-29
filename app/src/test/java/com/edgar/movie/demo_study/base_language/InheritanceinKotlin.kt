import org.junit.Test

/**
 * Inheritance 继承
 */
class InheritanceinKotlin {
    @Test
    fun demo(){

    }
}

// Kotlin classes are final by default. If you want to allow the class inheritance, mark the class with the open modifier.
open class Dog {                // 1
    open fun sayHello() {       // 2
        println("wow wow!")
    }
}

// Kotlin methods are also final by default. As with the classes, the open modifier allows overriding them.
class Yorkshire : Dog() {       // 3
    override fun sayHello() {   // 4
        println("wif wif!")
    }
}

fun main() {
    val dog: Dog = Yorkshire()
    dog.sayHello()
}

//Inheritance with Parameterized Constructor 通过参数构造器来继承

open class Tiger(val origin: String) {
    fun sayHello() {
        println("A tiger from $origin says: grrhhh!")
    }
}

class SiberianTiger : Tiger("Siberia")                  // 1

//fun main() {
//    val tiger: Tiger = SiberianTiger()
//    tiger.sayHello()
//}

//Passing Constructor Arguments to Superclass

open class Lion(val name: String, val origin: String) {
    fun sayHello() {
        println("$name, the lion from $origin says: graoh!")
    }
}

class Asiatic(name: String) : Lion(name = name, origin = "India")
