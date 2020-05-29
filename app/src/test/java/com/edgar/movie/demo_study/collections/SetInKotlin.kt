package com.edgar.movie.demo_study.collections

import org.junit.Test

class SetInKotlin {
    val openIssues: MutableSet<String> = mutableSetOf("uniqueDescr1", "uniqueDescr2", "uniqueDescr3") // 1

    fun addIssue(uniqueDesc: String): Boolean {
        return openIssues.add(uniqueDesc)                                                             // 2
    }

    fun getStatusLog(isAdded: Boolean): String {
        return if (isAdded) "registered correctly." else "marked as duplicate and rejected."          // 3
    }

    @Test
    fun main() {
        // A set is an unordered collection
        val aNewIssue: String = "uniqueDescr4"
        val anIssueAlreadyIn: String = "uniqueDescr2"

        println("Issue $aNewIssue ${getStatusLog(addIssue(aNewIssue))}")                              // 4
        println("Issue $anIssueAlreadyIn ${getStatusLog(addIssue(anIssueAlreadyIn))}")                // 5
    }
}