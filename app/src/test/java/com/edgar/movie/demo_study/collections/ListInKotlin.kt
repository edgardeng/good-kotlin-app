package com.edgar.movie.demo_study.collections

import org.junit.Test

class ListInKotlin {
    @Test
    fun demo () {
        //  lists can be either mutable (MutableList) or read-only (List)

//        use the standard library functions listOf() for read-only lists
//        and mutableListOf() for mutable lists.
        val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)        // 1
        val sudoers: List<Int> = systemUsers                              // 2

        fun addSudoer(newUser: Int) {                                     // 3
            systemUsers.add(newUser)
        }

        fun getSysSudoers(): List<Int> {                                  // 4
            return sudoers
        }

        // Updates the MutableList. All related read-only views are updated as well since they point to the same object.
            addSudoer(4)                                                  // 5
            println("Tot sudoers: ${getSysSudoers().size}")               // 6
            getSysSudoers().forEach {                                     // 7
                    i -> println("Some useful info on user $i")
            }
            // getSysSudoers().add(5) <- Error!                           // 8

    }

}