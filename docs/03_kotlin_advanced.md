## Basic Kotlin Language Advanced

### Classes 类

类的声明: 类名称，类头(可选)，类身（可选）
```kotlin
class Customer                               
class Contact(val id: Int, var email: String) 
```

### Collections 集合

主要集合: List, Set, Map 

需创建可变集合时，添加mutable

```kotlin
val list1: MutableList<Int> = mutableListOf(1, 2, 3)
val list2: List<Int> = ListOf(1, 2, 3)  
```

_集合函数_

```kotlin
    val numbers = listOf(1, -2, 3, -4, 5, -6)
    // filter 截取
    val positives = numbers.filter { x -> x > 0 } 
    val negatives = numbers.filter { it < 0 }
    // map 映射
    val doubled = numbers.map { x -> x * 2 }
    val tripled = numbers.map { it * 3 }
    // any 存在
    val anyNegative = numbers.any { it < 0 }             
    // all 存在
    val allEven = numbers.all { it % 2 == 0 } 
    // none 存在
    val allEven2 = numbers.none { it % 2 == 1 } 
    // find 查找
    val words = listOf("Lets", "find", "something", "in", "collection", "somehow")  // 1
    val first = words.find { it.startsWith("some") }                                // 2
    val last = words.findLast { it.startsWith("some") }                             // 3
    val nothing = words.find { it.contains("nothing") }
    // first 查找
    val firstNum = numbers.first()
    val lastNum = numbers.last()
    val firstEven = numbers.first { it % 2 == 0 }        // 4
    val lastOdd = numbers.last { it % 2 != 0 }
    // 数量
    val totalCount = numbers.count()                     // 2
    val evenCount = numbers.count { it % 2 == 0 }
    
    data class Person(val name: String, val city: String, val phone: String) // 1

        val people = listOf(                                                     // 2
            Person("John", "Boston", "+1-888-123456"),
            Person("Sarah", "Munich", "+49-777-789123"),
            Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
            Person("Vasilisa", "Saint-Petersburg", "+7-999-123456"))

        val phoneBook = people.associateBy { it.phone }                          // 3
        val cityBook = people.associateBy(Person::phone, Person::city)           // 4
        val peopleCities = people.groupBy(Person::city, Person::name)            // 5
    // filter 分割

    val evenOdd = numbers.partition { it % 2 == 0 }  // ([-2, -4, -6], [1, 3, 5])
    val (positives2, negatives2) = numbers.partition { it > 0 }
    
    // flatMap 映射
    val tripled2 = numbers.flatMap { listOf(it, it, it) } // []
    println(numbers.flatMap { listOf(it, it *it) }) // [1, 1, -2, 4, 3, 9, -4, 16, 5, 25, -6, 36]

    // min and max functions 最大最小
    val empty = emptyList<Int>()
    println("Numbers: $numbers, min = ${numbers.min()} max = ${numbers.max()}") // 1
    println("Empty: $empty, min = ${empty.min()}, max = ${empty.max()}") // For empty collections both functions return null.
    // sorted 排序
    println("Soroted: ${ numbers.sorted()}")
    val inverted = numbers.sortedBy { -it }
    // 获取map的值
    val map = mapOf("key" to 42)
    val value1 = map["key"]
    val value2 = map["key2"]   // 返回null
    val value3: Int = map.getValue("key")  // 存在正常，不存在异常
    val mapWithDefault = map.withDefault { k -> k.length } // 设置默认值
    val value4 = mapWithDefault.getValue("key2")
    try {
       map.getValue("anotherKey")                              // 4
    } catch (e: NoSuchElementException) {
        println("Message: $e")
    }
    // zip 压缩

    val A = listOf("a", "b", "c")                  // 1
    val B = listOf(1, 2)                     // 1

    // zip 不能变长
    val resultPairs = A zip B                         // [(a, 1), (b, 2)],         
    val resultReduce = A.zip(B) { a, b -> "$a$b" }    //[a1, b2], 
    val resultlist = A.zip(B) { a, b -> listOf(a,b) } // [[a, 1], [b, 2]]
    // getOrElse 获取
    val list = listOf(0, 10, 20)
    println(list.getOrElse(1) { 42 })    
    println(list.getOrElse(10) { 42 })

    val map3 = mutableMapOf<String, Int?>()
    println(map3.getOrElse("x") { 1 })       
    map3["x"] = 3
    println(map3.getOrElse("x") { 1 })       
    map3["x"] = null
    println(map3.getOrElse("x") { 1 })
```


### Reference 参考

* [kotlin Example](https://play.kotlinlang.org/byExample/01_introduction)

