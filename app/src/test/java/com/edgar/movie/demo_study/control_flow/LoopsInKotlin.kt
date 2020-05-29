import org.junit.Test

// Loops: for, while, do-while
class LoopsInKotlin {
    @Test
    fun demo() {
        val cakes = listOf("carrot", "cheese", "chocolate")

        for (cake in cakes) {                               // 1
            println("Yummy, it's a $cake cake!")
        }

        //while and do-while constructs work similarly to most languages as well.

        fun eatACake() = println("Eat a Cake")
        fun bakeACake() = println("Bake a Cake")

        var cakesEaten = 0
        var cakesBaked = 0

        while (cakesEaten < 5) {                    // 1
            eatACake()
            cakesEaten++
        }

        do {                                        // 2
            bakeACake()
            cakesBaked++
        } while (cakesBaked < cakesEaten)

        // -- Iterators

        class Animal(val name: String)

        // Defines an iterator in a class. It must be named iterator and have the operator modifier.
        class Zoo(val animals: List<Animal>) {
            operator fun iterator(): Iterator<Animal> {             // 1
                return animals.iterator()                           // 2
            }
        }

        val zoo = Zoo(listOf(Animal("zebra"), Animal("lion")))

        for (animal in zoo) {                                   // 3
            println("Watch out, it's a ${animal.name}")
        }


    }
}