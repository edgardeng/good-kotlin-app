package com.edgar.movie.demo_study.collections

class MapInKotlin {
    // A map is a collection of key/value pairs, where each key is unique and is used to retrieve the corresponding value.

    val EZPassAccounts: MutableMap<Int, Int> = mutableMapOf(1 to 100, 2 to 100, 3 to 100)   // 1
    val EZPassReport: Map<Int, Int> = EZPassAccounts                                        // 2

    fun updatePointsCredit(accountId: Int) {
        if (EZPassAccounts.containsKey(accountId)) {                                        // 3
            println("Updating $accountId...")
            EZPassAccounts[accountId] = EZPassAccounts.getValue(accountId) + Companion.POINTS_X_PASS  // 4
        } else {
            println("Error: Trying to update a non-existing account (id: $accountId)")
        }
    }

    fun accountsReport() {
        println("EZ-Pass report:")
        EZPassReport.forEach {                                                              // 5
                k, v -> println("ID $k: credit $v")
        }
    }

    fun main() {
        accountsReport()                                                                    // 6
        updatePointsCredit(1)                                                               // 7
        updatePointsCredit(1)
        updatePointsCredit(5)                                                               // 8
        accountsReport()                                                                    // 9
    }

    companion object {
        const val POINTS_X_PASS: Int = 15
    }
}