package com.edgar.movie.demo_study.functional

import org.junit.Test

class ExtensionFunctionsInKotlin {
    @Test
    fun demo (){

        val order = Order(listOf(Item("Bread", 25.0F), Item("Wine", 29.0F), Item("Water", 12.0F)))

        println("Max priced item name: ${order.maxPricedItemName()}")                     // 4
        println("Max priced item value: ${order.maxPricedItemValue()}")
        println("Items: ${order.commaDelimitedItemNames}")

    }
}


// add new members to any class with the extensions mechanism

data class Item(val name: String, val price: Float)                                   // 1

data class Order(val items: Collection<Item>)

fun Order.maxPricedItemValue(): Float = this.items.maxBy { it.price }?.price ?: 0F    // 2
fun Order.maxPricedItemName() = this.items.maxBy { it.price }?.name ?: "NO_PRODUCTS"

val Order.commaDelimitedItemNames: String                                             // 3
    get() = items.map { it.name }.joinToString()


// It is even possible to execute extensions on null references. In an extension function, you can check the object for null and use the result in your code:

fun <T> T?.nullSafeToString() = this?.toString() ?: "NULL"  // 1
