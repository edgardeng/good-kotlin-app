## Basic Kotlin Language

### Variable 变量

```kotlin
var a: String = "initial"  // Declares a mutable variable and initializing it
val b: Int = 1             // Declares an immutable variable and initializing it
val c = 3  
var e: Int  
println(e)                // 不可变量在使用前需要初始化 compiler error:  it must be initialized before the first read. 

```

### Function 函数

默认参数，命名参数 Default Parameter Values and Named Arguments


```kotlin
fun printMessage(message: String): Unit {                               // 1
    println(message)
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {  // 2
    println("[$prefix] $message")
}

fun sum(x: Int, y: Int): Int {                                          // 3
    return x + y
}

fun multiply(x: Int, y: Int) = x * y     

```

Infix Functions 中缀函数

```kotlin
  infix fun Int.times(str: String) = str.repeat(this)        // 1
  println(2 times "Bye ")                                    // 2

  val pair = "Ferrari" to "Katrina"                          // 3
  println(pair)

  infix fun String.onto(other: String) = Pair(this, other)   // 4
  val myPair = "McLaren" onto "Lucas"
  println(myPair)

    class Person(val name: String) {
      val likedPeople = mutableListOf<Person>()
      infix fun likes(other: Person) { likedPeople.add(other) }  // 6
    }

  val sophia = Person("Sophia")
  val claudia = Person("Claudia")
  sophia likes claudia 

```

Operator Functions 操作符函数

```kotlin
operator fun Int.times(str: String) = str.repeat(this)       // 1
println(2 * "Bye ")                                          // 2

operator fun String.get(range: IntRange) = substring(range)  // 3
val str = "Always forgive your enemies; nothing annoys them so much."
println(str[0..14])    
```

vararg Parameters 可变参数函数

```kotlin
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
```

### Control Flow 控制流

#### When

```kotlin
fun cases(obj: Any) {                                
    when (obj) {                                     // 1   
        1 -> println("One")                          // 2
        "Hello" -> println("Greeting")               // 3
        is Long -> println("Long")                   // 4
        !is String -> println("Not a string")        // 5
        else -> println("Unknown")                   // 6
    }   
}

val result = when (obj) {                   // 1
        1 -> "one"                              // 2
        "Hello" -> 1                            // 3
        is Long -> false                        // 4
        else -> 42                              // 5
    }
```

#### 循环

```kotlin
val cakes = listOf("carrot", "cheese", "chocolate")

for (cake in cakes) {                               // 1
    println("Yummy, it's a $cake cake!")
}

 while (cakesEaten < 5) {                    // 1
        eatACake()
        cakesEaten ++
    }
    
    do {                                        // 2
        bakeACake()
        cakesBaked++
    } while (cakesBaked < cakesEaten)

```

#### Ranges

```kotlin
for(i in 0..3) {             // 1
    print(i)
} 
for(i in 2..8 step 2) {      // 2
    print(i)
}

for (i in 3 downTo 0) {      // 3
    print(i)
}
```

以上都是双闭区间


### Reference 参考

* [kotlin Example](https://play.kotlinlang.org/byExample/01_introduction)

