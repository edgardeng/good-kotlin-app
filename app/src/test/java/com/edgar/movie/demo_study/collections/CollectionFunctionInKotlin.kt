import org.junit.Test

class CollectionFunctionInKotlin {
    @Test
    fun main (){
//        filter function enables you to filter collections
        val numbers = listOf(1, -2, 3, -4, 5, -6)      // 1

        val positives = numbers.filter { x -> x > 0 }  // 2

        val negatives = numbers.filter { it < 0 }      // 3

        println(positives)
        println(negatives)

        // map extension function enables you to apply a transformation to all elements in a collection.
//        val numbers = listOf(1, -2, 3, -4, 5, -6)     // 1

        val doubled = numbers.map { x -> x * 2 }      // 2

        val tripled = numbers.map { it * 3 }
        println(doubled)
        println(tripled)
        // any returns true if the collection contains at least one element that matches the given predicate.
        val anyNegative = numbers.any { it < 0 }             // 2

        val anyGT6 = numbers.any { it > 6 }
        println(anyNegative)
        println(anyGT6 )

        // Function all returns true if all elements in collection match the given predicate.
        val allEven = numbers.all { it % 2 == 0 }            // 2

        val allLess6 = numbers.all { it < 6 }

        // Function none returns true if there are no elements that match the given predicate in the collection.

        val allEven2 = numbers.none { it % 2 == 1 }           // 2

        val allLess2 = numbers.none { it > 6 }

        // find and findLast functions return the first or the last collection element that matches the given predicate. If there are no such elements, functions return null.

        val words = listOf("Lets", "find", "something", "in", "collection", "somehow")  // 1

        val first = words.find { it.startsWith("some") }                                // 2
        val last = words.findLast { it.startsWith("some") }                             // 3

        val nothing = words.find { it.contains("nothing") }

        // first, last:These functions return the first and the last element of the collection correspondingly

        val firstNum = numbers.first()                          // 2
        val lastNum = numbers.last()                            // 3

        val firstEven = numbers.first { it % 2 == 0 }        // 4
        val lastOdd = numbers.last { it % 2 != 0 }

//        count functions returns either the total number of elements
        val totalCount = numbers.count()                     // 2
        val evenCount = numbers.count { it % 2 == 0 }

        // Functions associateBy and groupBy build maps from the elements of a collection indexed by the specified key.

        // associateBy uses the last suitable element as the value
        // groupBy builds a list of all suitable elements and puts it in the value
        data class Person(val name: String, val city: String, val phone: String) // 1

        val people = listOf(                                                     // 2
            Person("John", "Boston", "+1-888-123456"),
            Person("Sarah", "Munich", "+49-777-789123"),
            Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
            Person("Vasilisa", "Saint-Petersburg", "+7-999-123456"))

        val phoneBook = people.associateBy { it.phone }                          // 3
        val cityBook = people.associateBy(Person::phone, Person::city)           // 4
        val peopleCities = people.groupBy(Person::city, Person::name)            // 5

        println("--associateBy ")
        println(phoneBook)
        println("--associateBy ")
        println(cityBook)
        println("--groupBy ")
        println(peopleCities)

        // partition function splits the original collection

        val evenOdd = numbers.partition { it % 2 == 0 }            // 2 Splits numbers into a Pair of lists with even and odd numbers.

        val (positives2, negatives2) = numbers.partition { it > 0 }
        println()
        println(evenOdd) // ([-2, -4, -6], [1, 3, 5])
        println()
        println(positives2)
        println(negatives2)

        // flatMap transforms each element of a collection into an iterable object and builds a single list of the transformation results

        val tripled2 = numbers.flatMap { listOf(it, it, it) }
        println(numbers.flatMap { listOf(it, it *it) })

        // min and max functions

        val empty = emptyList<Int>()

        println("Numbers: $numbers, min = ${numbers.min()} max = ${numbers.max()}") // 1
        println("Empty: $empty, min = ${empty.min()}, max = ${empty.max()}") // For empty collections both functions return null.
        // sorted
        println("Soroted: ${ numbers.sorted()}")
        val inverted = numbers.sortedBy { -it }
        val pow = numbers.sortedBy { it*it }

        println("Soroted: ${ inverted}")
        println("Soroted: ${ pow}")

        // Map Element Access

        val map = mapOf("key" to 42)

        val value1 = map["key"]                                     // 1
        val value2 = map["key2"]                                    // 2

        val value3: Int = map.getValue("key")                       // getValue function returns an existing value corresponding to the given key or throws an exception if the key wasn't found. For maps created with withDefault

        val mapWithDefault = map.withDefault { k -> k.length } // 设置默认值
        val value4 = mapWithDefault.getValue("key2")                // 3

        try {
            map.getValue("anotherKey")                              // 4
        } catch (e: NoSuchElementException) {
            println("Message: $e")
        }

        // zip function merges two given collections into a new collection

        val A = listOf("a", "b", "c")                  // 1
        val B = listOf(1, 2)                     // 1

        // zip 不能变长
        val resultPairs = A zip B                      // 2
        val resultReduce = A.zip(B) { a, b -> "$a$b" }
        val resultlist = A.zip(B) { a, b -> listOf(a,b) }

        println("resultPairs: ${resultPairs}, resultReduce: ${resultReduce}, ")
        println(resultlist)
        //getOrElse provides safe access to elements of a collection.

        val list = listOf(0, 10, 20)
        println(list.getOrElse(1) { 42 })    // 1
        println(list.getOrElse(10) { 42 })

        val map3 = mutableMapOf<String, Int?>()
        println(map3.getOrElse("x") { 1 })       // 1

        map3["x"] = 3
        println(map3.getOrElse("x") { 1 })       // 2

        map3["x"] = null
        println(map3.getOrElse("x") { 1 })


    }
}